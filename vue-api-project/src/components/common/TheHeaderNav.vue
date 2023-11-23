<template>
  <div id="container">
    <header>
      <nav class="navbar navbar-expand-lg navbar-light" :class="{ 'bg-transparent': isTransparent }">
        <RouterLink class="navbar-brand" to="/" style="color: black;">&nbsp; SSAFITMATE</RouterLink>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
          aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
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
              <RouterLink class="nav-link" :to="{ name: 'detail', params: { userId: store.loginUserObj.user_id } }">마이페이지
              </RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" :to="{ name: 'videoList' }">운동영상</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink class="nav-link" :to="{ name: 'userboard' }">유저게시판</RouterLink>
            </li>
          </ul>
          <LogoutModal v-if="showlogoutModal" @cancellogoutModal="cancellogoutModal" />
        </div>
      </nav>
    </header>



  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { ref } from "vue";
import LogoutModal from "../modal/LogoutModal.vue";
import { computed } from "vue";

const store = useUserStore();

const showlogoutModal = ref(false);

const logout = () => {
  showlogoutModal.value = true;
};

const cancellogoutModal = () => {
  showlogoutModal.value = false;
};

// 스크롤 이벤트에 따라 투명한 배경 설정
const isTransparent = ref(true);
window.addEventListener("scroll", () => {
  isTransparent.value = window.scrollY < 50;
});
</script>

<style scoped>
#container {
  text-align: center;
}

.navbar {
  padding: 1rem 0;
  /* 상단 padding 추가 */
  width: 100%;
  top: 0;
  z-index: 1000;
  transition: background-color 0.3s ease;
  /* 배경색 전환 효과 */
}

/* 배경이 투명할 때의 스타일 */
.bg-transparent {
  background-color: transparent !important;
}

.navbar-brand {
  font-weight: bold;
}

.navbar-toggler {
  border: none;
}

.navbar-toggler-icon {
  background-color: rgb(250, 250, 250);
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
  color: #bfd49e;
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
