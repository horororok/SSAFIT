import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_VIDEO_API = `http://localhost:8080/api-video/video`;

export const useReviewStore = defineStore("review", () => {
    const reviewList = ref([]);
    const getReviewList = function(videoId){
        // console.log("000000")
        // console.log("videoId: ", videoId)
        axios.get(`${REST_VIDEO_API}/review/${videoId}`)
        
        .then((response) => {
            reviewList.value = response.data
        })
    }

    
    const review = ref({});

    const getReview = function(reviewId){
        axios.get(`${REST_VIDEO_API}/review/detail/${reviewId}`)
        .then((response) => {
            console.log(response.data)
            review.value = response.data
        })
    }

    const createReview = function(review){ // review: {videoId, content}
        axios.post(`${REST_VIDEO_API}/review`, review)
        .then((response) => {
            review.value = response.data
        })
    }

    const updateReview = function(review){
        axios.put(`${REST_VIDEO_API}/review/${videoId}`, review)
        .then((response) => {
            review.value = response.data
        })
    }


    return {
        reviewList,
        getReviewList,
        review,
        getReview,
        createReview,
        updateReview,
    };
});
