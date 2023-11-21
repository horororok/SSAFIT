<template>
    <div>
        <h2>팔로잉 목록</h2>
        <hr>
        <div class="row justify-content-center">
      <div v-for="user in store.following" :key="user.user_id" class="col-md-2 mb-4">
        <div class="card border">
          <!-- 임시 이미지 (이미지 주소를 실제 이미지 파일 경로로 변경해야 합니다) -->
          <img src="https://via.placeholder.com/150" alt="프로필 이미지" class="card-img-top" style="border-radius: 50%;">
          <div class="card-body">
            <h5 class="card-title">닉네임: {{ user.nickname }}</h5>
            <p class="card-text">자기소개: {{ user.self_intro }}</p>
            <p class="card-text">선호 스포츠: {{ user.fav_sport }}</p>
            <p class="card-text">성별: {{ user.gender === 1 ? "여자" : "남자"  }}</p>
            <p class="card-text">나이: {{ user.age }}</p>
            <p class="card-text">주소: {{ user.address }}</p>
          <span @click="follow(user.user_id)" class="btn-link" style="cursor: pointer;">
            {{ user.user_follow_cnt === 1? "언팔로우" : "팔로우" }}
          </span> 
          </div>
        </div>
      </div>
    </div>

    <hr>

    <h2>팔로워 목록</h2>
        <hr>
        <div class="row justify-content-center">
      <div v-for="user in store.follower" :key="user.user_id" class="col-md-2 mb-4">
        <div class="card border">
          <!-- 임시 이미지 (이미지 주소를 실제 이미지 파일 경로로 변경해야 합니다) -->
          <img src="https://via.placeholder.com/150" alt="프로필 이미지" class="card-img-top" style="border-radius: 50%;">
          <div class="card-body">
            <h5 class="card-title">닉네임: {{ user.nickname }}</h5>
            <p class="card-text">자기소개: {{ user.self_intro }}</p>
            <p class="card-text">선호 스포츠: {{ user.fav_sport }}</p>
            <p class="card-text">성별: {{ user.gender === 1 ? "여자" : "남자"  }}</p>
            <p class="card-text">나이: {{ user.age }}</p>
            <p class="card-text">주소: {{ user.address }}</p>
          <span @click="follow(user.user_id)" class="btn-link" style="cursor: pointer;">
            {{ user.user_follow_cnt === 1? "언팔로우" : "팔로우" }}
          </span> 
          </div>
        </div>
      </div>
    </div>
    </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useUserStore } from '@/stores/user';

const store = useUserStore();

onMounted(() => {
    const userId = store.loginUserObj.user_id;

    store.getFollowerList(userId);
    store.getFollowingList(userId);
})

</script>

<style scoped>

</style>