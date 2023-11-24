<template>
  <div class="container mt-4">
    <div class="d-flex flex-wrap">
      <span class="col-md-auto">
        <div class="mb-3">
          <label class="form-label" style="font-size: 10px; color: #bfd49e;">정렬 기준:</label>
          <select class="form-select" v-model="searchInfo.orderBy" style="font-size: 10px; background-color: #fff;">
            <option value="none">없음</option>
            <option value="title">제목</option>
            <option value="view_cnt">조회수</option>
          </select>
        </div>
      </span>

      <span class="col-md-auto">
        <div class="mb-3">
          <label class="form-label" style="font-size: 10px; color: #bfd49e;">정렬 방향:</label>
          <select class="form-select" v-model="searchInfo.orderByDir" style="font-size: 10px; background-color: #fff;">
            <option value="asc">오름차순</option>
            <option value="desc">내림차순</option>
          </select>
        </div>
      </span>

      <span class="col-md-auto ml-auto">
        <div class="mb-3">
          <button class="btn btn-primary" style="font-size: 10px; background-color: #bfd49e; border-color: #bfd49e;" @click="searchVideoList">정렬</button>
        </div>
      </span>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useVideoStore } from '@/stores/video'
  
const store = useVideoStore()
const searchInfo = ref({
  orderBy: 'none',
  orderByDir: 'asc'
})

const searchVideoList = function () {
  store.searchVideoList(searchInfo.value)
}
</script>

<style scoped>
</style>
