<template>
    <div class="container mt-5">
    <h2 class="text-center">추가 정보 등록 창</h2>
    <form class="col-md-6 offset-md-3 mt-4">
      <div class="mb-3">
        <label for="age" class="form-label">나이</label>
        <div class="input-group">
          <input
            type="number"
            class="form-control"
            id="age"
            v-model="age"
          />
        </div>
      </div>
      <div class="mb-3">
        <label class="form-label">성별</label>
        <div class="form-check">
          <input
            type="radio"
            class="form-check-input"
            id="female"
            v-model="gender"
            value="female"
          />
          <label class="form-check-label" for="female">여성</label>
        </div>
        <div class="form-check">
          <input
            type="radio"
            class="form-check-input"
            id="male"
            v-model="gender"
            value="male"
          />
          <label class="form-check-label" for="male">남성</label>
        </div>
      </div>
      <div class="mb-3">
        <label for="self_intro" class="form-label">자기소개 한줄</label>
        <input
          type="textarea" 
          class="form-control"
          id="self_intro"
          v-model="self_intro"
        />
      </div>
      <div class="mb-3">
        <label for="address" class="form-label">주소(빼야겠다)</label>
        <input
          type="text"
          class="form-control"
          id="address"
          v-model="address"
        />
      </div>
      <div class="mb-3">
        <label class="form-label">선호 스포츠 1순위</label>
        <div class="form-check">
          <input
            type="radio"
            class="form-check-input"
            id="soccer"
            v-model="fav_sport"
            value="soccer"
          />
          <label class="form-check-label" for="soccer">축구</label>
        </div>
        <div class="form-check">
          <input
            type="radio"
            class="form-check-input"
            id="baseball"
            v-model="fav_sport"
            value="baseball"
          />
          <label class="form-check-label" for="baseball">야구</label>
        </div>
        <div class="form-check">
          <input
            type="radio"
            class="form-check-input"
            id="basketball"
            v-model="fav_sport"
            value="basketball"
          />
          <label class="form-check-label" for="basketball">농구</label>
        </div>
        <div class="form-check">
          <input
            type="radio"
            class="form-check-input"
            id="volleyball"
            v-model="fav_sport"
            value="volleyball"
          />
          <label class="form-check-label" for="volleyball">배구</label>
        </div>
      </div>
      <button type="button" class="btn btn-primary" @click="registMypage">등록</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";
import { useUserStore } from "@/stores/user";

const store = useUserStore();

const age = ref("");
const gender = ref("");
const self_intro = ref("");
const address = ref("");
const fav_sport = ref("");

const registMypage = () => {
    const mypageUser = {
        age : age.value,
        gender : gender.value,
        self_intro : self_intro.value,
        address : address.value,
        fav_sport : fav_sport.value,
        user_id : store.loginUserObj.user_id, //store에서 user_id 가져오기
    };

    // 여자면 1, 남자면 0
    mypageUser.gender = gender.value === "female" ? 1 : 0;
    
    //선호 스포츠 값 변환
    switch (fav_sport.value) {
    case "soccer":
      mypageUser.fav_sport = "축구";
      break;
    case "baseball":
      mypageUser.fav_sport = "야구";
      break;
    case "basketball":
      mypageUser.fav_sport = "농구";
      break;
    case "volleyball":
      mypageUser.fav_sport = "배구";
      break;
  }

    store.signupMypage(mypageUser);
}

//등록했으면 해당 정보 반환해주고 수정 버튼 나오게 하기 추가 

</script>

<style scoped>

</style>