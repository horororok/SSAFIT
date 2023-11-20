<template>
    <div class="container mt-4">
    <h4>내 추가 정보</h4>
    <div class="card">
      <div class="card-body">
        <div class="mb-2">
            <strong>나이 :</strong> {{ store.myPageUser.age }}
        </div>
        <div class="mb-2">
            <strong>성별 :</strong> {{ gender }}
        </div>
        <div class="mb-2">
            <strong>자기소개 :</strong> {{ store.myPageUser.self_intro }}
        </div>
        <div class="mb-2">
            <strong>주소 :</strong> {{ store.myPageUser.address }}
        </div>
        <div class="mb-2">
            <strong>선호 스포츠 :</strong> {{ store.myPageUser.fav_sport }}
        </div>
      </div>
      <button  @click="insertUser">추가 정보 등록</button>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { useRoute, useRouter } from 'vue-router';
import { onMounted, computed } from 'vue';

const store = useUserStore();

const route = useRoute();
const router = useRouter(); 


onMounted(() => {
    // console.log(route.params.userId);
    store.getmyPageUser(route.params.userId);

});

const gender = computed(() => {
    return store.myPageUser.gender === 1 ? "여자" : "남자";
})

const insertUser = function () {
  router.push({ name: 'myregist' });
};

</script>

<style scoped>

</style>