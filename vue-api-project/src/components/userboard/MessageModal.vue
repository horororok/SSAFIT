<template>
    <div v-if="isVisible" class="modal">
      <div class="modal-content">
        <span class="close" @click="closeModal">&times;</span>
        <h2>메시지 보내기</h2>
        
        <form @submit.prevent="sendMessage">
          <div class="mb-3">
            <label for="recipient" class="form-label">수신자:</label>
            <input type="text" id="recipient" v-model="recipient" class="form-control" disabled>
          </div>
          <div class="mb-3">
            <label for="message" class="form-label">메시지:</label>
            <textarea id="message" v-model="message" class="form-control" rows="4"></textarea>
          </div>
          <button type="submit" class="btn btn-primary">보내기</button>
        </form>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, } from 'vue';
  import { watchEffect } from 'vue';
  
  
  const isVisible = ref(false);
  const recipient = ref(''); // 수신자 정보를 동적으로 업데이트해야 할 경우 사용
  const message = ref('');
  
  const props = defineProps(['isVisible']);
  

  const openModal = () => {
    isVisible.value = true;
    // 수신자 정보 등을 설정하는 로직을 추가
  };
  
  const closeModal = () => {
    isVisible.value = false;
    // 모달 닫기 시 수신자 정보 초기화 등을 수행
  };
  
  const sendMessage = () => {
    // 메시지를 서버로 전송하는 로직을 추가
    // 성공적으로 전송되면 모달을 닫기
    closeModal();
  };

watchEffect(() => {
    if (props.isVisible) {
      openModal();
    }
  });

  </script>
  
  <style scoped>
  </style>
  