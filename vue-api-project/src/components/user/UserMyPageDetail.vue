<template>
     <div>
    <h2 class="mt-3">상세 정보</h2>
    <div class="my-3">
      <div class="mb-2">
        <strong>ID:</strong> {{ store.mypage.id }}
      </div>
      <div class="mb-2">
        <strong>Password:</strong> {{ store.mypage.password }}
      </div>
      <div class="mb-2">
        <strong>Name:</strong> {{ store.mypage.name }}
      </div>
      <div class="mb-2">
        <strong>Email:</strong> {{ store.mypage.email }}
      </div>
      <div class="mb-4">
        <strong>Nickname:</strong> {{ store.mypage.nickname }}
      </div>

      <button class="btn btn-primary" @click="updateUser">회원정보 수정</button>
    </div>
  </div>
</template>

<script setup>
import {useRoute, useRouter} from 'vue-router'
import {useUserStore} from '@/stores/user';
import {ref, onMounted} from 'vue';

const store = useUserStore();
const user = ref({});

const route = useRoute();
const router = useRouter();

onMounted(() => {
    store.getmyPage(route.params.userId);
});

const updateUser = function () {
  router.push({ name: 'myupdate' });
};


</script>

<style scoped>

</style>