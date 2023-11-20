<template>
    <div id="container">
      <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <RouterLink class="navbar-brand" to="/">SSAFIT</RouterLink>
  
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
  
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto flex-nowrap">
              <li class="nav-item" v-if="store.isLoggedIn">
                <a href="#" class="nav-link" @click="logout">로그아웃</a>
              </li>
              <li class="nav-item" v-else>
                <RouterLink class="nav-link" to="/login">로그인</RouterLink>
              </li>
              <li class="nav-item" v-show="!store.isLoggedIn">
                <RouterLink class="nav-link" to="/regist">회원가입</RouterLink>
              </li>
              <li class="nav-item" v-if="store.isLoggedIn">
                <RouterLink class="nav-link" :to="{ name: 'mypage', params: { userId: store.loginUserObj.user_id} }">마이페이지</RouterLink>
              </li>
              <li class="nav-item">
                <RouterLink class="nav-link" :to="{ name: 'videoList' }">VideoList</RouterLink>
              </li>
              <li class="nav-item">
                <RouterLink class="nav-link" :to="{ name: 'videoList' }">유저게시판</RouterLink>
              </li>
            </ul>
          </div>
        </nav>
      </header>
    </div>
  </template>
  
  <script setup>
  import { useUserStore } from "@/stores/user";
  
  const store = useUserStore();
  
  const logout = () => {
      store.setlogout();
  };
  </script>
  
  <style scoped>
  #container {
    text-align: center;
  }
  
  .navbar {
    padding: 1rem;
  }
  
  .navbar-brand {
    font-weight: bold;
  }
  
  .navbar-toggler {
    border: none;
  }
  
  .navbar-toggler-icon {
    background-color: #000;
  }
  
  .navbar-nav {
    margin-left: auto;
  }
  
  .nav-link {
    font-weight: bold;
    text-decoration: none;
    color: black;
  }
  
  .nav-link.router-link-exact-active {
    color: #42b983;
  }
  
  @media (max-width: 768px) {
    .navbar-nav {
      margin-left: 0;
      flex-direction: row !important;
    }
    
    .navbar-nav .nav-item {
      margin-right: 1rem;
    }
  }
  </style>
  