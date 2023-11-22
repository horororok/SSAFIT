<template>
  <div class="container-fluid">
    <div class="row">
      <!-- 사이드바 -->
      <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block sidebar">
        <div class="position-sticky">
          <router-link :to="{ name: 'detail', params: { userId: store.loginUserObj.user_id } }" class="nav-link text-light">
            상세 정보
          </router-link>
          <hr class="mb-2 mt-2">
          <router-link :to="{ name: 'userdetail', params: { userId: store.loginUserObj.user_id } }" class="nav-link text-light">
            추가 정보
          </router-link>
          <hr class="mb-2 mt-2">
          <router-link :to="{ name: 'zzim', params: { userId: store.loginUserObj.user_id } }" class="nav-link text-light">
            찜한 영상 보기
          </router-link>
          <hr class="mb-2 mt-2">
          <router-link :to="{ name: 'follow', params: { userId: store.loginUserObj.user_id } }" class="nav-link text-light">
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
  background-color: #bfd49e;
}

.nav-link {
  color: #fff;
}

.nav-link:hover {
  background-color: #6c757d;
  color: #fff;
}
</style>
