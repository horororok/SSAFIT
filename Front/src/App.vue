<template>
  <div>
    <HeaderNav :user="user" @logout="logout" />
    <RouterView @login-user="loginUser" />
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import HeaderNav from "@/components/common/HeaderNav.vue";
import axios from "axios";
import { useRouter } from "vue-router";
import http from '@/util/http-common.js';

const router = useRouter();
const user = ref(null);

onMounted(() => {
  const savedUser = localStorage.getItem("loginUser");
  if (savedUser) {
    user.value = JSON.parse(savedUser);
  }
});

const logout = () => {
  user.value = null;
  localStorage.removeItem("loginUser");
};

const loginUser = (loginUser) => {
  // user 정보 요청 api 주소
  const API_URL = `user`;

  // axios 요청
  http
    .get(API_URL)
    .then((res) => {
      let matchedUser = res.data.find(
        (u) => u.id === loginUser.id && u.password === loginUser.password
      );

      if (matchedUser) {
        user.value = matchedUser;
        localStorage.setItem("loginUser", JSON.stringify(matchedUser));
        alert("로그인 성공");
        router.push("/");
      } else {
        alert("로그인 실패");
      }
    })
    .catch((err) => {
      console.log(err);
      alert("로그인 실패: 서버 에러");
    });
};
</script>
<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  list-style: none;
  font-family: Arial, Helvetica, sans-serif;
}
.text-center {
  text-align: center;
}
.container {
  margin: 0px 30px;
}
</style>
