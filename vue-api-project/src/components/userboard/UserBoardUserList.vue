<template>
  <div>
    <div>
      <div>
        &nbsp;<button class="filter-button" @click="filterUsers('축구')">축구</button>
        <button class="filter-button" @click="filterUsers('농구')">농구</button>
        <button class="filter-button" @click="filterUsers('야구')">야구</button>
        <button class="filter-button" @click="filterUsers('배구')">배구</button>
      </div>
      <div>
        &nbsp;<button class="filter-button" @click="filterUsers(1)">여자</button>
        <button class="filter-button" @click="filterUsers(0)">남자</button>
      </div>
    </div>
    <div v-if="friends.length === 0">
      <p>등록된 유저 정보가 없습니다.</p>
    </div>

    <div v-else>
      <div class="row justify-content-center">
        <div v-for="user in filteredFriends" :key="user.user_id" class="col-md-2 mb-4">
          <div class="card">
            <div class="card-body">
              <div class="profile-title mb-3">
                <h5 class="card-title font-weight-bold">{{ user.nickname }}</h5>
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
              <div class="mb-2" v-if="user.profile_image">
                <div class="profile-image-frame">
                  <img :src="user.profile_image" alt="프로필 이미지" class="profile-image">
                </div>
              </div>
              <button @click="follow(user.user_id)" class="btn follow-button"
                :class="{ 'followed': user.user_follow_cnt === 1 }">
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
import { useUserStore } from "@/stores/user";
import { onMounted, computed } from "vue";
import { ref } from "vue";

const store = useUserStore();

const selectedSport = ref("");
const selectedGender = ref("");

const friends = computed(() => store.friends);

const filteredFriends = computed(() => {
  return friends.value.filter(user => {

    const sportFilter = !selectedSport.value || user.fav_sport === selectedSport.value;
    const genderFilter = !selectedGender.value || user.gender.toString() === selectedGender.value;
    
    return sportFilter && genderFilter;

  });
});

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

const filterUsers = function (filtering) {
  console.log("Filtering:", filtering);
  switch (filtering) {
    case '축구':
    case '농구':
    case '야구':
    case '배구':
      selectedSport.value = filtering;
      selectedGender.value = ""; 
      break;
    case 1:
    case 0:
      selectedGender.value = filtering.toString();
      selectedSport.value = ""; 
      console.log(selectedGender.value)
      break;
  }
};
</script>

<style scoped>
.filter-button {
  cursor: pointer;
  background-color: #bfd49e;
  color: #fff;
  border: none;
  border-radius: 5px;
  margin-right: 5px;
  margin-bottom: 5px;
  padding: 5px 10px;
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
  width: 300px;
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
</style>