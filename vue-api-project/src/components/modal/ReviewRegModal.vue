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

const { showModal } = defineProps(['showModal']);
const emit = defineEmits();

const closeModalHandler = () => {
  emit('closeModal');
};
</script>
  
<style scoped>
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
    width: 300px; 
    background-color: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
}

.modal-content button {
    background-color: #bfd49e;
    color: white;
    border: none;
    padding: 10px 15px;
    border-radius: 4px;
    cursor: pointer;
}

.modal-content button:hover {
    background-color: #bfd49e;
}
</style>
  