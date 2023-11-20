<template>
    <div class="container-fluid">
      <div class="row">
        <!-- 사이드바 -->
        <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
        <div class="position-sticky">
          <router-link :to="{ name: 'detail', params: { userId: store.loginUserObj.user_id } }" class="text-dark text-decoration-none mb-2">
            상세 정보
          </router-link>
          <hr class="mb-2 mt-2">
          <router-link :to="{ name: 'userdetail', params: { userId: store.loginUserObj.user_id } }" class="text-dark text-decoration-none mb-2">
            추가 정보
          </router-link>
          <hr class="mb-2 mt-2">
          <router-link :to="{ name: 'zzim', params: { userId: store.loginUserObj.user_id } }" class="text-dark text-decoration-none mb-2">
            찜한 영상 보기
          </router-link>
          <hr class="mb-2 mt-2">
          <router-link :to="{ name: 'follow', params: { userId: store.loginUserObj.user_id } }" class="text-dark text-decoration-none">
            팔로우 관리
          </router-link>
        </div>
      </nav>
  
        <!-- 주요 내용 -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
          <router-view />
        </main>
      </div>
    </div>
  </template>
  
  <script setup>
  import { useUserStore } from '@/stores/user';
  
  const store = useUserStore();
  </script>
  
  <style scoped>
  #sidebar {
    height: 100vh;
    position: -webkit-sticky;
    position: sticky;
    top: 0;
  }
  
  .btn-secondary {
    background-color: #6c757d;
    color: #fff;
  }
  
  .btn-secondary:hover {
    background-color: #5a6268;
    color: #fff;
  }
  </style>
  