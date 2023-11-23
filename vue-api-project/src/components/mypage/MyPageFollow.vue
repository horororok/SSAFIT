<template>
 <div>
    <div v-if="follower.length === 0 && following.length === 0" class="empty-message">
      <p>아직 친구가 없습니다. </p>
      <div><img src="@/assets/img/followcat.jpg" alt="No FOLLOW Users"></div>
      <button @click="goUserBoard" class="btn btn-success btn-move-to-board">이용자게시판으로 이동</button>
    </div>
    <div v-else>
    <h5>팔로잉</h5>
    <hr>
    <div class="row justify-content-center">
      <div v-for="user in following" :key="user.user_id" class="col-md-2 mb-4">
      <div class="card">
      <div class="card-body">
        <div class="profile-title mb-3">
          <h5 class="card-title font-weight-bold" >{{ user.nickname }}</h5>
        </div>
        <div class="mb-2">
          {{ user.age }}세
        </div>
        <div class="mb-2">
          {{ user.gender === 1 ? "여자" : "남자" }}
        </div>
        <div class="mb-2">
          {{ user.self_intro }}
        </div>
        <div class="mb-2">
          {{ user.address }}
        </div>
        <div class="mb-2">
          <strong>선호 스포츠 :</strong> {{ user.fav_sport }}
        </div>
        <div class="mb-2" >
          <div class="profile-image-frame">
            <img :src="`${user.profile_image}`" alt="프로필 이미지" class="profile-image" >
          </div>
        </div>
        <button @click="follow(user.user_id)" class="btn follow-button">
              {{ user.user_follow_cnt === 1 ? "언팔로우" : "팔로우" }}
        </button>
        </div>
      </div>
      </div>
    </div>

    <hr>

    <h5>팔로워</h5>
    <hr>
    <div class="row justify-content-center">
      <div v-for="user in follower" :key="user.user_id" class="col-md-2 mb-4">
      <div class="card">
      <div class="card-body">
        <div class="profile-title mb-3">
          <h5 class="card-title font-weight-bold" >{{ user.nickname }}</h5>
        </div>
        <div class="mb-2">
          {{ user.age }}세
        </div>
        <div class="mb-2">
          {{ user.gender === 1 ? "여자" : "남자" }}
        </div>
        <div class="mb-2">
          {{ user.self_intro }}
        </div>
        <div class="mb-2">
          {{ user.address }}
        </div>
        <div class="mb-2">
          <strong>선호 스포츠 :</strong> {{ user.fav_sport }}
        </div>
        <div class="mb-2" >
          <div class="profile-image-frame">
            <img :src="`${user.profile_image}`" alt="프로필 이미지" class="profile-image" >
          </div>
        </div>
        <button @click="follow(user.user_id)" class="btn follow-button">
              {{ user.user_follow_cnt === 1 ? "언팔로우" : "팔로우" }}
        </button>
        </div>
      </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script setup>
import { onMounted, ref, computed } from 'vue';
import { useUserStore } from '@/stores/user';
import router from '@/router';

const store = useUserStore();

const follower = computed(() => store.follower);
const following = computed(() => store.following);

const isfollowed = ref(store.following.user_follow_cnt);

const follow = function (input_userId) {
  const followInfo = {
    user_from_id: store.loginUserObj.user_id,
    user_to_id: input_userId,
  };

  if (isfollowed.value === 0) {
    isfollowed.value = 1;
    store.follow(followInfo);
  } else {
    isfollowed.value = 0;
    store.unfollow(followInfo);
  }
  router.go(0);
};

onMounted(() => {
  console.log("isfollowed", isfollowed.value);
  const userId = store.loginUserObj.user_id;

  store.getFollowerList(userId);
  store.getFollowingList(userId);
});

const goUserBoard = function() {
  router.push('/userboard');
}
</script>

<style scoped>
.empty-message {
  text-align: center;
  margin: 50px 0;
  padding: 20px;
}

.empty-message p {
  font-size: 18px;
  color: #555;
  margin-bottom: 10px;
}

.empty-message img {
  max-width: 50%;
  height: 350px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  object-fit: cover;
}


.follow-button {
  cursor: pointer;
  background-color: #fff;
  color: #bfd49e;
  border: 1px solid #bfd49e;
  border-radius: 5px;
  padding: 5px 10px;
}

.followed {
  background-color: #bfd49e;
  color: #fff;
}

.card {
  width: 250px;
  margin: 0 auto;
  border: 1px solid #ddd; 
  border-radius: 8px; 
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); 
}

.card-body {
  text-align: center;
}

.profile-title {
  background-color: #9fbf8e;
  border-radius: 8px;
  padding: 10px;
  text-align: center;
}

.card .card-title {
  margin: 0;
  color: #fff; 
}

.card .mb-2 {
  padding: 10px;
  border-bottom: 1px solid #ddd; 
}

.btn {
  margin-top: 10px;
}

.profile-image-frame {
  padding: 10px;
  overflow: hidden;
}

.profile-image {
  width: 100%;
  height: auto;
  border-radius: 70%; 
}

.no-profile-image {
  padding: 10px;
  border: 2px dashed #ccc; 
  border-radius: 50%; 
  width: 100%;
  height: auto;
  text-align: center;
  color: #ccc;
}

.btn-move-to-board {
  margin-top: 15px;
  color: #fff;
  border-radius: 5px;
  padding: 10px 20px;
  cursor: pointer;
  transition: background-color 0.3s;
}


</style>