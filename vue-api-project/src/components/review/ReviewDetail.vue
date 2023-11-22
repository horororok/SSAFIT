<template>
  <div class="container mt-4">
    <h4 style="color: #3c3c3c;">Review Detail</h4>
    <hr>

<!-- 삭제하기 -->
    <div><strong>작성자:</strong> {{ store.review ? store.review.nickname : '로딩 중' }}</div>
    <div><strong>내용:</strong> {{ store.review ? store.review.content : '로딩 중' }}</div>
    <div><strong>작성일:</strong> {{ store.review ? store.review.created_at : '로딩 중' }}</div>

    <button @click="deleteReview" class="btn btn-danger" style="margin-top: 10px;">삭제</button>
    <button @click="updateReview" class="btn btn-primary" style="margin-top: 10px;">수정</button>
    <button @click="goToReviewList" class="btn btn-secondary" style="margin-top: 10px;">목록으로</button>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
import { useReviewStore } from "@/stores/review";
import { ref, onMounted, computed } from "vue";
import axios from 'axios';
import router from "@/router";
import { useVideoStore } from '../../stores/video';

// props를 명시적으로 선언
const props = defineProps(['reviewId']);

const store = useReviewStore();
const videoStore = useVideoStore();
const route = useRoute();

const localReviewId = ref(null);

const videoId = computed(() => videoStore.video.video_id);
const reviewId = computed(() => store.review.review_id);

onMounted(() => {
  console.log(props.reviewId);

  if (props.reviewId && store.review) {
    store.getReview(props.reviewId);
  } else {
    console.error("review is undefined or null");
  }
});

const deleteReview = function () {
  axios.delete(`http://localhost:8080/api-video/review/${props.reviewId}`)
    .then(() => {
      router.push({ name: 'videoDeatil', params: { reviewId: props.reviewId } });
    });
};

const updateReview = function () {
  router.push({ name: 'videoReviewUpdate', params: { reviewId: props.reviewId } });
};

const goToReviewList = function () {
  router.push({ name: 'videoDetail', params: { videoId: route.params.videoId } });
};
</script>

<style scoped>
.container {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.btn-danger, .btn-primary, .btn-secondary {
  background-color: #bfd49e;
  border-color: #bfd49e;
  color: #fff;
}

.btn-danger:hover, .btn-primary:hover, .btn-secondary:hover {
  background-color: #9fbf8e;
  border-color: #9fbf8e;
}

</style>
