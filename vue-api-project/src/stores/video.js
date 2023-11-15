import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_VIDEO_API = `http://localhost:8080/api-video/video`

export const useVideoStore = defineStore('video', () => {
  const videoList = ref([])
  
  const getVideoList = function () {
    
    // 잠시 더미 데이터 사용
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

const sortVideoList = function (sortCondition) {
  
  axios.get(REST_VIDEO_API, {
    params: sortCondition
  })
    .then((res) => {
      videoList.value = res.data
  })
}

const searchVideoList = function (searchCondition) {
  axios.get(REST_BOARD_API, {
    params: searchCondition
  })
    .then((res) => {
      videoList.value = res.data
  })
}

  return { videoList, getVideoList, video, getVideo,  sortVideoList, searchVideoList }
})
