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
  width: 300px; /* 너비 조절 */
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
}

/* 삭제 및 취소 버튼 스타일링 */
.modal-content button {
  margin: 5px;
  padding: 10px 15px;
  border-radius: 4px;
  cursor: pointer;
}

.btn-danger {
  background-color: #dc3545;
  color: white;
  border: none;
}

.btn-danger:hover {
  background-color: #c82333;
}

.btn-secondary {
  background-color: #6c757d;
  color: white;
  border: none;
}

.btn-secondary:hover {
  background-color: #545b62;
}
</style>
