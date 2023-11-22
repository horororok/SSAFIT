<template>
  <div>
    <h4>유저 목록</h4>
    <div>
      <!-- 필터링 옵션 -->
      <div>
        <button @click="filterUsers('축구')">축구</button>
        <button @click="filterUsers('농구')">농구</button>
        <button @click="filterUsers('야구')">야구</button>
        <button @click="filterUsers('배구')">배구</button>
      </div>

      <div>
        <button @click="filterUsers('여자')">여자</button>
        <button @click="filterUsers('남자')">남자</button>
      </div>

      <div>
        <button @click="filterUsers('10대')">10대</button>
        <button @click="filterUsers('20대')">20대</button>
        <button @click="filterUsers('30대')">30대</button>
        <button @click="filterUsers('40대')">40대</button>
        <button @click="filterUsers('50대')">50대</button>
        <button @click="filterUsers('60대')">60대</button>
        <button @click="filterUsers('70이상')">70이상</button>
      </div>

      <div>
        <button @click="filterUsers('서울특별시')">서울</button>
        <button @click="filterUsers('경기도')">경기</button>
        <button @click="filterUsers('인천광역시')">인천</button>
        <button @click="filterUsers('강원도')">강원</button>
        <button @click="filterUsers('충청북도')">충북</button>
        <button @click="filterUsers('충청남도')">충남</button>
        <button @click="filterUsers('대전광역시')">대전</button>
        <button @click="filterUsers('경상북도')">경북</button>
        <button @click="filterUsers('경상남도')">경남</button>
        <button @click="filterUsers('대구광역시')">대구</button>
        <button @click="filterUsers('전라북도')">전북</button>
        <button @click="filterUsers('전라남도')">전남</button>
        <button @click="filterUsers('광주광역시')">광주</button>
        <button @click="filterUsers('부산광역시')">부산</button>
        <button @click="filterUsers('제주특별자치도')">제주</button>
        <button @click="filterUsers('울산광역시')">울산</button>
        <button @click="filterUsers('세종특별자치시')">세종</button>
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
import { onMounted, computed } from "vue";
import { storeToRefs } from "pinia";
import router from "@/router";
import { ref } from "vue";

const store = useUserStore();

// 필터링에 사용될 상태
const selectedSport = ref("");
const selectedGender = ref("");
const selectedAge = ref("");
const selectedAddress = ref("");

// friends를 ref로 감싸기
// const { friends } = storeToRefs(store);
const friends = computed(() => store.friends);

// 친구 목록을 필터링하는 computed 속성
const filteredFriends = computed(() => {
  return friends.value.filter(user => {
    // 필터링 조건 추가
    const sportFilter = !selectedSport.value || user.fav_sport === selectedSport.value;
    const genderFilter = !selectedGender.value || user.gender.toString() === selectedGender.value;
    const ageFilter = !selectedAge.value || user.age.toString() === selectedAge.value;
    const addressFilter = !selectedAddress.value || user.address.includes(selectedAddress.value);

    // 모든 필터 조건이 true이면 포함시킴
    return sportFilter && genderFilter && ageFilter && addressFilter;
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
const filterUsers = function (value) {
  // 각 버튼에 따라 필터링에 사용될 상태 업데이트
  switch (value) {
    case '축구':
    case '농구':
    case '야구':
    case '배구':
      selectedSport.value = value;
      break;
    case '여자':
    case '남자':
      selectedGender.value = value;
      break;
    case '10대':
    case '20대':
    case '30대':
    case '40대':
    case '50대':
    case '60대':
    case '70이상':
      selectedAge.value = value;
      break;
    default:
      selectedAddress.value = value;
      break;
  }
};
</script>

<style scoped></style>
