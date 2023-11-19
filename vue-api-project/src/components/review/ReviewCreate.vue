<template>
  <div class="container mt-4">
    <h4>댓글 작성</h4>
    <div class="border p-3 mb-3">
      <div class="mb-3">
        <label for="writer" class="form-label">작성자: </label>
        <span v-if="userStore.isLoggedIn && userStore.loginUserObj.nickname">
          {{ userStore.loginUserObj.nickname }}
        </span>
        <span v-else class="text-danger">로그인하세요</span>
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">댓글 내용: </label>
        <textarea id="content" class="form-control" cols="30" rows="5" v-model="review.content"></textarea>
      </div>
      <div class="mb-3">
        <button @click="createReview" class="btn btn-primary me-2">댓글 작성</button>
        <button @click="cancelCreate" class="btn btn-secondary">취소</button>
      </div>
      <div v-if="!userStore.isLoggedIn || !userStore.loginUserObj.nickname">
        <p class="text-danger">로그인이 필요합니다.</p>
      </div>
    </div>
    <ReviewRegModal v-if="showModal" @closeModal="closeModalHandler" />
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useReviewStore } from "@/stores/review";
import { useUserStore } from "@/stores/user";
import router from "@/router";
import { useRoute } from "vue-router";
import ReviewRegModal from "./ReviewRegModal.vue";


const store = useReviewStore();
const userStore = useUserStore();
const route = useRoute();

const showModal = ref(false);

const review = ref({
  // video_id랑 user_id를 넣어서 보내줘야한다.
  video_id: route.params.videoId,
  //로그인 되어 있으면 user_id 보내주고 안되어 있으면 null 보내주기
  user_id: userStore.isLoggedIn ? userStore.loginUserObj.user_id : null,
  content: "",
  created_at: "",
});


const currentDate = ref(new Date().toISOString().substr(0, 10));

const cancelCreate = function () {
  router.push({ name: "videoDetail", params: { videoId: store.review.video_id } });
};

const createReview = function () {
  console.log(review.value)
  if (!userStore.isLoggedIn || !userStore.loginUserObj.nickname) {
    // 로그인하지 않은 경우 알림 표시
    alert("로그인이 필요합니다.");
    return;
  }

  review.value.created_at = currentDate.value;


  // 리뷰 등록 후 모달 표시
store.createReview(review.value)
  .then(() => {
    // 작업이 성공적으로 완료된 경우
    showModal.value = true;
    // 입력칸 비우기
    review.value.content = "";
  })
  .catch(error => {
    // 작업이 실패한 경우
    console.error('Error during review creation:', error);
  });
};

const closeModalHandler =()=>{
  showModal.value = false;
}

onMounted(() => {
  userStore.loginUserObj.nickname = userStore.loginUserObj.nickname;
});
</script>

<style scoped></style>
