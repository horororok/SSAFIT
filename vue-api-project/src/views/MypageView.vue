<template>
  <div>
      <MypageSideNav /> 
  </div>
</template>

<script setup>
import {useRoute} from 'vue-router';
import { useUserStore } from '@/stores/user';
import { onMounted } from 'vue';
import MypageSideNav from '@/components/mypage/MypageSideNav.vue';

const store = useUserStore();

const route = useRoute();

onMounted(() => {
    store.getmyPage(route.params.userId);
});

</script>

<style scoped>

</style>