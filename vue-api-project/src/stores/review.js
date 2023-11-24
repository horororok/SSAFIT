import { ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_REVIEW_API = `http://localhost:8080/review`;

export const useReviewStore = defineStore("review", () => {
 
  const reviewList = ref([]);
  const getReviewList = function (videoId) {
    axios.get(`${REST_REVIEW_API}/${videoId}`)
      .then((response) => {
        reviewList.value = response.data
      })
  }

  const review = ref({});
  const getReview = function (reviewId) {
    axios.get(`${REST_REVIEW_API}/detail/${reviewId}`)
      .then((response) => {
        review.value = response.data
      })
  }

  const createReview = function (reviewData) { 
    return new Promise((resolve, reject) => {
      axios.post(REST_REVIEW_API, reviewData)
        .then((response) => {
          getReviewList(reviewData.video_id); 
          resolve(response.data); 
        })
        .catch((error) => {
          reject(error);
        });
    });
  };
  
  const updateReview = function (review) {
    axios({
      url: REST_REVIEW_API,
      method: "PUT",
      data: {
        review_id: review.review_id,
        content: review.content,
        user_id: review.user_id,
        video_id: review.video_id,
      },
    })
  }

  const deleteReview = function (reviewId) {
    axios.delete(`${REST_REVIEW_API}/${reviewId}`)
      .then(() => {
        reviewList.value = reviewList.value.filter((review) => review.review_id !== reviewId)
      })
  }

  return {
    reviewList,
    getReviewList,
    review,
    getReview,
    createReview,
    updateReview,
    deleteReview,
  };
});
