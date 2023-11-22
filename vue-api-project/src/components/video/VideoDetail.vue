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
      {{ video.title }}
    </div>
    <div class="mb-2 bg-light p-3">
      <div class="d-flex justify-content-between">
        <div class="mb-1">
          <strong>채널</strong> {{ video.channel_name }}
        </div>
        <div class="mb-1">
          <strong>파트</strong> {{ video.part }} |
          <!-- 좋아요 토글 버튼 -->
          <span @click="toggleLike" class="btn-link" style="cursor: pointer; font-size: 20px; text-decoration: none;">
            {{ video.is_user_liked === 1 ? "❤️" : "🤍" }}
          </span>
          <!-- {{ likedCnt }} -->
        </div>
      </div>
    </div>
    <ReviewList />
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useVideoStore } from "@/stores/video";
import { useReviewStore } from '@/stores/review';
import { useUserStore } from '@/stores/user';
import { onMounted, computed, watch } from "vue";
import ReviewList from '@/components/review/ReviewList.vue';
import { ref } from 'vue';

const store = useVideoStore()
const reviewStore = useReviewStore()
const userStore = useUserStore()

const route = useRoute();
const router = useRouter();

const video = computed(() => store.video)

// 페이지 로드 시 비디오 및 리뷰 정보 로딩
onMounted(() => {
  store.getVideo(route.params.videoId);
  reviewStore.getReviewList(route.params.videoId);
});

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

const userId = ref(userStore.loginUserObj.user_id);
const videoId = ref(route.params.videoId);
// const isLikedVideo = ref(store.video.is_user_liked);

// const toggleLike = function(){
//   const videolike = {
//     user_id : userId.value,
//     video_id : videoId.value,
//   };

//   if(store.video.is_user_liked === 0){
//     store.video.is_user_liked = 1;
//     store.likeVideo(videolike);
//   } else if(store.video.is_user_liked === 1){
//     store.video.is_user_liked = 0;
//     store.unlikeVideo(videolike);
//   }
// }

const toggleLike = async function() {
  const videolike = {
    user_id: userId.value,
    video_id: videoId.value,
  };

  try {
    if (store.video.is_user_liked === 0) {
      await store.likeVideo(videolike);
      store.video.is_user_liked = 1;
    } else {
      await store.unlikeVideo(videolike);
      store.video.is_user_liked = 0;
    }
  } catch (error) {
    console.error('Error toggling like:', error);
  }
};

// 좋아요 버튼 아이콘 계산
// const likeButtonIcon = computed(() => {
//   return isLikedVideo.value === 1 ? '❤️' : '🤍'; 
// });

// const likedCnt = ref(store.video.liked_cnt);

// // watch 함수를 사용하여 store.video.liked_cnt 변경 감지
// watch(() => store.video.liked_cnt, (newValue) => {
//     console.log('liked_cnt changed:', newValue);
//     // store.video.liked_cnt 값이 변경될 때마다 likedCnt 값 업데이트
//     likedCnt.value = newValue;
// });



// const isLikedVideo = ref(store.video.is_user_liked);
// watch(() => store.video.is_user_liked, (newValue) => {
//     // console.log('liked_cnt changed:', newValue);
//     // store.video.liked_cnt 값이 변경될 때마다 likedCnt 값 업데이트
//     isLikedVideo.value = newValue;
// });


</script>
<style scoped>
</style>