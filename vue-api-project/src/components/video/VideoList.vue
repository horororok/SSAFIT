<template>
  <div class="container mt-4">

    <div class="d-flex justify-content-between align-items-center">
      <div class="mb-3 d-flex align-items-center">
        <div class="btn-group" role="group">
          <button @click="filterVideos('전신')" class="btn btn-primary">전신</button>
          <button @click="filterVideos('하체')" class="btn btn-primary">하체</button>
          <button @click="filterVideos('상체')" class="btn btn-primary">상체</button>
          <button @click="filterVideos(null)" class="btn btn-secondary">전체</button>

          <!-- <button @click="filterVideos('전신')"
            :class="{ 'btn-primary': selectedPart?.value === '전신', 'btn-secondary': selectedPart?.value !== '전신' }">전신</button>
          <button @click="filterVideos('하체')"
            :class="{ 'btn-primary': selectedPart?.value === '하체', 'btn-secondary': selectedPart?.value !== '하체' }">하체</button>
          <button @click="filterVideos('상체')"
            :class="{ 'btn-primary': selectedPart?.value === '상체', 'btn-secondary': selectedPart?.value !== '상체' }">상체</button>
          <button @click="filterVideos(null)" :class="{ 'btn-secondary': !selectedPart?.value }">전체</button> -->

        </div>
      </div>

      <div class="d-flex flex-wrap align-items-center">
        <div class="col-md-auto mb-3" style="margin-right: 4px;">
          <select class="form-select" v-model="searchInfo.orderBy" style="font-size: 1.2rem; background-color: #fff;">
            <option value="title">제목</option>
            <option value="view_cnt">조회수</option>
          </select>
        </div>

        <div class="col-md-auto mb-3" style="margin-right: 4px;">
          <select class="form-select" v-model="searchInfo.orderByDir" style="font-size: 1.2rem; background-color: #fff;">
            <option value="asc">오름차순</option>
            <option value="desc">내림차순</option>
          </select>
        </div>

        <div class="mb-3 ml-auto">
          <button class="btn btn-primary" @click="searchVideoList">정렬</button>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-md-4" v-for="video in filteredVideos" :key="video.id">
        <div class="card mb-4">
          <img :src="video.thumbnail" class="card-img-top" alt="Thumbnail">
          <div class="card-body">
            <h5 class="card-title clickable-text" @click="showVideoDetail(video.video_id)">
              <!-- 제목이 두 줄 이상이면 생략 처리 -->
              <span
                style="display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; text-align: center; font-size: 1.5rem; ">
                {{ emojify(video.title) }}
              </span>
            </h5>
            <p class="card-text"><strong>{{ video.channel_name }}</strong></p>
            <p class="card-text"><strong>파트</strong> {{ video.part }} | <strong>조회수</strong> {{ video.view_cnt }}</p>
            <div class="d-flex align-items-center">
              <span>{{ video.like_cnt }}</span>
              <span v-if="video.is_user_liked == 1">
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
import router from "@/router";
import { emojify } from '@twuni/emojify';

const store = useVideoStore();

const selectedPart = ref(null);

const user = JSON.parse(sessionStorage.getItem("loginUser"));

onMounted(() => {
  store.getVideoList(user.user_id);
});

const filterVideos = (part) => {
  selectedPart.value = part;
};

const filteredVideos = computed(() => {
  if (!selectedPart.value) {
    return store.videoList;
  }
  return store.videoList.filter((video) => video.part === selectedPart.value);
});

const showVideoDetail = function (videoId) {
  if (videoId) {
    router.push({ name: 'videoDetail', params: { videoId: videoId } });
  } else {
    console.error("Invalid videoId:", videoId);
  }
}

const searchInfo = ref({
  orderBy: 'title',
  orderByDir: 'asc'
})

const searchVideoList = function () {
  store.searchVideoList(searchInfo.value)
}
</script>

<style scoped>
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
  color : #bfd49e;
  background-color: white;
  border-color: #bfd49e;
}

.btn-secondary {
  background-color: #6c757d;
  border-color: #6c757d;
}

.btn-secondary:hover {
  color: #6c757d;
  background-color: white;
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
