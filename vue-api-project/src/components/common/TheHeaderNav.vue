<template>
    <div id="container">
        <header>
            <nav>
                <RouterLink to="/">Home</RouterLink> |
                <RouterLink to="/youtube">Youtube</RouterLink> |
                <a href="#" v-if="getUser" @click="logout">로그아웃</a>
                <RouterLink to="/login" v-else>로그인</RouterLink> |
                <RouterLink :to="{ name: 'regist' }">회원가입</RouterLink>
            </nav>
        </header>
    </div>
</template>

<script setup>
import {computed} from "vue";

const props = defineProps(["user"]);
const emits = defineEmits(["logout"]);

const getUser = computed(() => !!props.user);

const logout = () => {
  emits("logout");
};

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