<template>
    <div>
        <h4>리뷰 수정</h4>
        <fieldset>
            <legend>등록</legend>
            <div>
                <label for="writer">작성자 : </label>
                <input type="text" id="writer" readonly v-model="store.review.user_id">
            </div>
            <div>
                <label for="content">내용 : </label>
                <textarea id="content" cols="30" rows="10" v-model="store.review.content"></textarea>
            </div>
            <div>
                <button @click="updateReview">수정</button>
            </div>
        </fieldset>
    </div>
</template>

<script setup>
import { useReviewStore } from "@/stores/review";
const store = useReviewStore();

const updateReview = function () {
    store.updateReview()
}

</script>

<style  scoped>

</style>