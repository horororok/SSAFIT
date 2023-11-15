<template>
  <div>
    <h4>Review Detail</h4>
    <hr>
    <div>
      <strong>작성자:</strong> {{ reviewAuthor.nickname }}
    </div>
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
import { ref, onMounted } from "vue";
import axios from 'axios';
import router from "@/router";

const store = useReviewStore();
const route = useRoute();
const reviewAuthor = ref({});

onMounted(() => {
  store.getReview(route.params.id);
  getReviewAuthorInfo();
});

const deleteReview = function () {
  axios.delete(`http://localhost:8080/api-review/review/${route.params.id}`)
    .then(() => {
      router.push({ name: 'reviewList' });
    });
};

const updateReview = function () {
  router.push({ name: 'reviewUpdate' });
};

const goToReviewList = function () {
  router.push({ name: 'reviewList' });
};

const getReviewAuthorInfo = function () {
  axios.get(`http://localhost:8080/api-user/user/${store.review.user_id}`)
    .then((response) => {
      reviewAuthor.value = response.data;
    })
    .catch((error) => {
      console.error("리뷰 작성자 정보를 가져오는 중 에러 발생:", error);
    });
};

// async function getReviewAuthorInfo() {
//   try {
//     const response = await axios.get(`http://localhost:8080/api-user/user/${store.review.user_id}`);
//     reviewAuthor.value = response.data;
//   } catch (error) {
//     console.error("리뷰 작성자 정보를 가져오는 중 에러 발생:", error);
//   }
// }
</script>
  
<style scoped></style>
  