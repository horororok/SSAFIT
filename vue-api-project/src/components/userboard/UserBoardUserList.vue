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
            <!-- <button @click="showUserBoardDetail(user.user_id)" class="btn btn-primary">
              팔로우 버튼으로 수정
            </button> -->
            <!-- <button>
              팔로우
            </button> -->
            <!-- <span @click="follow" class="btn-link" style="cursor: pointer;">
            {{ followbutton }}
          </span> -->
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
import { useUserStore } from '@/stores/user';
import { onMounted, ref, computed } from "vue";
import router from "@/router";

const store = useUserStore();

const friends = ref(store.friends);
const userId = store.loginUserObj.user_id;

onMounted(() => {
    //페이지 로드시 유저 목록 가져옴
    // console.log("유저아이디:" ,userId);
    store.getFriendList(userId);
});

const user_to_id = ref(store.friends.user_id); 
const isfollowed = ref(store.friends.user_follow_cnt);

const follow = function(input_userId) {

  if(isfollowed.value === 0){
    isfollowed.value === 1
  }else{
    isfollowed.value === 0
  }

  const followInfo = {
    user_from_id : userId.value,
    user_to_id : input_userId,
  }

  console.log("나 : ", followInfo.user_from_id);
  console.log("너 : ", followInfo.user_to_id);

  if(isfollowed.value === 1){
    store.follow(followInfo);
  }else if(isfollowed.value === 0){ 
    store.unfollow(followInfo);
  }
}

// const followState = ref(false);
// watch(() => store.friends.user_follow_cnt, (newValue) => {
  
// })

// const followbutton = computed(() => {
//   return isfollowed.value? "언팔로우" : "팔로우";
// })


</script>

<style scoped>

</style>