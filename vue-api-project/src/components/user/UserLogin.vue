<template>
  <div class="container mt-5">
    <div class="row">
      <!-- 왼쪽에 고양이 사진 -->
      <div class="col-md-6">
        <div class="mb-2 text-center">
          <img src="@/assets/img/logincat.jpg" alt="Cat Image" style="max-width:70%; height:100%; ">
        </div>
      </div>

      <!-- 오른쪽에 로그인 폼 -->
      <div class="col-md-6">
        <h2 class="text-center">로그인</h2>
        <form @submit.prevent="login" class="mt-4">
          <div class="mb-3">
            <label for="id" class="form-label">아이디</label>
            <input
              type="text"
              class="form-control"
              id="id"
              placeholder="아이디를 입력하세요."
              v-model="id"
            />
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">비밀번호</label>
            <input
              type="password"
              class="form-control"
              id="password"
              placeholder="비밀번호를 입력하세요."
              v-model="password"
            />
          </div>
          <button type="submit" class="btn btn-primary">로그인</button>
        </form>
        <div class="mt-3 text-center">
          <p class="mb-0">계정이 없다면</p>
          <router-link to="/register" style="text-decoration: none; color: #007bff;">회원가입</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
const store = useUserStore();

const id = ref("");
const password = ref("");

const login = () => {
  let user = {
    id: id.value,
    password: password.value,
  };
  store.setlogin(user);
};
</script>

<style scoped>
  /* 스타일 추가 */
  body {
    background-color: #f8f9fa;
  }

  /* .container {
    background-color: #ffffff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  } */

  form {
    background-color: #ffffff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }

  button {
    width: 100%;
  }

  a {
    color: #007bff;
  }
</style>
