<template>
  <div class="container mt-4">
    <div class="card border-0">
      <div class="card-body" style="max-width: 50%; margin: auto;">
        <h2 class="mt-3" style="color: black;">{{ store.mypage.name }}님의 마이페이지 🐾</h2>
        <div class="my-3">
          <img src="@/assets/img/mypagecat.jpg" alt="Cat Image" style="max-width: 100%; height: auto; border-radius: 8px;">
        </div>
        <button class="btn btn-success" @click="updateUser">회원정보 수정</button>
      </div>
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


.card-body {
  text-align: center;
  padding: 20px; /* 원하는 여백 값으로 조절 */
}

.btn-primary {
  color: #fff;
}
</style>