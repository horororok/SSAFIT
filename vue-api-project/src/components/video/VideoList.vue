<template>
  <div class="container mt-4">

    <!-- 영상 목록 제목 -->
    <h4>영상 목록</h4>
    <hr>

    <!-- 파트별로 필터링할 버튼들 -->
    <div class="mb-3 d-flex">
      <button @click="filterVideos('전신')" class="btn btn-primary me-2">전신</button>
      <button @click="filterVideos('하체')" class="btn btn-primary me-2">하체</button>
      <button @click="filterVideos('상체')" class="btn btn-primary me-2">상체</button>
      <button @click="filterVideos(null)" class="btn btn-secondary">전체</button>
    
      <!-- 비디오 정렬을 위한 컴포넌트 -->
      <VideoSearchInput />
    </div>
    <hr>

    <!-- 비디오 목록을 표시하는 테이블 -->
    <table class="table">
      <thead>
        <tr>
          <th scope="col">제목</th>
          <th scope="col">썸네일</th>
          <th scope="col">채널 이름</th>
          <th scope="col">파트</th>
          <th scope="col">조회수</th>
        </tr>
      </thead>
      <tbody>
        <!-- 선택된 파트에 따라 필터링된 비디오를 표시 -->
        <tr v-for="video in filteredVideos" :key="video.id">
          <td>
            <!-- 비디오 제목을 클릭하면 비디오 상세 페이지로 이동 -->
            <div @click="showVideoDetail(video.video_id)">{{ video.title }}</div>
          </td>
          <!-- 썸네일 이미지 표시 -->
          <td>
            <img :src="video.thumbnail" alt="Thumbnail" class="img-thumbnail">
          </td>
          <td>{{ video.channel_name }}</td>
          <td>{{ video.part }}</td>
          <td>{{ video.view_cnt }}</td>
        </tr>
      </tbody>
    </table>

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
  height: 2rem; /* Adjust the height as needed */
  line-height: 1.5; /* Set to 1.5 for vertical centering */
  width: 8rem; /* Adjust the width as needed */
}

.btn-primary {
  background-color: #007bff;
  border-color: #007bff;
}

.btn-primary:hover {
  background-color: #0056b3;
  border-color: #0056b3;
}

.btn-secondary {
  background-color: #6c757d;
  border-color: #6c757d;
}

.btn-secondary:hover {
  background-color: #545b62;
  border-color: #545b62;
}
</style>

