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

    const createReview = function (reviewData) { // reviewData: { video_id, content,user_id }
        console.log("reviewData: ", reviewData)
        axios.post(`${REST_VIDEO_API}/review`, reviewData)
          .then((response) => {
            // 추가: 리뷰를 생성한 후, 목록에 추가
            reviewList.value.push(response.data);
          });
      };

    const updateReview = function(review){
      axios({
        url: `${REST_VIDEO_API}/review`,
        method: "PUT",
        data: {
          review_id: review.review_id,
          content: review.content,
          user_id: review.user_id,
          video_id: review.video_id,
        },
      })
      




        // axios.put(`${REST_VIDEO_API}/review/${videoId}`, review)
        // .then((response) => {
        //     review.value = response.data
        // })
    }

    const deleteReview = function(reviewId){
        axios.delete(`${REST_VIDEO_API}/review/${reviewId}`)
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
