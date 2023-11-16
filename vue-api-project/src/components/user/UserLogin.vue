<template>
  <div class="container mt-5">
    <h2 class="text-center">로그인 창</h2>
    <form @submit.prevent="login" class="col-md-4 offset-md-4 mt-4">
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

</style>