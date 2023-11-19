<template>
  <div class="container mt-4">

    <h4 class="mb-4">비디오 상세</h4>
    <hr>

    <div class="mb-3">
      <strong>제목:</strong> {{ store.video.title }}
    </div>
    <div class="mb-3">
      <strong>채널:</strong> {{ store.video.channel_name }}
    </div>
    <div class="mb-3">
      <strong>조회수:</strong> {{ store.video.view_cnt }}
    </div>
    <div class="mb-3">
      <strong>파트:</strong> {{ store.video.part }}
    </div>

    <div v-if="store.video.url" class="mb-4">
      <iframe width="560" height="315" :src="`https://www.youtube.com/embed/${youtubeVideoId}`"
        title="YouTube video player" frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        allowfullscreen>
      </iframe>
    </div>

    <ReviewList />
    <button class="btn btn-primary" @click="showReviews">리뷰보기</button>
    <button class="btn btn-secondary" @click="goToVideoList">목록으로</button>

    <!-- 모달 컴포넌트 추가 -->
    <!-- <ReviewRegModal v-if="showModal" @closeModal="closeModal" /> -->

  </div>
</template>
  
<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useVideoStore } from "@/stores/video";
import { useReviewStore } from '../../stores/review';
import { onMounted, computed } from "vue";
import axios from 'axios'
import ReviewList from '../review/ReviewList.vue';
import ReviewRegModal from '../review/ReviewRegModal.vue';
import { ref } from 'vue';

const store = useVideoStore()
const reviewStore = useReviewStore()

const route = useRoute();
const router = useRouter();
onMounted(() => {

  store.getVideo(route.params.videoId);
  reviewStore.getReviewList(route.params.videoId);

});
// router.go(0);

const createReview = function () {
  router.push({ name: 'videoReviewCreate', params: { videoId: route.params.videoId } });

}

const goToVideoList = function () {
  router.push({ name: 'videoList' })
}

function getYouTubeVideoId(url) {
  // YouTube 동영상 URL에서 'v=' 뒤의 문자열을 추출
  const regExp = /^.*(?:youtu.be\/|v\/|e\/|u\/\w+\/|embed\/|v=)([^#\&\?]*).*/;
  const match = url.match(regExp);

  // 추출된 문자열이 있다면 Video ID 반환, 없다면 null 반환
  return (match && match[1]) ? match[1] : null;
}


const youtubeVideoId = computed(() => {
  return store.video.url ? getYouTubeVideoId(store.video.url) : null;
});
const videoId = computed(() => {
  return store.video.video_id ? `${store.video.video_id}` : null;
});

// showModal 상태
const showModal = ref(false);



</script>
  

<style scoped></style>
  