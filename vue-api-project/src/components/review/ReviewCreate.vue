<template>
    <div>
        <h4>리뷰 작성</h4>
        <fieldset>
            <legend>등록</legend>
            <div>
                <label for="writer">작성자 : </label>
                <input type="text" id="writer" v-model="user.id">
            </div>
            <div>
                <label for="content">내용 : </label>
                <textarea id="content" cols="30" rows="10" v-model="review.content"></textarea>
            </div>
            <div>
                <label for="created_at">작성일 : </label>
                <input type="date" id="created_at" v-model="review.created_at">

            </div>
            <div>
                <button @click="createReview">등록</button>
            </div>
        </fieldset>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { useReviewStore } from "@/stores/review";

const store = useReviewStore()
const review = ref({
    user_id: '',
    content: '',
    created_at: '',
})

const createReview = function () {
    store.createReview(review.value)
}
</script>

<style scoped></style>
