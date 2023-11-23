<template>
    <div>
        <fieldset>
            <legend>등록</legend>
            <div>
                <label for="writer">작성자 : </label>
                <input type="text" id="writer" readonly v-model="store.review.nickname">
            </div>
            <div v-if="!isEditing">
                <label for="content">내용 : </label>
                <div>{{ store.review.content }}</div>
                <button @click="startEditing">수정</button>
            </div>
            <div v-else>
                <label for="content">내용 : </label>
                <textarea id="content" cols="30" rows="10" v-model="editedContent"></textarea>
                <button @click="saveChanges">저장</button>
                <button @click="cancelUpdate">취소</button>
            </div>
        </fieldset>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useReviewStore } from "@/stores/review";
import router from "@/router";

const store = useReviewStore();
const isEditing = ref(false);
const editedContent = ref(store.review.content);

const startEditing = function () {
    isEditing.value = true;
}

const saveChanges = function () {
    store.updateReview({ content: editedContent.value });
    isEditing.value = false;
}

const cancelUpdate = function () {
    isEditing.value = false;
    router.push({ name: 'videoDetail', params: { videoId: store.review.video_id } });
}
</script>

<style scoped>
</style>
