<template>
  <div class="container mt-5">
    <h5 class="text-center">추가 정보 수정 🐾</h5>
    <form class="col-md-6 offset-md-3 mt-4">
      <div class="mb-3">
        <label for="age" class="form-label">나이</label>
        <div class="input-group">
          <input type="number" class="form-control" id="age" v-model="age" />
        </div>
      </div>
      <div class="mb-3">
        <label class="form-label">성별</label>
        <div class="form-check">
          <input type="radio" class="form-check-input" id="female" v-model="gender" value="female" />
          <label class="form-check-label" for="female">여성</label>
        </div>
        <div class="form-check">
          <input type="radio" class="form-check-input" id="male" v-model="gender" value="male" />
          <label class="form-check-label" for="male">남성</label>
        </div>
      </div>
      <div class="mb-3">
        <label for="self_intro" class="form-label">자기소개 한줄</label>
        <input type="textarea" class="form-control" id="self_intro" v-model="self_intro" />
      </div>
      <div class="mb-3">
        <label for="city" class="form-label">시(도)</label>
        <select class="form-select" id="city" v-model="address">
          <option value="" disabled selected>시(도)를 선택하세요</option>
          <option value="서울특별시">서울특별시</option>
          <option value="인천광역시">인천광역시</option>
          <option value="부산광역시">부산광역시</option>
          <option value="대구광역시">대구광역시</option>
          <option value="광주광역시">광주광역시</option>
          <option value="대전광역시">대전광역시</option>
          <option value="울산광역시">울산광역시</option>
          <option value="세종특별자치시">세종특별자치시</option>
          <option value="경기도">경기도</option>
          <option value="강원도">강원도</option>
          <option value="충청북도">충청북도</option>
          <option value="충청남도">충청남도</option>
          <option value="전라북도">전라북도</option>
          <option value="전라남도">전라남도</option>
          <option value="경상북도">경상북도</option>
          <option value="경상남도">경상남도</option>
          <option value="제주특별자치도">제주특별자치도</option>
        </select>
      </div>

      <div class="mb-3">
        <label class="form-label">선호 스포츠 1순위</label>
        <div class="form-check">
          <input type="radio" class="form-check-input" id="soccer" v-model="fav_sport" value="축구" />
          <label class="form-check-label" for="soccer">축구</label>
        </div>
        <div class="form-check">
          <input type="radio" class="form-check-input" id="baseball" v-model="fav_sport" value="야구" />
          <label class="form-check-label" for="baseball">야구</label>
        </div>
        <div class="form-check">
          <input type="radio" class="form-check-input" id="basketball" v-model="fav_sport" value="농구" />
          <label class="form-check-label" for="basketball">농구</label>
        </div>
        <div class="form-check">
          <input type="radio" class="form-check-input" id="volleyball" v-model="fav_sport" value="배구" />
          <label class="form-check-label" for="volleyball">배구</label>
        </div>
      </div>
      <div class="mb-3">
        <label for="profile_image" class="form-label">프로필 이미지</label>
        <div class="image-list" style="display: flex;">
          <div v-for="(image, index) in imageList" :key="index" @click="selectImage(index)" class="image-item">
            <img :src="`/profileimg/${image.name}.jpg`" alt="프로필 이미지" 
                  :style="{ cursor: 'pointer' }" class="hoverable" />
            {{ image.name }}
          </div>
        </div>
        <div class="form-text" v-if="selectedImage">
          선택된 이미지: {{ selectedImage.name }}
        </div>
      </div>
      <div>
        <button type="button" class="btn btn-success mt-3" @click="updateMypage">수정</button>
        <button type="button" class="btn btn-secondary mt-3 mx-1" @click="cancelUpdate">취소</button>
      </div>
      </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";

const store = useUserStore();
const router = useRouter();

const age = ref("");
const gender = ref("");
const self_intro = ref("");
const address = ref("");
const fav_sport = ref("");
const selectedImage = ref("");

const imageList = ref([
  { name: 'cutecat', url: '/profileimg/cutecat.jpg' },
  { name: 'hipcat', url: '/profileimg/hipcat.jpg' },
  { name: 'santacat', url: '/profileimg/santacat.jpg' },
  { name: 'sleepycat', url: '/profileimg/sleepycat.jpg' },
  { name: 'taehuncat', url: '/profileimg/taehuncat.jpg' },
]);

const selectImage = (index) => {
  selectedImage.value = imageList.value[index];
  
};

onMounted(() => {
  const mypageUser = store.myPageUser;
  age.value = mypageUser.age;
  gender.value = mypageUser.gender === 1 ? "female" : "male";
  self_intro.value = mypageUser.self_intro;
  address.value = mypageUser.address;
  fav_sport.value = mypageUser.fav_sport;
  selectedImage.value = mypageUser.profile_image;
});

const updateMypage = () => {
  const updatedInfo = {
    age: age.value,
    gender: gender.value,
    self_intro: self_intro.value,
    address: address.value,
    fav_sport: fav_sport.value,
    user_id: store.loginUserObj.user_id,
    profile_image: selectedImage.value.url,
  };
  updatedInfo.gender = gender.value === "female" ? 1 : 0;

  store.updateMyPageUser(updatedInfo);
};

const cancelUpdate = () => {
  router.go(-1);
};
</script>

<style scoped>
.image-list {
  display: flex;
}

.image-item {
  margin-right: 10px;
}

.image-item img {
  width: 100px;
  height: 100px;
  object-fit: cover;
  transition: transform 0.2s, border-color 0.2s;
}

.image-item img:hover {
  transform: scale(1.1); 
  border: 2px solid rgb(0, 0, 0); 
}

.form-text {
  margin-top: 10px;
}

.hoverable {
  cursor: pointer;
}

</style>