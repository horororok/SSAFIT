<template>
    <div>
        <h4>유저 한명 상세 페이지</h4>

        
            <div>프로필 사진</div>
            <div>닉네임: </div>
            <div>자기소개: </div>
            <div>선호 스포츠: </div>

            <div>
                <button @click="toggleFollow">
                    {{ isFollowing ? '언팔로우' : '팔로우' }}
                </button>
            </div>

            <div v-if="isFollowing">팔로우 중입니다</div>

            <div>
                <button @click="openSendMessageModal">
                    메시지 보내기
                </button>
            </div>

            <!-- MessageModal을 참조하고 ref로 설정 -->
            <MessageModal :isVisible="isVisible" @closeModal="closeModal" @sendMessage="sendMessage" />
       
      
    </div>
</template>
  
 
<script setup>
import { useUserStore } from '@/stores/user';
import { onMounted, ref, computed } from "vue";

import MessageModal from '@/components/userboard/MessageModal.vue';

import { useRoute } from 'vue-router';

const store = useUserStore();
const route = useRoute();
const user = ref(null);

onMounted(async () => {
    try {
        // 현재 라우터에서 유저 아이디 가져오기
        const userId = route.params.userboardId;
        console.log("userId:", userId);
        // 유저 아이디를 사용하여 유저 정보 가져오기
        user.value = await store.getUserBoardOne(userId);
    } catch (error) {
        console.error('유저 데이터를 가져오는 중 오류 발생:', error);
    }
});
</script>
  
<style scoped></style>
  