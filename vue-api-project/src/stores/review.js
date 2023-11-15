import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_REVIEW_API = `http://localhost:8080/api-review/review`;
const REST_USER_API = `http://localhost:8080/api-user/user`; // 사용자 정보를 가져오는 API 주소

export const useReviewStore = defineStore("review", () => {

    const reviewList = ref([]);
    const getReviewList = function(videoId){
        axios.get(`${REST_REVIEW_API}/list/${videoId}`)
        .then((response) => {
            reviewList.value = response.data
        })
    }
    // const getReviewList = async function (videoId) {
    //     try {
    //         const response = await axios.get(`${REST_REVIEW_API}/list/${videoId}`);
    //         const reviews = response.data;

    //         // 각 리뷰 작성자의 닉네임을 가져와서 추가
    //         for (const review of reviews) {
    //             const userResponse = await axios.get(`${REST_USER_API}/${review.user_id}`);
    //             review.nickname = userResponse.data.nickname;
    //         }

    //         reviewList.value = reviews;
    //         return reviews;
    //     } catch (error) {
    //         console.error("리뷰 목록을 가져오는 중 에러 발생:", error);
    //         throw error;
    //     }
    // };

    
    const review = ref({});
    const getReview = function(reviewId){
        axios.get(`${REST_REVIEW_API}/${reviewId}`)
        .then((response) => {
            review.value = response.data
        })
    }
    
    // const getReview = async function (id) {
    //     try {
    //         const response = await axios.get(`${REST_REVIEW_API}/${id}`);
    //         const reviewData = response.data;

    //         // 리뷰 작성자의 닉네임을 가져와서 추가
    //         const userResponse = await axios.get(`${REST_USER_API}/${reviewData.user_id}`);
    //         reviewData.nickname = userResponse.data.nickname;

    //         review.value = reviewData;
    //         return reviewData;
    //     } catch (error) {
    //         console.error(`리뷰(${id})를 가져오는 중 에러 발생:`, error);
    //         throw error;
    //     }
    // };

    const createReview = function(reviewId){
        axios.post(`${REST_REVIEW_API}`, reviewId)
        .then((response) => {
            review.value = response.data
        })
    }
    // const createReview = async function (review) {
    //     try {
    //         const response = await axios.post(`${REST_REVIEW_API}`, review);
    //         return response.data;
    //     } catch (error) {
    //         console.error("리뷰를 생성하는 중 에러 발생:", error);
    //         throw error;
    //     }
    // }

    return {
        reviewList,
        getReviewList,
        review,
        getReview,
        createReview,
    };
});
