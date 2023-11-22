<template>
    <div class="modal">
        <div class="modal-content">
            <p>내용을 입력하십시오.</p>
            <button @click="closewarnModalHandler">확인</button>
        </div>
    </div>
</template>

<script setup>
import { ref, defineProps, defineEmits } from 'vue';

const { showwarnModal } = defineProps(['showwarnModal']);
const emit = defineEmits();

const closewarnModalHandler = () => {
  emit('closewarnModal');
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
    width: 300px; /* 너비 조절 */
    background-color: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
}

/* 확인 버튼 스타일링 */
.modal-content button {
    background-color: #bfd49e;
    color: white;
    border: none;
    padding: 10px 15px;
    border-radius: 4px;
    cursor: pointer;
}

/* 확인 버튼에 호버 효과 추가 */
.modal-content button:hover {
    background-color: #bfd49e;
}
</style>