<template>
    <div>
        <h2>Review List</h2>

        <!-- 리뷰 목록 표시하는 테이블 -->
        <table>
            <tr>
                <th>작성자</th>
                <th>내용</th>
                <th>작성일</th>
            </tr>

            <!-- 리뷰 목록 표시 -->
            <tr v-for="review in store.reviewList" :key="review.id">
                <td>{{ review.user_id }}</td>
                <td>
                    <div @click="showReviewDetail(review.id)">{{ review.content }}</div>
                </td>
                <td>{{ review.created_at }}</td>
            </tr>
        </table>

    </div>
</template>

<script setup>
import { useReviewStore } from "@/stores/review";
import { onMounted } from "vue";
import router from "@/router"; // Vue Router 인스턴스를 가져옴
import { ref, } from "vue";


const store = useReviewStore();

const props = defineProps(['videoId']);

const reviewList = ref(null);

onMounted(async () => {
    // 페이지 로드시 리뷰 목록을 가져옴
    const id = props.videoId || "";
    await store.getReviewList(id);
    reviewList.value = store.reviewList;
});

// onMounted(async () => {
//     try {
//         const id = props.videoId || "";
//         const reviews = await store.getReviewList(id);
//         console.log("Review List:", reviews);
//     } catch (error) {
//         console.error("Error fetching review list:", error);
//     }
// });

const showReviewDetail = function (reviewId) {
    router.push({ name: 'reviewDetail', params: { id: reviewId } })
}
</script>

<style scoped></style>