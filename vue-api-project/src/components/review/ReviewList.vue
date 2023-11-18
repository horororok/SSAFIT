<template>
  <div>
    <h2>리뷰 목록</h2>

    <ReviewCreate />

    <table>
      <tr v-for="review in store.reviewList" :key="review.id">
        <td>{{ review.nickname }}</td>
        <td>
          <div v-if="!review.isEditing">{{ review.content }}</div>
          <textarea v-else v-model="review.updatedContent"></textarea>
        </td>
        <td>{{ review.created_at }}</td>
        <!-- <td>
            <button @click="toggleMenu(review)">메뉴</button>
            <div v-if="review.showMenu" class="dropdown-menu">
              <button v-if="isCurrentUser(review.user_id) && !review.isEditing" @click="startEditing(review)">수정</button>
              <button v-if="isCurrentUser(review.user_id) && review.isEditing" @click="updateReview(review)">등록</button>
              <button v-if="isCurrentUser(review.user_id) && review.isEditing" @click="cancelEditing(review)">취소</button>
              <button v-if="isCurrentUser(review.user_id)" @click="deleteReview(review)">삭제</button>
              <button v-else>좋아요</button>
            </div>
          </td> -->

        <td>
          <div v-if="isCurrentUser(review.user_id)">
            <div v-if="!review.isEditing">
              <button @click="startEditing(review)">수정</button>
              <button @click="deleteReview(review)">삭제</button>
            </div>
            <div v-else>
              <button @click="updateReview(review)">등록</button>
              <button @click="cancelEditing(review)">취소</button>
            </div>
          </div>
        </td>
      </tr>
    </table>
  </div>
</template>
  
<script setup>
import { useReviewStore } from "@/stores/review";
import { ref } from "vue";
import ReviewCreate from "./ReviewCreate.vue";
import { useUserStore } from "../../stores/user";

const store = useReviewStore();
const userStore = useUserStore();

// const selectedReviewId = ref(null);

// const showReviewDetail = function (reviewId) {
//   selectedReviewId.value = reviewId;
// };

const toggleMenu = function (review) {
  review.showMenu = !review.showMenu;
};

const isCurrentUser = function (id) {
  const loggedInUser = userStore.loginUserObj;
  return id === loggedInUser.user_id;
};

const startEditing = function (review) {
  review.isEditing = true;
  review.updatedContent = review.content;
  // store.updateReview(review);
};

const cancelEditing = function (review) {
  review.isEditing = false;
};

const updateReview = function (review) {
  // 리뷰 내용을 업데이트하거나 서버에서 업데이트 요청을 보냅니다.
  review.content = review.updatedContent;
  review.isEditing = false;
  store.updateReview(review);
};

const deleteReview = function (review) {
  store.deleteReview(review.review_id);
};
</script>
  
<style scoped>
.dropdown-menu {
  display: flex;
  flex-direction: column;
  position: absolute;
  background-color: #f9f9f9;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-menu button {
  padding: 10px;
  text-align: left;
  border: none;
  background: none;
  cursor: pointer;
}

.dropdown-menu button:hover {
  background-color: #ddd;
}
</style>
  