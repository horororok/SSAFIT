import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_VIDEO_API = `http://localhost:8080/api-video/video`
const REST_VIDEOLIKE_API = `http://localhost:8080/vlike`

export const useVideoStore = defineStore('video', () => {

  const isliked = ref(0)
  const liked_cnt = ref(0)

  const likeVideo = async function (videolike) {
    try {
      const response = await axios.post(`${REST_VIDEOLIKE_API}`, videolike, {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      isliked.value = response.data.isliked;
      liked_cnt.value = response.data.liked_cnt;
      return response.data;
    } catch (error) {
      console.error('Error in likeVideo:', error);
      throw error; 
    }
  };
  
  
  const unlikeVideo = async function (videolike) {
    try {
      const response = await axios.delete(`${REST_VIDEOLIKE_API}`, {
        data: videolike
      });
      isliked.value = response.data.isliked;
      liked_cnt.value = response.data.liked_cnt;
      return response.data;
    } catch (error) {
      console.error('Error in unlikeVideo:', error);
      throw error;
    }
  };

  const getLiked = async function (videolike) {
    try {
      const response = await axios.get(`${REST_VIDEOLIKE_API}`, {
        params: videolike
      });
      isliked.value = response.data.isliked;
      liked_cnt.value = response.data.liked_cnt;
      return response.data;
    } catch (error) {
      console.error('Error in getLiked:', error);
      throw error;
    }
  };




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

  return { videoList, getVideoList, video, getVideo, searchVideoList, isliked, liked_cnt, getLiked, likeVideo, unlikeVideo }
})
