<template>
  <div>
    <h4>리뷰 작성</h4>
    <fieldset>
      <legend>등록</legend>
      <div>
        <label for="writer">작성자 : </label>
        <!-- 현재 로그인된 사용자가 있으면 닉네임을, 없으면 '로그인하세요'를 표시 -->
        <span v-if="userStore.isLoggedIn && userStore.loginUserObj.nickname">
          {{ userStore.loginUserObj.nickname }}
        </span>
        <span v-else>로그인하세요</span>
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
      <!-- 로그인하지 않은 경우 알림 표시 -->
      <div v-if="!userStore.isLoggedIn || !userStore.loginUserObj.nickname">
        <p>로그인이 필요합니다.</p>
      </div>
    </fieldset>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useReviewStore } from "@/stores/review";
import { useUserStore } from "@/stores/user";
import router from "@/router";
import { useRoute } from "vue-router";

const store = useReviewStore();
const userStore = useUserStore();
const route = useRoute();

const review = ref({
  // video_id랑 user_id를 넣어서 보내줘야한다.
  video_id: route.params.videoId,
  user_id: userStore.loginUserObj.user_id,
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
  store.createReview(review.value);
};

onMounted(() => {
  userStore.loginUserObj.nickname = userStore.loginUserObj.nickname;
});
</script>

<style scoped></style>
