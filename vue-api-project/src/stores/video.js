import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_VIDEO_API = `http://localhost:8080/api-video/video`

export const useVideoStore = defineStore('video', () => {
  
  //영상 목록 가져오기
  const videoList = ref([])
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
        // console.log(video.value)
    })
  }

  //조건에 따른 정렬
  const searchVideoList = function (searchCondition) {
  axios.get(REST_VIDEO_API, {
    params: searchCondition
  })
    .then((res) => {
      videoList.value = res.data
  })
}

  return { videoList, getVideoList, video, getVideo, searchVideoList }
})
