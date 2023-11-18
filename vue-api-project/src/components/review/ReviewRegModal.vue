<!-- Modal.vue -->
<template>
    <div class="modal">
        <div class="modal-content">
            <p>리뷰가 등록되었습니다.</p>
            <button @click="closeModalHandler">확인</button>
        </div>
    </div>
</template>
  
<script setup>
import { ref, defineProps, defineEmits } from 'vue';

// 부모로부터 전달된 showModal 프로퍼티와 이벤트 발행을 위한 emit 함수 가져오기
const { showModal } = defineProps(['showModal']);
const emit = defineEmits();

// 확인 버튼 클릭 시 실행되는 함수
const closeModalHandler = () => {
  // 부모 컴포넌트로 closeModal 이벤트를 발행하여 모달 닫기 요청
  emit('closeModal');
};
</script>
  
<style scoped>
/* 모달 스타일링 */
.modal {
    display: flex;
    align-items: center;
    justify-content: center;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
    background-color: white;
    padding: 20px;
    border-radius: 8px;
}
</style>
  