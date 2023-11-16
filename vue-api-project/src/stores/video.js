import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_VIDEO_API = `http://localhost:8080/api-video/video`

export const useVideoStore = defineStore('video', () => {
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

  return { videoList, getVideoList, video, getVideo,  searchVideoList }
})
