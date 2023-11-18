<template>
    <div>
        <h2>마이페이지</h2>
        <div>
            <p>이름: {{user.name}}</p>
            <p>이메일 : {{user.email}}</p>
        </div>

    </div>
</template>

<script setup>
import {useUserStore} from '@/stores/user';
import {ref, onMounted} from 'vue';
import axios from 'axios';

const store = useUserStore()
const user = ref({});

onMounted(async() => {
   try{
     //현재 로그인한 사용자의 user_id 가져옴
    const userId = store.loginUserObj.user_id;
    // console.log(typeof(id)); //string

    //사용자 정보 가져옴
    await store.getUser(userId);
    // console.log(store.user);

    //store.user에 저장된 사용자 정보를 여기 user에 넣어줌.
    user.value = store.user;
    // console.log('사용자 정보:', user.value);
   } catch(err){
    console.error('사용자 정보 가져오는 중 오류 발생: ', error);
   }
});

</script>

<style scoped>

</style>