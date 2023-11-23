<template>
  <div class="container mt-5">
    <div class="row">
      <div class="col-md-6">
        <h2 class="text-center">회원가입</h2>
        <form class="mt-4">
      <div class="mb-3">
        <label for="id" class="form-label">아이디</label>
        <div class="input-group">
          <input
            type="text"
            class="form-control"
            id="id"
            v-model="id"
          />
          <button class="btn btn-outline-secondary" type="button" @click="idCheck">중복확인</button>
        </div>
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">비밀번호</label>
        <input
          type="password"
          class="form-control"
          id="password"
          v-model="password"
        />
      </div>
      <div class="mb-3">
        <label for="password2" class="form-label">비밀번호 확인</label>
        <input
          type="password"
          class="form-control"
          id="password2"
          v-model="password2"
        />
      </div>
      <div class="mb-3">
        <label for="name" class="form-label">이름</label>
        <input
          type="text"
          class="form-control"
          id="name"
          v-model="name"
        />
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">이메일</label>
        <input
          type="email"
          class="form-control"
          placeholder="이메일을 입력해주세요."
          id="email"
          v-model="email"
        />
      </div>
      <div class="mb-3">
        <label for="nickname" class="form-label">닉네임</label>
        <div class="input-group">
          <input
            type="text"
            class="form-control"
            id="nickname"
            v-model="nickname"
          />
          <button class="btn btn-outline-secondary" type="button" @click="nicknameCheck">중복확인</button>
        </div>
      </div>
      <button type="button" class="btn btn-success" @click="regist">회원 가입</button>
        </form>
      </div>
      <div class="col-md-6">
        <div class="cat-background"></div>
      </div>
    </div>
  </div>
</template>


<script setup>
import { ref } from "vue";
import axios from "axios";
import { useUserStore } from "@/stores/user";

const store = useUserStore();

const id = ref("");
const password = ref("");
const password2 = ref("");
const name = ref("");
const email = ref("");
const nickname = ref("");
const users = ref([]);

const idChecked = ref(false);
const nicknameChecked = ref(false);

const getUserList = () => {
  const API_URL = `http://localhost:8080/api-user/users`;
  axios({
    url: API_URL,
    method: "GET",
  })
    .then((res) => {
      users.value = Array.isArray(res.data) ? res.data : [];
    })
    .catch((err) => {
      console.log(err);
    });
};

//아이디 중복 체크
const idCheck = () => {
  const user = {
    id: id.value,
  };


  if (users.value.some((u) => u.id === user.id)) {
    alert("이미 사용 중인 아이디입니다.");
  } else {
    alert("사용 가능한 아이디입니다.");
    idChecked.value = true;
  }
};

//닉네임 중복 체크
const nicknameCheck = () => {
  const user = {
    nickname : nickname.value,
  };

  if (users.value.some((u) => u.nickname === user.nickname)) {
    alert("이미 사용 중인 닉네임입니다.");
  }else{
    alert("사용 가능한 닉네임입니다.");
    nicknameChecked.value = true;
  }
};


//유효성 검사
const regist = () => {
  const user = {
    id: id.value,
    password: password.value,
    name: name.value,
    email: email.value,
    nickname: nickname.value,
  };

  if (!email.value.includes('@')) {
    alert("올바른 이메일 형식이 아닙니다.");
    return;
  }

  let reg = /^(?=.*[@$!%*#?&]).{8,}$/;
  if (!reg.test(password.value)) {
    alert("비밀번호는 8자리 이상이며 특수문자를 1개 이상 포함해야 합니다.");
    return;
  }

  if (password.value !== password2.value) {
    alert("비밀번호가 일치하지 않습니다.");
    return;
  }

  if(!idChecked.value){
    alert("아이디 중복 확인을 해주세요.");
    return;
  }

  if(!nicknameChecked.value){
    alert("닉네임 중복 확인을 해주세요.");
    return;
  }

  store.createUser(user);
};

getUserList();
</script>


<style scoped>
.cat-background {
  object-fit: cover;
  width: 100%;
  height: auto;
  background-image: url("@/assets/img/registercat.jpg");
  background-size: cover;
  border-radius: 10px;
  min-height: 100%; /* 최소 높이를 100%로 설정 */
}


  .container {
    position: relative;
  }

  .position-relative {
    position: relative;
  }

  .col-md-6 {
    padding: 10; /* 컬럼 간격 제거 */
  }

 

  /* 스타일 추가 */
  body {
    background-color: #f8f9fa;
  }

  /* form {
    background-color: rgba(255, 255, 255, 0.8); 
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  } 
  */

  .btn-outline-secondary {
    border-radius: 0;
  }
</style>