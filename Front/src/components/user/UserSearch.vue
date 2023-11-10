<template>
  <div class="container">
    <div class="text-center">
      <input
        class="view"
        type="text"
        v-model="search"
        placeholder="이름을 입력하세요."
      />
      <button class="btn" @click="searchUser">검색</button>
    </div>
    <br />
    <hr />
    <div>
      <h2>검색 결과</h2>
      <div v-if="users.length">
        <table class="user-list">
          <colgroup>
            <col style="width: 5%" />
            <col style="width: 40%" />
            <col style="width: 20%" />
            <col style="width: 20%" />
            <col style="width: 15%" />
          </colgroup>
          <thead>
            <tr>
              <th>번호</th>
              <th>아이디</th>
              <th>이름</th>
              <th>이메일</th>
              <th>나이</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(user, index) in users" :key="index">
              <td>{{ index + 1 }}</td>
              <td>
                <RouterLink class="user-link" :to="`/user/${user.id}`">{{
                  user.id
                }}</RouterLink>
              </td>
              <td>{{ user.name }}</td>
              <td>{{ user.email }}</td>
              <td>{{ user.age }} 세</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-else>검색 결과가 없습니다.</div>
    </div>
    <br />
  </div>
</template>

<script setup>
import { ref } from "vue";
import axios from "axios";

const search = ref("");
const users = ref([]);

const searchUser = () => {
  const API_URL = `http://localhost:9999/userapi/user/search`;
  axios({
    url: API_URL,
    method: "GET",
    params: {
      word: search.value,
    },
  })
    .then((res) => {
      if (res.data) {
        const foundUser = res.data.find((user) => {
          return user.id === search.value;
        });
        if (foundUser) {
          users.value = [foundUser];
        } else {
          users.value = [];
          alert("검색한 사용자가 없습니다");
        }
      } else {
        alert("가입한 회원이 없습니다.");
      }
    })
    .catch((err) => {
      console.error(err);
      users.value = [];
      alert("검색 중 오류가 발생했습니다");
    });
};
</script>
