<template>
    <div>
        <h2>마이페이지</h2>
        <div>
            <div>{{store.mypage.name}} 님의 마이페이지 ^__^</div>

            <nav>
                <router-link :to="{ name: 'detail', params: { userId: store.loginUserObj.user_id} }" >상세 정보</router-link>
                <router-link :to="{ name: 'myregist', params: { userId: store.loginUserObj.user_id} }">추가 정보 등록</router-link>
                <!-- <router-link>찜한 영상 보기</router-link>
                <router-link>팔로워 / 팔로잉</router-link> -->
            </nav>
            <hr>
            <router-view />
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