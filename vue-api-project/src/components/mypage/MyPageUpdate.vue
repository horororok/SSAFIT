<template>
  <div class="container mt-3 d-flex flex-column align-items-center">
    <div class="mt-3 text-center">
      <div class="mb-3">
        <label for="password" class="fw-bold">비밀번호</label>
        <input type="password" id="password" v-model="editedpassword" class="form-control">
      </div>
      <div class="mb-3">
        <label for="name" class="fw-bold">이름</label>
        <input type="text" id="name" v-model="editedname" class="form-control">
      </div>
      <div class="mb-3">
        <label for="email" class="fw-bold">이메일</label>
        <input type="text" id="email" v-model="editedemail" class="form-control">
      </div>
      <div class="mb-3">
        <label for="nickname" class="fw-bold">닉네임</label>
        <div class="input-group">
          <input type="text" id="nickname" v-model="editednickname" class="form-control">
          <button class="btn btn-outline-secondary" @click="nicknameCheck">중복확인</button>
        </div>
      </div>
      <div>
        <button class="btn btn-success mt-3 " @click="saveChanges">저장</button>
        <button class="btn btn-secondary mt-3 mx-1" @click="cancelUpdate">취소</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useUserStore } from "@/stores/user";
import router from "@/router";
import axios from "axios";

const store = useUserStore();

const nicknameChecked = ref(false);

const users = ref([]);

const editedpassword = ref(store.mypage.password);
const editedname = ref(store.mypage.name);
const editedemail = ref(store.mypage.email);
const editednickname = ref(store.mypage.nickname);

const getUserList = () => {
  const API_URL = `http://localhost:8080/user/users`;
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

const nicknameCheck = () => {
  const user = {
    nickname : editednickname.value,
  };

  if (users.value.some((u) => u.nickname === user.nickname)) {
    alert("이미 사용 중인 닉네임입니다.");
  } else {
    alert("사용 가능한 닉네임입니다.");
    nicknameChecked.value = true;
  }
};

const saveChanges = function() {
    const user = {
        user_id : store.mypage.user_id,
        id : store.mypage.id,
        password : editedpassword.value,
        name : editedname.value,
        email : editedemail.value,
        nickname : editednickname.value,
    }

    if(!nicknameChecked.value){
    alert("닉네임 중복 확인을 해주세요.");
    return;
  }

    if (!editedemail.value.includes('@')) {
    alert("올바른 이메일 형식이 아닙니다.");
    return;
  }

  let reg = /^(?=.*[@$!%*#?&]).{8,}$/;
  if (!reg.test(editedpassword.value)) {
    alert("비밀번호는 8자리 이상이며 특수문자를 1개 이상 포함해야 합니다.");
    return;
  }

    store.updateUser(user);
}

const cancelUpdate = () => {
  router.go(-1);
};

getUserList();
</script>

<style scoped>
</style>