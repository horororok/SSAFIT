<template>
  <div>
    <h4>Review Detail</h4>
    <hr>
    <div><strong>작성자:</strong> {{ store.review.nickname }}</div>
    <div><strong>내용:</strong> {{ store.review.content }}</div>
    <div><strong>작성일:</strong> {{ store.review.created_at }}</div>

    <button @click="deleteReview">삭제</button>
    <button @click="updateReview">수정</button>

    <button @click="goToReviewList">목록으로</button>
  </div>
</template>
  
<script setup>
import { useRoute, } from 'vue-router';
import { useReviewStore } from "@/stores/review";
import { ref, onMounted, computed } from "vue";
import axios from 'axios';
import router from "@/router";
import { useVideoStore } from '../../stores/video';

const store = useReviewStore();
const videoStore = useVideoStore();
const route = useRoute();

const videoId = computed(() => videoStore.video.video_id);

onMounted(() => {
  console.log(route.params.reviewId)
  console.log(route.params)

  if(store.review){
    // const reviewId = store.review.review_id;
    // console.log("reviewId 잘 가져와지나?", reviewId)
    // await store.getReview(reviewId);
    store.getReview(route.params.reviewId)
  }else{
    console.error("review is undefined or null");
  }

});

const deleteReview = function () {
  axios.delete(`http://localhost:8080/api-video/review/${route.params.reviewId}`)
    .then(() => {
      router.push({ name: 'videoDeatil', params: { reviewId: reviewId.value } });
    });
};

const updateReview = function () {
  router.push({ name: 'videoReviewUpdate', params: {reviewId: route.params.reviewId} });


};

const goToReviewList = function () {
  console.log(route.params.videoId)
  router.push({ name: 'videoDetail', params: { videoId: route.params.videoId } });
};

</script>
  
<style scoped></style>
  