<template>
  <div class="container mt-4">
    <!-- 영상 목록 제목 -->
    <h4>영상 목록</h4>
    <hr>

    <div class="d-flex justify-content-between align-items-center">
      <!-- 파트별로 필터링할 버튼들 -->
      <div class="mb-3" style="display: flex;">
        <button @click="filterVideos('전신')" class="btn btn-primary me-2">전신</button>
        <button @click="filterVideos('하체')" class="btn btn-primary me-2">하체</button>
        <button @click="filterVideos('상체')" class="btn btn-primary me-2">상체</button>
        <button @click="filterVideos(null)" class="btn btn-secondary">전체</button>
      </div>
      <!-- 비디오 정렬을 위한 컴포넌트 -->
      <VideoSearchInput style="max-width: 200px;" />
    </div>

    <hr>

    <!-- 카드 형식으로 비디오 목록을 표시 -->
    <div class="row">
      <div class="col-md-4" v-for="video in filteredVideos" :key="video.id">
        <div class="card mb-4">
          <img :src="video.thumbnail" class="card-img-top" alt="Thumbnail">
          <div class="card-body">
            <h5 class="card-title clickable-text" @click="showVideoDetail(video.video_id)">
              <!-- 제목이 두 줄 이상이면 생략 처리 -->
              <span style="display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; text-align: center; font-size: 1.5rem;">
                {{ video.title }}
              </span>
            </h5>
            <p class="card-text"><strong>{{ video.channel_name }}</strong></p>
            <p class="card-text"><strong>파트</strong> {{ video.part }} | <strong>조회수</strong> {{ video.view_cnt }}</p>
            <!-- 좋아요 개수 표시 -->
            <div class="d-flex align-items-center">
              <span>{{ video.like_cnt }}</span>

              <!-- isLiked에 따라 하트 아이콘을 보여줌 -->
              <span v-if="video.isliked==1">
                ❤️
              </span>
              <span v-else>
                🤍
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useVideoStore } from "@/stores/video";
import { onMounted, ref, computed } from "vue";
import VideoSearchInput from "./VideoSearchInput.vue";
import router from "@/router"; // Vue Router 인스턴스를 가져옴

const store = useVideoStore();

// 선택된 파트를 저장하는 변수
const selectedPart = ref(null);

onMounted(() => {
  // 페이지 로드시 비디오 목록을 가져옴
  store.getVideoList();
});

// 파트에 따라 비디오를 필터링하는 함수
const filterVideos = (part) => {
  selectedPart.value = part;
};

// 선택된 파트에 따라 필터링된 비디오를 반환하는 계산된 속성
const filteredVideos = computed(() => {
  if (!selectedPart.value) {
    // 선택된 파트가 없으면 전체 비디오 목록 반환
    return store.videoList;
  }
  // 선택된 파트와 일치하는 비디오만 반환
  return store.videoList.filter((video) => video.part === selectedPart.value);
});

// 비디오 상세 페이지로 이동하는 함수
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
/* 필터 버튼 스타일 추가 */
.btn-primary,
.btn-secondary {
  height: 2.5rem;
  line-height: 1.5;
  padding: 1rem 1.5rem;
  font-size: 1.2rem;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.btn-primary {
  background-color: #bfd49e;
  border-color: #bfd49e;
}

.btn-primary:hover {
  background-color: #bfd49e;
  border-color: #bfd49e;
}

.btn-secondary {
  background-color: #6c757d;
  border-color: #6c757d;
}

.btn-secondary:hover {
  background-color: #545b62;
  border-color: #545b62;
}

.button-container {
  display: inline-block;
}

.clickable-text {
  cursor: pointer;
}

.clickable-text:hover {
  color: #414951;
  text-decoration: underline;
}
</style>
