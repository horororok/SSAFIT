<template>
  <div class="search">
      <div>
          <label>정렬 기준 :</label>
          <select v-model="searchInfo.orderBy">
              <option value='none'>없음</option>
              <option value="title">제목</option>
              <option value="view_cnt">조회수</option>
          </select>
      </div>
      <div>
          <label>정렬 방향 :</label>
          <select v-model="searchInfo.orderByDir" @change="sortVideoList">
              <option value="asc">오름차순</option>
              <option value="desc">내림차순</option>
          </select>
      </div>
      <div>
          <button @click="sortVideoList">정렬</button>
      </div>
      <ul>
    <li v-for="video in sortedVideoList" :key="video.id">
      {{ video.title }}
    </li>
  </ul>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useVideoStore } from '@/stores/video'

const store = useVideoStore()
const searchInfo = ref({
  key: 'none',
  word: '',
  orderBy: 'none',
  orderByDir: 'asc'
})


</script>

<style scoped>
.search {
  display: flex;
}
</style>

