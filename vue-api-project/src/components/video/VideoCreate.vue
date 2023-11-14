<template>
    <div>
        <h4>비디오 작성</h4>
        <fieldset>
            <legend>등록</legend>
            <div>
                <label for="title">제목 : </label>
                <input type="text" id="title" v-model="video.title">
            </div>
            <div>
                <label for="writer">쓰니 : </label>
                <input type="text" id="writer" v-model="video.writer">
            </div>
            <div>
                <label for="content">내용 : </label>
                <textarea id="content" cols="30" rows="10" v-model="video.content"></textarea>
            </div>
            <div>
                <button @click="createVideo">등록</button>
            </div>
        </fieldset>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { useVideoStore } from "@/stores/video";

const store = useVideoStore()
const video = ref({
    title: '',
    writer: '',
    content: ''
})

const createVideo = function () {
    store.createVideo(video.value)
}
</script>

<style scoped></style>

<!-- 필요없으니 나중에 삭제 -->