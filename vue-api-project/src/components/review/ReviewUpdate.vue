<template>
    <div>
        <h4>리뷰 수정</h4>
        <fieldset>
            <legend>등록</legend>
            <div>
                <label for="writer">작성자 : </label>
                <input type="text" id="writer" readonly v-model="store.review.nickname">
            </div>
            <div>
                <label for="content">내용 : </label>
                <textarea id="content" cols="30" rows="10" v-model="store.review.content"></textarea>
            </div>
            <div>
                <button @click="updateReview">수정</button>
                <button @click="cancelUpdate">취소</button>
            </div>
        </fieldset>
    </div>
</template>

<script setup>
import { useReviewStore } from "@/stores/review";
import router from "../../router";
const store = useReviewStore();

const updateReview = function () {
    store.updateReview()
}
const cancelUpdate = function () {
    router.push({ name: 'videoDetail', params: { videoId: store.review.video_id } });
}

</script>

<style  scoped>

</style>