<template>
    <div>
    <h2 class="mt-3">마이페이지</h2>
    <div class="my-4">
      <div class="mb-3">{{ store.mypage.name }} 님의 마이페이지 ^__^</div>

      <nav class="mb-4">
        <router-link
          :to="{ name: 'detail', params: { userId: store.loginUserObj.user_id } }"
          class="btn btn-outline-primary me-2"
        >
          상세 정보
        </router-link>
        <!-- <router-link
          :to="{ name: 'myregist', params: { userId: store.loginUserObj.user_id } }"
          class="btn btn-outline-primary me-2"
        >
          추가 정보 등록
        </router-link> -->
        <router-link
          :to="{ name: 'userdetail', params: { userId: store.loginUserObj.user_id } }"
          class="btn btn-outline-primary me-2"
        >
          추가 정보
        </router-link>
        <!-- <router-link class="btn btn-outline-primary me-2">찜한 영상 보기</router-link>
        <router-link class="btn btn-outline-primary me-2">팔로워 / 팔로잉</router-link> -->
      </nav>
      <hr />
      <router-view />
    </div>
  </div>
    

    
</template>

<script setup>
import {useRoute} from 'vue-router'
import {useUserStore} from '@/stores/user';
import {ref, onMounted} from 'vue';

const store = useUserStore()
const user = ref({});

const route = useRoute()

onMounted(() => {
    store.getmyPage(route.params.userId);
});





</script>

<style scoped>

</style>