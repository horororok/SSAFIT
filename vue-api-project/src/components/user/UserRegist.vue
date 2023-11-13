<template>
    <div>
        <h2>회원가입창</h2>    
        <fieldset class="text-center">
        <label for="id">아이디</label>
        <input type="text" id="id" v-model="id" class="view" />  
        <button class="idCheck" @click="idCheck">중복확인</button>
        <br>
        <label for="password">비밀번호</label>
        <input
            type="password"
            id="password"
            v-model="password"
            class="view"
        /><br />
        <label for="password2">비밀번호 확인</label>
        <input
            type="password"
            id="password2"
            v-model="password2"
            class="view"
        /><br />
        <label for="name">이름</label>
        <input type="text" id="name" v-model="name" class="view" /><br />
        <label for="email">이메일</label>
        <input type="email" id="email" v-model="email" class="view" /><br />
        <label for="phone_num">전화번호</label>
        <input type="text" id="phone_num" v-model="phone_num" class="view" /><br />
        <label for="nickname">닉네임</label>
        <input type="text" id="nickname" v-model="nickname" class="view" /><br />
        <button class="btn" @click="regist">등록</button>
    </fieldset>
    </div>
</template>

<script setup>
import {ref} from "vue";
import axios from "axios";
import {useUserStore} from "@/stores/user";

const store = useUserStore()

const id = ref("");
const password = ref("");
const password2 = ref("");
const name = ref("");
const email = ref("");
const phone_num = ref("");
const nickname = ref("");

const users = ref([]);

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
        id: id.value
    }

    if(users.value.some((u) => u.id === user.id)){
      alert("이미 사용 중인 아이디입니다.");
      return;
    }else{
        alert("사용 가능한 아이디입니다.");
    }
}

//유효성 검사
const regist =() => {
    const user = {
        id: id.value,
        password : password.value,
        name: name.value,
        email : email.value, 
        phone_num : phone_num.value,
        nickname: nickname.value,
    }

    if(!email.value.includes('@')){
    alert("올바른 이메일 형식이 아닙니다.");
    return;
    }

    let reg = /^(?=.*[@$!%*#?&]).{8,}$/;
    if(!reg.test(password.value)){ 
      alert("비밀번호는 8자리 이상이며 특수문자를 1개 이상 포함해야 합니다.");
      return;
    }

    if(password.value !== password2.value){
      alert("비밀번호가 일치하지 않습니다.");
      return;
    }

    if(phone_num.value.length != 11){
        alert("전화번호는 숫자만 입력해 주십시오.");
        return;
    } //수정해야함

    if(users.value.some((u) => u.nickname === user.nickname)){
      alert("이미 사용 중인 닉네임입니다.");
      return;
    }

    store.createUser(user);
};

getUserList();

</script>

<style scoped>

</style>