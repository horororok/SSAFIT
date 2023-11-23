<!-- ReviewList.vue -->
<template>
  <div class="container mt-4">
    <h4 style="color: #3c3c3c;">리뷰 목록</h4>

    <ReviewCreate />

    <table class="table mt-3">
      <tr v-for="review in store.reviewList" :key="review.id">
        <!-- <td>{{ review.profile_image }}</td> -->
        <td>{{ review.nickname }}</td>
        <td>
          <div v-if="!review.isEditing">{{ review.content }}</div>
          <textarea v-else class="form-control" v-model="review.updatedContent"></textarea>
        </td>
        <td>{{ review.created_at }}</td>
        <td>
          <div v-if="isCurrentUser(review.user_id)" class="d-flex">
            <div v-if="!review.isEditing" class="me-2">
              <button @click="confirmDeleteReview(review)" class="btn btn-sm btn-outline-danger" style="background-color: #bfd49e; color: #fff;">삭제</button>&nbsp;
              <button @click="startEditing(review)" class="btn btn-sm btn-outline-primary" style="background-color: #bfd49e; color: #fff;">수정</button>
            </div>
            <div v-else>
              <button @click="updateReview(review)" class="btn btn-sm btn-primary" style="background-color: #bfd49e; color: #fff;">등록</button>&nbsp;
              <button @click="cancelEditing(review)" class="btn btn-sm btn-secondary" style="background-color: #bfd49e; color: #fff;">취소</button>
            </div>
          </div>
        </td>
      </tr>
    </table>

    <!-- 삭제 모달 컴포넌트 추가 -->
    <DeleteModal v-if="showDeleteModal" :showModal.sync="showDeleteModal" @deleteConfirmed="deleteReviewConfirmed" @cancelDelete="cancelDelete" />
  </div>
</template>

<script setup>
import { useReviewStore } from "@/stores/review";
import { ref } from "vue";
import ReviewCreate from "./ReviewCreate.vue";
import { useUserStore } from "../../stores/user";
import DeleteModal from "./DeleteModal.vue";

const store = useReviewStore();
const userStore = useUserStore();

const showDeleteModal = ref(false);
let reviewToDelete;

const isCurrentUser = function (id) {
  const loggedInUser = userStore.loginUserObj;
  return id === loggedInUser.user_id;
};

const confirmDeleteReview = function (review) {
  reviewToDelete = review;
  showDeleteModal.value = true;
};

const deleteReviewConfirmed = function () {
  store.deleteReview(reviewToDelete.review_id);
  showDeleteModal.value = false;
};

const cancelDelete = function () {
  showDeleteModal.value = false;
};

const startEditing = function (review) {
  review.isEditing = true;
  review.updatedContent = review.content;
};

const cancelEditing = function (review) {
  review.isEditing = false;
};

const updateReview = function (review) {
  review.content = review.updatedContent;
  review.isEditing = false;
  store.updateReview(review);
};
</script>

<style scoped>
.container {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.btn-primary, .btn-secondary, .btn-outline-danger, .btn-outline-primary, .btn-outline-secondary {
  background-color: #bfd49e;
  border-color: #bfd49e;
  color: #fff;
}

.btn-primary:hover, .btn-secondary:hover, .btn-outline-danger:hover, .btn-outline-primary:hover, .btn-outline-secondary:hover {
  background-color: #9fbf8e;
  border-color: #9fbf8e;
}

.table {
  color: #3c3c3c;
}
</style>
