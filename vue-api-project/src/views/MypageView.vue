<template>
  <div>
    <!-- <h2 class="mt-3">{{ store.mypage.name }} 님의 마이페이지 ^__^</h2> -->
    <!-- <h2 class="mt-3"></h2> -->
    <!-- <div class="my-4"> -->
      <MypageSideNav /> 
    <!-- </div> -->
  </div>
</template>

<script setup>
import {useRoute} from 'vue-router';
import { useUserStore } from '../stores/user';
import {ref, onMounted} from 'vue';
import MypageSideNav from '@/components/mypage/MypageSideNav.vue';

const store = useUserStore();

const route = useRoute();

onMounted(() => {
    store.getmyPage(route.params.userId);
});

</script>

<style scoped>

</style>