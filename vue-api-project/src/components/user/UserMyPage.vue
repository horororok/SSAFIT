<template>
    <div>
        <h2>마이페이지</h2>
        <div>
            <div>{{store.mypage.name}} 님의 마이페이지 ^__^</div>
            <div>{{store.mypage.id}}</div>
            <div>{{store.mypage.email}}</div>
        </div>

    </div>
</template>

<script setup>
import {useRoute} from 'vue-router'
import {useUserStore} from '@/stores/user';
import {ref, onMounted} from 'vue';

const store = useUserStore()
const user = ref({});

const route = useRoute()

onMounted(() => {

    store.getmyPage(route.params.userId);

});





</script>

<style scoped>

</style>