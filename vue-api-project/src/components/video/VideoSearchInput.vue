<template>
  <div class="container mt-4">
    <div class="row">
      <div class="col-md-auto">
        <div class="mb-3">
          <label class="form-label" style="font-size: 10px;">정렬 기준:</label>
          <select class="form-select" v-model="searchInfo.orderBy">
            <option value="none">없음</option>
            <option value="title">제목</option>
            <option value="view_cnt">조회수</option>
          </select>
        </div>
      </div>

      <div class="col-md-auto">
        <div class="mb-3">
          <label class="form-label" style="font-size: 10px;">정렬 방향:</label>
          <select class="form-select" v-model="searchInfo.orderByDir">
            <option value="asc">오름차순</option>
            <option value="desc">내림차순</option>
          </select>
        </div>
      </div>

      <div class="col-md-auto ml-auto">
        <div class="mb-3">
          <button class="btn btn-primary" style="font-size: 10px;" @click="searchVideoList">정렬</button>
        </div>
      </div>
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
.search {
  display: flex;
}

:host {
  max-width: 100px;
  /* 또는 필요한 스타일을 추가하세요 */
}
</style>
