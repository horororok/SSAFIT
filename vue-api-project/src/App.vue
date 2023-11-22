<template>
  <div>
    <THeHeaderNav />
    <RouterView />
    
  </div>
</template>

<script setup>
import THeHeaderNav from "@/components/common/THeHeaderNav.vue";

import { useUserStore } from "@/stores/user";
import { ref, onMounted, watchEffect } from "vue";


const store = useUserStore();
const user = ref(null);
onMounted(() => {
  const loginUserId = localStorage.getItem("loginUser");
  if (loginUserId >= 1) {
    store.getUser(loginUserId);
  }
  // if (savedUser) {
  //   store.user = JSON.parse(savedUser);
  // }
});

watchEffect(() => {

  const storedUserId = sessionStorage.getItem("loginUser");
  store.isLoggedIn = !!sessionStorage.getItem("loginUser");
  if (store.isLoggedIn) {
    store.getUser(storedUserId);
  }

})
</script>

<style scoped>
body {
  background-color: #e1fff0;
}
</style>
