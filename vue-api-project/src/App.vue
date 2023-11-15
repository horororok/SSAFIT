<template>
    <div>
        <THeHeaderNav/>
        <RouterView />
    </div>
</template>

<script setup>
import THeHeaderNav from "@/components/common/THeHeaderNav.vue";
// import { useUserStore } from "@/stores/user";
// import{ref, onMounted} from "vue";

// const store = useUserStore();

// const user = ref(null);

// onMounted(() => {
//   const savedUser = localStorage.getItem("loginUser");
//   if (savedUser) {
//     store.user = JSON.parse(savedUser);
//   }
// });



</script>

<style  scoped></style>