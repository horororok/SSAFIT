<template>
  <div>
    <h4>유저 목록</h4>
    <hr>
    <div class="row justify-content-center">
      <div v-for="user in friends" :key="user.user_id" class="col-md-2 mb-4">
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


  <!-- 팔로우 언팔로우 할 때 새로고침 한번 해야 버튼 바뀜............... -->
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { onMounted, ref, computed } from "vue";
import router from "@/router";

const store = useUserStore();

const friends = ref(store.friends);
const userId = store.loginUserObj.user_id;

onMounted(() => {
    store.getFriendList(userId);
});


const isfollowed = ref(store.friends.user_follow_cnt);

const me = JSON.parse(sessionStorage.getItem("loginUser"));

const follow = function(input_userId) {

  if(isfollowed.value === 0){
    isfollowed.value = 1
  }else{
    isfollowed.value = 0
  }

  console.log("isfollowedvalue: " , isfollowed.value);
  const followInfo = {
    user_from_id : me.user_id,
    user_to_id : input_userId,
  }

  // console.log("나 : ", followInfo.user_from_id);
  // console.log("너 : ", followInfo.user_to_id);

  if(isfollowed.value === 0){
    store.follow(followInfo);
  }else if(isfollowed.value === 1){ 
    store.unfollow(followInfo);
  }
}




</script>

<style scoped>

</style>