<!-- DeleteModal.vue -->
<template>
    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <p>정말 삭제하시겠습니까?</p>
        <button @click="deleteConfirmed" class="btn btn-danger">삭제</button>
        <button @click="cancelDelete" class="btn btn-secondary">취소</button>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, defineProps, defineEmits } from 'vue';
  
  const { showModal } = defineProps(['showModal']);
  const emit = defineEmits();
  
  const deleteConfirmed = () => {
    emit('deleteConfirmed');
  };
  
  const cancelDelete = () => {
    emit('cancelDelete');
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
    background-color: white;
    padding: 20px;
    border-radius: 8px;
    text-align: center;
  }
  </style>
  