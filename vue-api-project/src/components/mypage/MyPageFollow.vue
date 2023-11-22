<template>
 <div>
    <h2>팔로잉 목록</h2>
    <hr>
    <div class="row justify-content-center">
      <div v-for="user in following" :key="user.user_id" class="col-md-2 mb-4">
        <div class="card">
      <div class="card-body">
        <h5 class="card-title">{{ user.nickname }}</h5>
        <div class="mb-2">
          <strong>나이 :</strong> {{ user.age }}
        </div>
        <div class="mb-2">
          <strong>성별 :</strong> {{ user.gender === 1 ? "여자" : "남자" }}
        </div>
        <div class="mb-2">
          <strong>자기소개 :</strong> {{ user.self_intro }}
        </div>
        <div class="mb-2">
          <strong>주소 :</strong> {{ user.address }}
        </div>
        <div class="mb-2">
          <strong>선호 스포츠 :</strong> {{ user.fav_sport }}
        </div>
        <div class="mb-2" >
          <!-- 이미지를 보여줄 img 태그 -->
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

    <h2>팔로워 목록</h2>
    <hr>
    <div class="row justify-content-center">
      <div v-for="user in follower" :key="user.user_id" class="col-md-2 mb-4">
        <div class="card">
      <div class="card-body">
        <h5 class="card-title">{{ user.nickname }}</h5>
        <div class="mb-2">
          <strong>나이 :</strong> {{ user.age }}
        </div>
        <div class="mb-2">
          <strong>성별 :</strong> {{ user.gender === 1 ? "여자" : "남자" }}
        </div>
        <div class="mb-2">
          <strong>자기소개 :</strong> {{ user.self_intro }}
        </div>
        <div class="mb-2">
          <strong>주소 :</strong> {{ user.address }}
        </div>
        <div class="mb-2">
          <strong>선호 스포츠 :</strong> {{ user.fav_sport }}
        </div>
        <div class="mb-2" >
          <!-- 이미지를 보여줄 img 태그 -->
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
</script>

<style scoped>




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
}

.card-body {
  text-align: center;
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
  border: 2px dashed #ccc; /* 이미지 없을 때의 대체 테두리 스타일 */
  border-radius: 50%; /* 원형 프로필 이미지를 위한 테두리 반지름 */
  width: 100%;
  height: auto;
  text-align: center;
  color: #ccc;
}
</style>