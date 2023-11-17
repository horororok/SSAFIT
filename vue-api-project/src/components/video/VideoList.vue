<template>
  <div>
    <!-- 영상 목록 제목 -->
    <h4>영상 목록</h4>
    <hr>

    <!-- 파트별로 필터링할 버튼들 -->
    <button @click="filterVideos('전신')">전신</button>
    <button @click="filterVideos('하체')">하체</button>
    <button @click="filterVideos('상체')">상체</button>
    <button @click="filterVideos(null)">전체</button>
    <hr>

    <!-- 비디오 목록을 표시하는 테이블 -->
    <table>
      <tr>
        <th>제목</th>
        <th>썸네일</th>
        <th>채널 이름</th>
        <th>파트</th>
        <th>조회수</th>
      </tr>

      <!-- 선택된 파트에 따라 필터링된 비디오를 표시 -->
      <tr v-for="video in filteredVideos" :key="video.id">
        <td >
          <!-- 비디오 제목을 클릭하면 비디오 상세 페이지로 이동 -->
          <div @click="showVideoDetail(video.video_id)">{{ video.title }}</div>
          </td>
        <!-- 썸네일 이미지 표시 -->
        <td>
          <img :src="video.thumbnail" alt="Thumbnail" >
        </td>
        <td>{{ video.channel_name }}</td>
        <td>{{ video.part }}</td>
        <td>{{ video.view_cnt }}</td>
      </tr>
    </table>

    <!-- 비디오 정렬을 위한 컴포넌트 -->
    <VideoSearchInput />
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
        //name은 라우터에서 지정한 이름, params는 라우터에서 지정한 파라미터
    } else {
        console.error("Invalid videoId:", videoId);
    }
}
</script>

<style scoped></style>
