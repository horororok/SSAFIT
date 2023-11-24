<template>
  <div class="container mt-5">
    <div class="row">
      <div class="col-md-6">
        <div class="mb-2 text-center">
          <img src="@/assets/img/logincat.jpg" alt="Cat Image" style="max-width:70%; height:100%; ">
        </div>
      </div>

      <div class="col-md-6">
        <h2 class="text-center">로그인</h2>
        <form @submit.prevent="login" class="mt-4">
          <div class="mb-3">
            <label for="id" class="form-label">아이디</label>
            <input type="text" class="form-control" id="id" placeholder="아이디를 입력하세요." v-model="id" />
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">비밀번호</label>
            <input type="password" class="form-control" id="password" placeholder="비밀번호를 입력하세요." v-model="password" />
          </div>
          <button type="submit" class="btn btn-success">로그인</button>
        </form>
        <div class="mt-3 text-center">
          <p class="mb-0">계정이 없다면</p>
          <router-link to="/regist" style="text-decoration: none; color: #bfd49e;">회원가입</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useRoute } from "vue-router";

const route = useRoute();

const store = useUserStore();

const id = ref("");
const password = ref("");

const login = () => {
  let user = {
    id: id.value,
    password: password.value,
  };
  store.setlogin(user)
}
</script>

<style scoped>
body {
  background-color: #f8f9fa;
}

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
