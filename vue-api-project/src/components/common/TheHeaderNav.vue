<template>
    <div id="container">
        <header>
            <nav>
                <RouterLink to="/">Home</RouterLink> |
                <a href="#" v-if="store.isLoggedIn" @click="logout">로그아웃</a> 
                <RouterLink to="/login" v-else>로그인</RouterLink> |
                <RouterLink to="/regist" v-show="!store.isLoggedIn">회원가입</RouterLink> |
                <RouterLink :to="{ name: 'mypage', params: { userId: store.loginUserObj.user_id} }" v-show="store.isLoggedIn">마이페이지</RouterLink> |
                <RouterLink :to="{ name: 'videoList' }">VideoList</RouterLink> |
                <RouterLink :to="{ name: 'videoCreate' }">VideoCreate</RouterLink>
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

// console.log("확인", store.loginUserObj);

</script>

<style  scoped>
#container {
    text-align: center;
}

nav {
    padding: 30px
}

nav a {
    font-weight: bold;
    text-decoration: none;
    color: black;
}

nav a.router-link-exact-active {
    color: #42b983
}
</style>