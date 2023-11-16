<template>
    <div id="container">
        <header>
            <nav>
                <RouterLink to="/">Home</RouterLink> |
                <a href="#" v-if="store.isLoggedIn" @click="logout">로그아웃</a> 
                <RouterLink to="/login" v-else>로그인</RouterLink> |
                <RouterLink to="/regist" v-show="!store.isLoggedIn">회원가입</RouterLink> |
                <RouterLink :to="{ name: 'videoList' }">VideoList</RouterLink> |
                <RouterLink :to="{ name: 'videoCreate' }">VideoCreate</RouterLink>
            </nav>
        </header>
    </div>
</template>

<script setup>
import { watchEffect } from "vue";
import { useUserStore } from "@/stores/user";

const store = useUserStore();

const logout = () => {
    store.setlogout();
};

watchEffect(() => {
    store.isLoggedIn = !!sessionStorage.getItem("loginUser");
})

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