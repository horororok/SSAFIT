<!-- 리뷰로 써먹자 -->

<template>
    <div>
        <h4>게시글 수정</h4>
        <fieldset>
            <legend>등록</legend>
            <div>
                <label for="title">제목 : </label>
                <input type="text" id="title" v-model="store.video.title">
            </div>
            <div>
                <label for="writer">쓰니 : </label>
                <input type="text" id="writer" readonly v-model="store.video.writer">
            </div>
            <div>
                <label for="content">내용 : </label>
                <textarea id="content" cols="30" rows="10" v-model="store.video.content"></textarea>
            </div>
            <div>
                <button @click="updateVideo">수정</button>
            </div>
        </fieldset>
    </div>
</template>

<script setup>
import { useVideoStore } from "@/stores/video";
const store = useVideoStore();

const updateVideo = function () {
    store.updateVideo()
}
</script>

<style scoped></style>

<!-- 나중에 삭제 -->