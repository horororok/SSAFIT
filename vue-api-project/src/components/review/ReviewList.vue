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
                <td>{{ review.nickname }}</td>
                <td>
                    <div @click="showReviewDetail(review.review_id)">{{ review.content }}</div>
                </td>
                <td>{{ review.created_at }}</td>
            </tr>
        </table>

    </div>
</template>

<script setup>
import { useReviewStore } from "@/stores/review";
import { useVideoStore } from "@/stores/video";
import { onMounted } from "vue";
import router from "@/router"; 
import { ref,computed } from "vue";

const store = useReviewStore();

const videoStore = useVideoStore();
// const videoId2 = computed(()=>videoStore.video.video_id);

// const reviewList = ref(null);

/*
onMounted(async () => {
    if (videoStore.video) {
        const videoId = videoStore.video.video_id ;
        await store.getReviewList(videoId);
    } else {
        // videoStore.videoDetail이 정의되지 않았을 때의 처리
        console.error("video is undefined or null");
    }
});
*/

// 리뷰 상세 페이지로 이동하는 함수
const showReviewDetail = function (reviewId) {
    // console.log("showReviewDetail => ", reviewId)
    // console.log(videoId2);
    // console.log(videoId2.value);
    
    router.push({ name: 'videoReviewDetail', params: { reviewId: reviewId  } })
}
</script>

<style scoped></style>