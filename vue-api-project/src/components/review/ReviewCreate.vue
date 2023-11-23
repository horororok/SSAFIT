<template>
  <div class="container mt-4">
    <div class="mb-3">
      <label for="writer" class="form-label">작성자 &nbsp; </label>
      <span v-if="userStore.isLoggedIn && userStore.loginUserObj.nickname" style="color: #3c3c3c;">
        {{ userStore.loginUserObj.nickname }}
      </span>
      <span v-else class="text-danger">로그인하세요</span>
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">리뷰 내용 </label>
      <textarea id="content" class="form-control" cols="30" rows="5" v-model="review.content"></textarea>
    </div>
    <div class="mb-3">
      <button @click="createReview" class="btn btn-primary me-2"
        style="background-color: #bfd49e; border-color: #bfd49e;">댓글 작성</button>
      <button @click="cancelCreate" class="btn btn-secondary" style="background-color: #fff; color: #3c3c3c;">취소</button>
    </div>
    <div v-if="!userStore.isLoggedIn || !userStore.loginUserObj.nickname">
      <p class="text-danger">로그인이 필요합니다.</p>
    </div>
    <ReviewwarnModal v-if="showwarnModal" @closewarnModal="closewarnModalHandler" />
    <ReviewRegModal v-if="showModal" @closeModal="closeModalHandler" />
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useReviewStore } from "@/stores/review";
import { useUserStore } from "@/stores/user";
import router from "@/router";
import { useRoute } from "vue-router";
import ReviewRegModal from "../modal/ReviewRegModal.vue";
import ReviewwarnModal from "../modal/ReviewwarnModal.vue";

const store = useReviewStore();
const userStore = useUserStore();
const route = useRoute();

const showModal = ref(false);

const showwarnModal = ref(false);

const review = ref({
  video_id: route.params.videoId,
  user_id: userStore.isLoggedIn ? userStore.loginUserObj.user_id : null,
  content: "",
  created_at: "",
});

const currentDate = ref(new Date().toISOString().substr(0, 10));

const cancelCreate =  function () {
  review.value.content = "";

};


const createReview = function () {
  if (!userStore.isLoggedIn || !userStore.loginUserObj.nickname) {
    alert("로그인이 필요합니다.");
    return;
  }

  // 리뷰 내용이 비어 있는지 확인
  if (!review.value.content.trim()) {
    showwarnModal.value = true;
    review.value.content = "";
    return;
  }

  review.value.created_at = currentDate.value;

  store.createReview(review.value)
    .then(() => {
      showModal.value = true;
      review.value.content = "";
    })
    .catch(error => {
      console.error('Error during review creation:', error);
    });
};

const closeModalHandler = () => {
  showModal.value = false;
};

const closewarnModalHandler = () => {
  showwarnModal.value = false;
};

onMounted(() => {
  userStore.loginUserObj.nickname = userStore.loginUserObj.nickname;
});
</script>

<style scoped>
.container {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.btn-primary {
  background-color: #bfd49e;
  border-color: #bfd49e;
  color: #fff;
}

.btn-primary:hover {
  background-color: #9fbf8e;
  border-color: #9fbf8e;
}

.btn-secondary {
  background-color: #fff;
  color: #3c3c3c;
}

.text-danger {
  color: #dc3545;
}
</style>
