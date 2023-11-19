<template>
    <div class="container mt-4">
      <div class="row">
        <!-- <div class="col-md-4">
          <div class="mb-3">
            <label class="form-label">검색 기준:</label>
            <select class="form-select" v-model="searchInfo.key">
              <option value="none">없음</option>
              <option value="title">제목</option>
              <option value="content">내용</option>
            </select>
          </div>
          <div class="mb-3">
            <label class="form-label">검색 내용:</label>
            <input type="text" class="form-control" v-model="searchInfo.word" />
          </div>
        </div> -->
  
        <div class="col-md-auto">
          <div class="mb-3">
            <label class="form-label">정렬 기준:</label>
            <select class="form-select" v-model="searchInfo.orderBy">
              <option value="none">없음</option>
              <option value="title">제목</option>
              <option value="view_cnt">조회수</option>
            </select>
          </div>
        </div>
  
        <div class="col-md-auto">
          <div class="mb-3">
            <label class="form-label">정렬 방향:</label>
            <select class="form-select" v-model="searchInfo.orderByDir">
              <option value="asc">오름차순</option>
              <option value="desc">내림차순</option>
            </select>
          </div>
        </div>
  
        <div class="col-md-auto ml-auto">
          <div class="mb-3">
            <label class="form-label invisible">정렬 버튼 위치 조정용 레이블</label>
            <button class="btn btn-primary w-100" @click="searchVideoList">정렬</button>
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
    // key: 'none',
    // word: '',
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
  </style>
  