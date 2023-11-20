<template>
  <div class="container mt-4">

    <!-- 비디오 상세 정보 헤더 -->
    <h4 class="mb-4">비디오 상세</h4>
    <button class="btn btn-secondary" @click="goToVideoList">목록으로</button>
    <hr>

    <!-- YouTube 비디오 재생 영역 -->
    <div v-if="store.video.url" class="mb-4">
      <iframe width="560" height="315" :src="`https://www.youtube.com/embed/${youtubeVideoId}`"
        title="YouTube video player" frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        allowfullscreen>
      </iframe>
    </div>
    <hr>

    <!-- 비디오 제목 및 정보 -->
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
          <!-- 좋아요 토글 버튼 -->
          <span @click="toggleLike" class="btn-link" style="cursor: pointer; font-size: 20px; text-decoration: none;">
            {{ likeButtonIcon }}
          </span>
          {{ store.video.liked_cnt }}
        </div>
      </div>
    </div>

    <!-- 리뷰 목록 -->
    <ReviewList />
    <!-- 리뷰 목록 보기 버튼 -->
    <button class="btn btn-primary" @click="showReviews">리뷰보기</button>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useVideoStore } from "@/stores/video";
import { useReviewStore } from '../../stores/review';
import { useUserStore } from '../../stores/user';
import { onMounted, computed } from "vue";
import axios from 'axios'
import ReviewList from '../review/ReviewList.vue';
import ReviewRegModal from '../review/ReviewRegModal.vue';
import { ref } from 'vue';

// 비디오, 리뷰, 사용자 스토어 초기화
const store = useVideoStore()
const reviewStore = useReviewStore()
const userStore = useUserStore()

// 라우터 및 라우트 초기화
const route = useRoute();
const router = useRouter();

// 페이지 로드 시 비디오 및 리뷰 정보 로딩
onMounted(() => {
  store.getVideo(route.params.videoId);
  reviewStore.getReviewList(route.params.videoId);

  
  console.log(route.params.videoId);
});



// 리뷰 생성 페이지로 이동
const createReview = function () {
  router.push({ name: 'videoReviewCreate', params: { videoId: route.params.videoId } });
}

// 비디오 목록 페이지로 이동
const goToVideoList = function () {
  router.push({ name: 'videoList' })
}

// YouTube 비디오 ID 추출 함수
function getYouTubeVideoId(url) {
  const regExp = /^.*(?:youtu.be\/|v\/|e\/|u\/\w+\/|embed\/|v=)([^#\&\?]*).*/;
  const match = url.match(regExp);
  return (match && match[1]) ? match[1] : null;
}

// YouTube 비디오 ID 계산
const youtubeVideoId = computed(() => {
  return store.video.url ? getYouTubeVideoId(store.video.url) : null;
});


const isLiked = ref(store.isliked.value);
const user_id = ref(userStore.loginUserObj.user_id);
const video_id = ref(route.params.videoId);

// 좋아요 토글 함수
const toggleLike = function () {
  // 좋아요 상태 업데이트
  isLiked.value = !isLiked.value;

  const videolike = {
    user_id : user_id.value,
    video_id : video_id.value,
  }

  // console.log('userId:', user_id.value);
  // console.log('videoId:', video_id.value);

  if (isLiked.value) {
    // 좋아요 버튼을 눌렀을 때
    store.likeVideo(videolike);
    store.getVideo(route.params.videoId);

  } else {
    // 좋아요 버튼을 다시 눌렀을 때 (좋아요 취소)
    store.unlikeVideo(videolike);
    store.getVideo(route.params.videoId);
  }

  //문제...좋아요 개수 반응형으로 바뀌게 해주려고 getVideo를 좋아요, 취소 할때 실행시켜주는데
  //그러면 계속 조회수도 하나씩 올라가버림 (get할때마다 조회수 올라가게 해서.. 흠...)
}

  //좋아요 한 상태이면 해당 페이지 들어갔을 때 빨간하트 나와야 함
  //새로고침하면 다시 하얀 하트 (onMount 설정?)

// 좋아요 버튼 아이콘 계산
  const likeButtonIcon = computed(() => {
  return isLiked.value ? '❤️' : '🤍';
  });

</script>
<style scoped>
</style>