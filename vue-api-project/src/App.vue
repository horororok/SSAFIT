<template>
    <div>
        <THeHeaderNav :user="user" @logout="logout"/>
        <RouterView @login-user="loginUser"/>
    </div>
</template>

<script setup>
import{ ref, onMounted } from "vue";
import THeHeaderNav from "@/components/common/THeHeaderNav.vue";
import axios from "axios";
import { useRouter } from "vue-router";

const router = useRouter();
const user = ref(null);



onMounted(() => {
  const savedUser = localStorage.getItem("loginUser");
  if (savedUser) {
    user.value = JSON.parse(savedUser);
  }
});


//로그아웃
const logout = () => {
  user.value = null;
  localStorage.removeItem("loginUser");
};


//로그인
const loginUser = (loginUser) => {
  const API_URL = `http://localhost:8080/api-user/users`;

  axios
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

<style  scoped></style>