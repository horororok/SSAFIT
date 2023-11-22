<template>
  <div class="container mt-5">
    <h2 class="text-center">추가 정보 수정 창</h2>
    <form class="col-md-6 offset-md-3 mt-4">
      <!-- 이전에 등록한 정보를 기본값으로 설정 -->
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

        <!-- 이미지 목록 표시 -->
        <div class="image-list" style="display: flex;">
          <div v-for="(image, index) in imageList" :key="index" @click="selectImage(index)">
            <!-- 정적 이미지 파일 경로 사용 -->
            <img :src="`/profileimg/${image.name}.jpg`" alt="프로필 이미지" :style="{ maxWidth: '30%', maxHeight: '30%' }" />
            {{ image.name }}
          </div>
        </div>
        <!-- 선택된 이미지에 대한 설명 텍스트 -->
        <div class="form-text" v-if="selectedImage">
          선택된 이미지: {{ selectedImage.url }}
        </div>
      </div>

      <button type="button" class="btn btn-primary" @click="updateMypage">수정</button>
      <button type="button" class="btn btn-secondary" @click="cancelUpdate">취소</button>
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
  { name: 'img1', url: '/profileimg/img1.jpg' },
  { name: 'img2', url: '/profileimg/img2.jpg' },
  { name: 'img3', url: '/profileimg/img3.jpg' },
]);

const selectImage = (index) => {
  console.log(imageList.value)
  // 선택된 이미지의 인덱스를 저장
  selectedImage.value = imageList.value[index];
  
};

onMounted(() => {
  // 기존 정보를 불러와서 데이터 바인딩
  const mypageUser = store.myPageUser;
  age.value = mypageUser.age;
  gender.value = mypageUser.gender === 1 ? "female" : "male";
  self_intro.value = mypageUser.self_intro;
  address.value = mypageUser.address;
  
  // 선호 스포츠 값 데이터 바인딩
  console.log(mypageUser.fav_sport)
  fav_sport.value = mypageUser.fav_sport;

  console.log(mypageUser.profile_image)
  selectedImage.value = mypageUser.profile_image;
});

const updateMypage = () => {
  // 수정된 정보를 서버에 전송
  console.log(selectedImage.value)
  const updatedInfo = {
    age: age.value,
    gender: gender.value,
    self_intro: self_intro.value,
    address: address.value,
    fav_sport: fav_sport.value,
    user_id: store.loginUserObj.user_id,
    profile_image: selectedImage.value,
  };
  updatedInfo.gender = gender.value === "female" ? 1 : 0;

  // 서버에 수정된 정보 전송
  store.updateMyPageUser(updatedInfo);

  // 수정 후 다시 마이페이지로 이동
  // router.push({ name: "mydata" });
};

const cancelUpdate = () => {
  // 수정 취소 시 이전 페이지로 이동
  router.go(-1);
};
</script>