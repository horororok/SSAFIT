<template>
    <div class="modal">
        <div class="modal-content">
            <p>로그아웃 하시겠습니까?</p>
            <button @click="logout">네</button>
            <div class="margin-between"></div>
            <button @click="cancellogoutModal">취소</button>
        </div>
    </div>
</template>

<script setup>
import {defineProps, defineEmits} from 'vue';
import { useUserStore } from '@/stores/user';

const store = useUserStore();

const clear= ()=>{
    store.loginUserObj = null;
}

const {showlogoutModal} = defineProps(['showlogoutModal']);
const emit = defineEmits();

const logout = () => {
    emit('cancellogoutModal');
    store.setlogout();
    clear();
};
const cancellogoutModal = () => {
    emit('cancellogoutModal');
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
    padding: 8px 12px;
    border-radius: 4px;
    cursor: pointer;
}

.modal-content button:hover {
    background-color: #bfd49e;
}

.margin-between {
    margin: 2px 0;
}
</style>