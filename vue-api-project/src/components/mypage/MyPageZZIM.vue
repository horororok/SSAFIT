<template>
  <div>
    <div v-if="videoStore.likedVideos.length === 0" class="empty-message">
      <p>찜한 영상이 없습니다.</p>
      <img src="../../assets/img/zzimcat.jpg" alt="No ZZIM Videos">
    </div>
    <!-- 카드 형식으로 비디오 목록을 표시 -->
    <div class="row justify-content-center">
      <div class="col-md-4" v-for="likedVideo in videoStore.likedVideos" :key="likedVideo.id">
        <div class="card mb-4">
          <img :src="likedVideo.thumbnail" class="card-img-top" alt="Thumbnail">
          <div class="card-body">
            <h5 class="card-title clickable-text" @click="showVideoDetail(likedVideo.video_id)">
              <!-- 제목이 두 줄 이상이면 생략 처리 -->
              <span style="display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden;">
                {{ likedVideo.title }}
              </span>
            </h5>
            <p class="card-text"><strong>{{ likedVideo.channel_name }}</strong></p>
            <p class="card-text"><strong>파트</strong> {{ likedVideo.part }} | <strong>조회수</strong> {{ likedVideo.view_cnt
            }}</p>
            <!-- 좋아요 개수 표시 -->
            <div class="d-flex align-items-center">
              <span>{{ likedVideo.like_cnt }}</span>
              <span>
                ❤️
              </span>
              <!-- 하트 클릭하면 unlike 실행하도록 수정!! -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useVideoStore } from '@/stores/video';
import { useUserStore } from '@/stores/user';
import router from "@/router";

const userStore = useUserStore()
const videoStore = useVideoStore()

onMounted(() => {
  const userId = userStore.loginUserObj.user_id;
  videoStore.getLikedVideos(userId);
})

// 비디오 상세 페이지로 이동
const showVideoDetail = function (videoId) {
  if (videoId) {
    // videoId가 유효하면 비디오 상세 페이지로 이동
    router.push({ name: 'videoDetail', params: { videoId: videoId } });
  } else {
    console.error("Invalid videoId:", videoId);
  }
}


</script>

<style scoped>
.empty-message {
  text-align: center;
  margin: 50px 0;
  padding: 20px;
  /* background-color: #f8f8f8;
  border: 1px solid #ddd;
  border-radius: 8px; */
}

.empty-message p {
  font-size: 18px;
  color: #555;
  margin-bottom: 10px;
}

.empty-message img {
  max-width: 60%;
  height: 500px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.clickable-text {
  cursor: pointer;
}

.clickable-text:hover {
  color: #414951;
  text-decoration: underline;
}
</style>