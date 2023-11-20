<template>
  <div>
    <h4>유저 목록</h4>
    <hr>
    <div class="row justify-content-center">
      <div v-for="user in UserBoardList" :key="user.id" class="col-md-2 mb-4">
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
            <button @click="showUserBoardDetail(user.user_id)" class="btn btn-primary">
              자세히 보기
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { onMounted, ref, computed } from "vue";
import router from "@/router";

const store = useUserStore();

const UserBoardList = ref(store.userBoardList);

onMounted(() => {
    //페이지 로드시 유저 목록 가져옴
    store.getUserBoardList();
});



const showUserBoardDetail = function(userId){

  console.log("userId:", userId);
    if(userId){
        router.push({name : 'userboarddetail', params: {userboardId : userId}})
    }else{
        console.error("Invalid userId:", userId);
    }
}


</script>

<style scoped>

</style>