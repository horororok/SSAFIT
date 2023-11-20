import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_VIDEO_API = `http://localhost:8080/api-video/video`
const REST_VIDEOLIKE_API = `http://localhost:8080/api-vlike`

export const useVideoStore = defineStore('video', () => {

  const likedVideos = ref([]) //좋아요 한 영상 리스트
  const getLikedVideos = function (user_id) {
    axios.get(`${REST_VIDEOLIKE_API}/${user_id}`)
      .then((res) => {
        likedVideos.value = res.data
      })
      .catch((err) => {
        console.log("getLikedVideoList 에러", err);
      })
  }

  const isliked = ref(false)  //0이면 좋아요 안 한 상태, 1이면 좋아요 한 상태
  
  //좋아요 했는지 안했는지 확인 (필요 없을 수도)
  const getLiked = function(videolike) {
    axios.get(REST_VIDEOLIKE_API, {
      params : videolike
    })
    .then((res) => {
      if(res.data === 0){
        isliked.value = false;
      }else if(res.data === 1){
        isliked.value = true;
      }
      // isliked.value = JSON.stringify(res.data)
    })
    .catch((err) => {
      console.log("getLiked 에러", err);
    })
  }

  //좋아요 
  const likeVideo = function(videoLike) {
    axios({
      url : REST_VIDEOLIKE_API,
      method: "POST",
      data :{
        user_id : videoLike.user_id,
        video_id : videoLike.video_id,
      }
    })
    .then((res) => {
      if(res.data === 1){
        console.log("좋아요 완료");
        getVideo(video.value.video_id);
      }else if(res.data === 0){
        console.log("좋아요 실패");
      }
    })
    .catch((err)=>{
      console.log("좋아요 실패 : 서버 에러", err);
    })
  }

  //좋아요 취소
  const unlikeVideo = function(videoLike) {
    axios.delete(REST_VIDEOLIKE_API, {
      data: {
        user_id : videoLike.user_id,
        video_id : videoLike.video_id
      }
    })
    .then((res) => {
      if(res.data === 1){
        console.log("좋아요 취소 완료");
        getVideo(video.value.video_id);
      }else if(res.data === 0){
        console.log("좋아요 취소 실패");
      }
    })
    .catch((err)=>{
      console.log("좋아요 취소 실패 : 서버 에러", err);
    })
  }


  const videoList = ref([])

  //영상 전체 
  const getVideoList = function () {
    axios.get(REST_VIDEO_API)
      .then((response) => {
        videoList.value = response.data
      })

  }

  //영상 한개
  const video = ref({})

  const getVideo = function (id) {
    axios.get(`${REST_VIDEO_API}/${id}`)
      .then((response) => {
        video.value = response.data
      })
  }

  //정렬
  const searchVideoList = function (searchCondition) {
    axios.get(REST_VIDEO_API, {
      params: searchCondition
    })
      .then((res) => {
        videoList.value = res.data
      })
  }

  return { videoList, getVideoList, video, getVideo, searchVideoList, isliked, getLiked, likeVideo, unlikeVideo }
})
