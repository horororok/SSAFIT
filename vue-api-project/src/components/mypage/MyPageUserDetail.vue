<template>
  <div class="container mt-4">
    <div v-if="!registered">
      <p>아직 추가 정보를 등록하지 않았습니다.</p>
      <p>유저 게시판에 올라올 정보를 등록해 스포츠 취향이 맞는 사람을 찾아보세요 🐾</p>
      <div>
          <img src="@/assets/img/sofacat.jpg" alt="Cat Image" style="max-width: 30%; height: auto; border-radius: 8px;">
      </div>
      <button @click="insertUser" class="btn btn-success">추가 정보 등록</button>
    </div>
    <div v-else class="card">
      <div class="card-body">
        <div class="profile-title mb-3">
          <h5 class="card-title font-weight-bold" >나의 프로필</h5>
        </div>
        <div class="mb-2">
          {{ store.myPageUser.age }}세
        </div>
        <div class="mb-2">
          {{ gender }}
        </div>
        <div class="mb-2">
          {{ store.myPageUser.self_intro }}
        </div>
        <div class="mb-2">
          {{ store.myPageUser.address }}
        </div>
        <div class="mb-2">
          <strong>선호 스포츠 :</strong> {{ store.myPageUser.fav_sport }}
        </div>
        <div class="mb-2"  v-if="profileImg">
          <div class="profile-image-frame">
            <img :src="profileImg" alt="프로필 이미지" class="profile-image" />
          </div>
        </div>
          <div v-else class="mb-2 no-profile-image">
            프로필 이미지가 없습니다.
          </div>
          <button @click="updateUser" class="btn btn-success d-block mx-auto mt-3">수정</button>
        </div>
      </div>
    </div>
    
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { useRoute, useRouter } from 'vue-router';
import { onMounted, computed, ref } from 'vue';
import axios from 'axios'

const store = useUserStore();

const route = useRoute();
const router = useRouter();

const registered = ref(false);

onMounted(() => {
  store.getmyPageUser(route.params.userId);
  isMyPageRegist(route.params.userId);

});

const gender = computed(() => {
  return store.myPageUser.gender === 1 ? "여자" : "남자";
})

const insertUser = function () {
  router.push({ name: 'myregist' });
};

const updateUser = function () {
  router.push({ name: 'myregistupdate' });
};

const profileImg = computed(() => {
  return store.myPageUser.profile_image; 
});


const REST_MYPAGE_API = `http://localhost:8080/mypage`

const isMyPageRegist = function(userId) {
  axios.get(`${REST_MYPAGE_API}/isregist/${userId}`)
    .then((res) => {
      if (res.data === 1) {
        console.log("추가정보등록확인: ", res.data);
        registered.value = true;
      }
    })
    .catch((err) => {
      console.log("추가등록확인 에러", err);
    });
};
</script>

<style scoped>
.card {
  width: 300px;
  margin: 0 auto;
  border: 1px solid #ddd; 
  border-radius: 8px; 
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); 
}

.card-body {
  text-align: center;
}

.profile-title {
  background-color: #9fbf8e;
  border-radius: 8px;
  padding: 10px;
  text-align: center;
}

.card .card-title {
  margin: 0;
  color: #fff;
}

.card .mb-2 {
  padding: 10px;
  border-bottom: 1px solid #ddd; 
}

.btn {
  margin-top: 10px;
}

.profile-image-frame {
  padding: 10px;
  overflow: hidden;
}

.profile-image {
  width: 100%;
  height: auto;
  border-radius: 70%; 
}

.no-profile-image {
  padding: 10px;
  border: 2px dashed #ccc;
  border-radius: 50%;
  width: 100%;
  height: auto;
  text-align: center;
  color: #ccc;
}
</style>

