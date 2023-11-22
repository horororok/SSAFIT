<template>
  <div class="container mt-4">
    <h4>추가 정보 페이지</h4>
    <div v-if="!registered">
      <p>아직 추가 정보를 등록하지 않았습니다.</p>
      <p>유저 게시판에 올라올 정보를 등록해 스포츠 취향에 맞는 사람을 찾아보세요!</p>
      <button @click="insertUser">추가 정보 등록</button>
    </div>
    <div v-else class="card">
      <div class="card-body">
        <div>
          프로필 이미지 선택
        </div>
        <div class="mb-2">
          <strong>나이 :</strong> {{ store.myPageUser.age }}
        </div>
        <div class="mb-2">
          <strong>성별 :</strong> {{ gender }}
        </div>
        <div class="mb-2">
          <strong>자기소개 :</strong> {{ store.myPageUser.self_intro }}
        </div>
        <div class="mb-2">
          <strong>주소 :</strong> {{ store.myPageUser.address }}
        </div>
        <div class="mb-2">
          <strong>선호 스포츠 :</strong> {{ store.myPageUser.fav_sport }}
        </div>
        <div class="mb-2">
          <!-- 이미지를 보여줄 img 태그 -->
          <img v-if="profileImg" :src="profileImg" alt="프로필 이미지">

          <!-- 이미지가 없을 경우의 대체 문구 -->
          <div v-else>
            프로필 이미지가 없습니다.
          </div>
        </div>
      </div>
      <button @click="updateUser">수정</button>
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
  //수정 버튼 클릭시 수정 페이지로 이동
  router.push({ name: 'myregistupdate' });
};

const profileImg = computed(() => {
  return store.myPageUser.profile_image; 
});


const REST_USER_API = `http://localhost:8080/api-user`
// 등록 여부 확인 함수
const isMyPageRegist = function(userId) {
  axios.get(`${REST_USER_API}/mypage/isregist/${userId}`)
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

<style scoped></style>