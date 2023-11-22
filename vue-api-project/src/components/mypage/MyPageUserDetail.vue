<template>
  <div class="container mt-4">
    <div v-if="!registered">
      <p>아직 추가 정보를 등록하지 않았습니다.</p>
      <p>유저 게시판에 올라올 정보를 등록해 스포츠 취향이 맞는 사람을 찾아보세요!</p>
      <button @click="insertUser" class="btn btn-primary">추가 정보 등록</button>
    </div>
    <div v-else class="card">
      <div class="card-body">
        <h5 class="card-title">나의 프로필</h5>
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
        <div class="mb-2"  v-if="profileImg">
          <!-- 이미지를 보여줄 img 태그 -->
          <div class="profile-image-frame">
            <img :src="profileImg" alt="프로필 이미지" class="profile-image" />
          </div>
        </div>
          <!-- 이미지가 없을 경우의 대체 문구 -->
          <div v-else class="mb-2 no-profile-image">
            프로필 이미지가 없습니다.
          </div>
        </div>
      </div>
      <button @click="updateUser" class="btn btn-success">수정</button>
    </div>
  <!-- </div> -->
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

<style scoped>
.card {
  width: 300px;
  margin: 0 auto;
}

.card-body {
  text-align: center;
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
  border: 2px dashed #ccc; /* 이미지 없을 때의 대체 테두리 스타일 */
  border-radius: 50%; /* 원형 프로필 이미지를 위한 테두리 반지름 */
  width: 100%;
  height: auto;
  text-align: center;
  color: #ccc;
}
</style>