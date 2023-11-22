<template>
  <div>
    <h4>유저 목록</h4>
    <div>
      <!-- 필터링 옵션 -->
      <div>
        <button class="filter-button" @click="filterUsers('축구')">축구</button>
        <button class="filter-button" @click="filterUsers('농구')">농구</button>
        <button class="filter-button" @click="filterUsers('야구')">야구</button>
        <button class="filter-button" @click="filterUsers('배구')">배구</button>
      </div>

      <div>
        <button class="filter-button" @click="filterUsers(1)">여자</button>
        <button class="filter-button" @click="filterUsers(0)">남자</button>
      </div>

    </div>

    <div class="row justify-content-center">
      <div v-for="user in filteredFriends" :key="user.user_id" class="col-md-2 mb-4">
        <!-- 카드 내용 -->
        <div class="card border" style="background-color: #bfd49e; padding: 10px; border-radius: 10px;">
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
            <button @click="follow(user.user_id)" class="btn follow-button"
              :class="{ 'followed': user.user_follow_cnt === 1 }">
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
import { onMounted, computed } from "vue";
import { storeToRefs } from "pinia";
import router from "@/router";
import { ref } from "vue";

const store = useUserStore();

// 필터링에 사용될 상태
const selectedSport = ref("");
const selectedGender = ref("");

// friends를 ref로 감싸기
const friends = computed(() => store.friends);

// 친구 목록을 필터링하는 computed 속성
const filteredFriends = computed(() => {
  return friends.value.filter(user => {
    // 필터링 조건 추가
    const sportFilter = !selectedSport.value || user.fav_sport === selectedSport.value;
    const genderFilter = !selectedGender.value || user.gender.toString() === selectedGender.value;
    //   console.log("selectedGender.value:", selectedGender.value);
    // console.log("user.gender.toString():", user.gender.toString());
    // console.log("Filter result:", sportFilter, genderFilter);
    // 모든 필터 조건이 true이면 포함시킴
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

// 버튼 클릭 시 필터링 적용 함수
const filterUsers = function (filtering) {
  console.log("Filtering:", filtering);
  // 각 버튼에 따라 필터링에 사용될 상태 업데이트
  switch (filtering) {
    case '축구':
    case '농구':
    case '야구':
    case '배구':
      selectedSport.value = filtering;
      selectedGender.value = ""; // 성별 옵션 초기화
      break;
    case 1:
    case 0:
      selectedGender.value = filtering.toString();
      selectedSport.value = ""; // 스포츠 옵션 초기화
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
</style>