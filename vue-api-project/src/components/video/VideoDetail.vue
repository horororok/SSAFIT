<template>
  <div class="container mt-4">
    <button class="btn btn-secondary" @click="goToVideoList">목록으로</button>
    <hr>
    <div v-if="store.video.url" class="mb-4" style="text-align: center;">
      <iframe width="560" height="315" :src="`https://www.youtube.com/embed/${youtubeVideoId}`"
        title="YouTube video player" frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        allowfullscreen>
      </iframe>
    </div>
    <hr>
    <div class="mb-1" style="font-size: 1.5em; color: #3c3c3c;"> 
      {{ video.title }}
    </div>
    <div class="mb-2 bg-light p-3">
      <div class="d-flex justify-content-between">
        <div class="mb-1">
          <strong>채널</strong> {{ video.channel_name }}
        </div>
        <div class="mb-1">
          <strong>파트</strong> {{ video.part }} |
          <span @click="toggleLike" class="btn-link" style="cursor: pointer; font-size: 20px; text-decoration: none; color: #bfd49e;">
            {{ video.is_user_liked === 1 ? "❤️" : "🤍" }}
          </span>
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
import { onMounted, computed } from "vue";
import ReviewList from '@/components/review/ReviewList.vue';
import { ref } from 'vue';

const store = useVideoStore()
const reviewStore = useReviewStore()
const userStore = useUserStore()

const route = useRoute();
const router = useRouter();

const video = computed(() => store.video)

onMounted(() => {
  store.getVideo(route.params.videoId);
  reviewStore.getReviewList(route.params.videoId);
});


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

const userId = ref(userStore.loginUserObj.user_id);
const videoId = ref(route.params.videoId);


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

</script>


<style scoped>
.container {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.btn-secondary {
  background-color: #bfd49e;
  border-color: #bfd49e;
  color: #fff;
}

.btn-secondary:hover {
  background-color: #9fbf8e;
  border-color: #9fbf8e;
}

</style>