import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

import { useUserStore } from './user'

const REST_VIDEO_API = `http://localhost:8080/video`
const REST_VIDEOLIKE_API = `http://localhost:8080/vlike`

export const useVideoStore = defineStore('video', () => {
  const store = useUserStore();

  //영상 전체 
  const videoList = ref([])
  const getVideoList = function (userId) {
    axios.get(`${REST_VIDEO_API}/list/${userId}`)
      .then((response) => {
        videoList.value = response.data;
      })
      .catch((err)=>{
        console.log("영상 목록 반환 실패 : 서버 에러", err);
      })
  }

  //영상 한개
  const video = ref({})
  const getVideo = function (id) {
    axios.get(`${REST_VIDEO_API}/${id}?user_id=${store.loginUserObj.user_id}`)
      .then((response) => {
        video.value = response.data
      })
  }

  //정렬
  const searchVideoList = function (searchCondition) {
    axios.get(`${REST_VIDEO_API}/search?user_id=${store.loginUserObj.user_id}`, {
      params: searchCondition
    })
      .then((res) => {
        videoList.value = res.data
      })
      .catch((err)=>{
        console.log("정렬 실패 : 서버 에러", err);
      })
  }

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

  return { videoList, getVideoList, video, getVideo, searchVideoList, 
     likeVideo, unlikeVideo, likedVideos, getLikedVideos }
})
