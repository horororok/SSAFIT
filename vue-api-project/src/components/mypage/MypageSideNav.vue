<template>
  <div class="container-fluid">
    <div class="row">
      <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block sidebar">
        <div class="position-sticky">
          <router-link :to="{ name: 'detail', params: { userId: store.loginUserObj.user_id } }" class="nav-link text-light pt-2">
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
  transition: color 0.3s, font-weight 0.3s; 
}

.nav-link:hover {
  color: #9fbf8e;
  font-weight: 900
}
</style>
