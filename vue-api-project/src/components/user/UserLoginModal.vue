<template>
    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <p>{{ message }}</p>
        <button @click="closeModal">확인</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, defineProps, defineEmits } from 'vue';

  
  const { showModal, message } = defineProps(['showModal', 'message']);
  const emit = defineEmits();
  
  const closeModal = () => {
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
  