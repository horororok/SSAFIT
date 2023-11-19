<template>
  <div class="container mt-4">

    <h4 class="mb-4">비디오 상세</h4>
    <button class="btn btn-secondary" @click="goToVideoList">목록으로</button>
    <hr>

    <div v-if="store.video.url" class="mb-4">
      <iframe width="560" height="315" :src="`https://www.youtube.com/embed/${youtubeVideoId}`"
        title="YouTube video player" frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        allowfullscreen>
      </iframe>
    </div>
    <hr>
    <div class="mb-1" style="font-size: 1.5em;">
      {{ store.video.title }}
    </div>
    <div class="mb-2 bg-light p-3">
  <div class="d-flex justify-content-between">
    <div class="mb-1">
      <strong>채널</strong> {{ store.video.channel_name }}
    </div>
    <div class="mb-1">
      <strong>파트</strong> {{ store.video.part }} |
      <strong>조회수</strong> {{ store.video.view_cnt }}
      <span @click="toggleLike" class="btn-link" style="cursor: pointer; font-size: 20px;">
        {{ likeButtonIcon }}
      </span>
      {{ store.video.like_cnt }}
    </div>
  </div>
</div>


    <ReviewList />
    <button class="btn btn-primary" @click="showReviews">리뷰보기</button>
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

const createReview = function () {
  router.push({ name: 'videoReviewCreate', params: { videoId: route.params.videoId } });
}

const goToVideoList = function () {
  router.push({ name: 'videoList' })
}

function getYouTubeVideoId(url) {
  const regExp = /^.*(?:youtu.be\/|v\/|e\/|u\/\w+\/|embed\/|v=)([^#\&\?]*).*/;
  const match = url.match(regExp);
  return (match && match[1]) ? match[1] : null;
}

const youtubeVideoId = computed(() => {
  return store.video.url ? getYouTubeVideoId(store.video.url) : null;
});

const videoId = computed(() => {
  return store.video.video_id ? `${store.video.video_id}` : null;
});

const isLiked = ref(false);

const toggleLike = function () {
  isLiked.value = !isLiked.value;
};

const likeButtonIcon = computed(() => {
  return isLiked.value ? '❤️' : '🤍';
});

</script>
