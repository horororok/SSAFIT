<template>
  <div class="container mt-4">
    <h4>내 추가 정보</h4>
    <div class="card">
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
      <!-- 조건부로 버튼 표시 -->
      <button v-if="!store.myPageUserRegistered" @click="insertUser">추가 정보 등록</button>
      <button v-else @click="updateUser">수정</button>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { useRoute, useRouter } from 'vue-router';
import { onMounted, computed } from 'vue';

const store = useUserStore();

const route = useRoute();
const router = useRouter();


onMounted(() => {
  // console.log(route.params.userId);
  store.getmyPageUser(route.params.userId);

});

const gender = computed(() => {
  return store.myPageUser.gender === 1 ? "여자" : "남자";
})

const insertUser = function () {
  router.push({ name: 'myregist' });
};

const updateUser = function () {
  //수정 버튼 클릭시 수정 페이지로 이동
  router.push({ name: 'myresgistupdate' });
};

const profileImg = computed(() => {
  return store.myPageUser.profile_image; 
});

</script>

<style scoped></style>