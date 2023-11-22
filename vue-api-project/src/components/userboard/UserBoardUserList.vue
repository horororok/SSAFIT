<template>
  <div>
    <h4>유저 목록</h4>
    <div class="row justify-content-center">
      <div v-for="user in friends" :key="user.user_id" class="col-md-2 mb-4">
        <div class="card border" style="background-color: #bfd49e; padding: 10px; border-radius: 10px;">
          <!-- 임시 이미지 (이미지 주소를 실제 이미지 파일 경로로 변경)-->
          <img :src="`${user.profile_image}`" alt="프로필 이미지" class="card-img-top" style="border-radius: 50%;">
          <div class="card-body text-center">
            <div>
              <h5 class="card-title" style="color: #fff;">{{ user.nickname }}</h5>
              <p class="card-text" style="color: #fff;">{{ user.self_intro }}</p>
              <p class="card-text" style="color: #fff;">선호 스포츠: {{ user.fav_sport }}</p>
              <p class="card-text" style="color: #fff;">{{ user.gender === 1 ? "여자" : "남자" }}</p>
              <p class="card-text" style="color: #fff;">{{ user.age }}세</p>
              <p class="card-text" style="color: #fff;">{{ user.address }}</p>
            </div>
            <button @click="follow(user.user_id)" class="btn"
              style="cursor: pointer; background-color: #fff; color: #bfd49e; border: 1px solid #fff; border-radius: 5px; padding: 5px 10px;">
              {{ user.user_follow_cnt === 1 ? "언팔로우" : "팔로우" }}
            </button>
            
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { onMounted, ref, computed } from "vue";
import { storeToRefs } from "pinia";
import router from "@/router";

const store = useUserStore();

const friends = computed(()=>store.friends)

const userId = store.loginUserObj.user_id;

onMounted(() => {
  console.log(userId);
  store.getFriendList(userId);
});

const me = JSON.parse(sessionStorage.getItem("loginUser"));

const follow = function (input_userId) {
  const followInfo = {
    user_from_id: me.user_id,
    user_to_id: input_userId,
  };

  if (store.userBoard.user_follow_cnt === 0) {
    store.userBoard.user_follow_cnt = 1;
    store.follow(followInfo);
  } else {
    store.userBoard.user_follow_cnt = 0;
    store.unfollow(followInfo);
  }

};
</script>


<style scoped></style>
