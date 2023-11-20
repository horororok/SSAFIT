<template>
  <div>
    <h2 class="mt-3">마이페이지</h2>
    <div class="my-4">
      <div class="mb-3">{{ store.mypage.name }} 님의 마이페이지 ^__^</div>

      <hr />
      <router-view />
    </div>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
import { useUserStore } from '@/stores/user';
import { ref, onMounted } from 'vue';

const store = useUserStore();
const user = ref({});

const route = useRoute();

onMounted(() => {
  store.getmyPage(route.params.userId);
});
</script>

<style scoped>
</style>
