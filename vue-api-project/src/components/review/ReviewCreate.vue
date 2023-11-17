<template>
    <div>
      <h4>리뷰 작성</h4>
      <fieldset>
        <legend>등록</legend>
        <div>
          <label for="writer">작성자 : </label>
          <input type="text" id="writer" readonly v-model="user.nickname">
        </div>
        <div>
          <label for="content">내용 : </label>
          <textarea id="content" cols="30" rows="10" v-model="review.content"></textarea>
        </div>
        <div>
          <label for="created_at">작성일 : </label>
          <input type="date" id="created_at" readonly v-model="currentDate">
        </div>
        <div>
          <button @click="createReview">등록</button>
          <button @click="cancelCreate">취소</button>
        </div>
      </fieldset>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from "vue";
  import { useReviewStore } from "@/stores/review";
  import { useUserStore } from "@/stores/user";
import router from "../../router";
  
  const store = useReviewStore();
  const review = ref({
    content: "",
    created_at: "",
  });
  
  const userStore = useUserStore();
  const user = ref({
    nickname: "",
  });
  
  const currentDate = ref(new Date().toISOString().substr(0, 10));
  
  const cancelCreate = function () {
    router.push({ name: 'videoDetail', params: { videoId: store.review.video_id } });
  };
  const createReview = function () {
    review.value.created_at = currentDate.value;
    store.createReview(review.value);
  };
  
  onMounted(() => {
    user.nickname = userStore.user.nickname;
  });
  </script>
  
  <style scoped></style>
  