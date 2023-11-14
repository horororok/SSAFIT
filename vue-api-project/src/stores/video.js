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

    // const dummyData = [
    //   {
    //     id: 1,
    //     title: 'Video 1',
    //     thumbnailUrl: 'https://example.com/thumbnail1.jpg',
    //     channel_name: 'Channel A',
    //     part: '전신',
    //     view_cnt: 1000,
    //   },
    //   {
    //     id: 2,
    //     title: 'Video 2',
    //     thumbnailUrl: 'https://example.com/thumbnail2.jpg',
    //     channel_name: 'Channel B',
    //     part: '하체',
    //     view_cnt: 1500,
    //   },
    //   {
    //     id: 3,
    //     title: 'Video 3',
    //     thumbnailUrl: 'https://example.com/thumbnail3.jpg',
    //     channel_name: 'Channel C',
    //     part: '상체',
    //     view_cnt: 800,
    //   },
    // ];

    // 가상의 더미 데이터로 비디오 목록 갱신
    // videoList.value = dummyData;
  }

  //영상 한개
  const video = ref({})
  const getVideo = function (id) {
    axios.get(`${REST_VIDEO_API}/${id}`)
      .then((response) => {
        video.value = response.data
        console.log(video.value)
    })
  }

  // const searchVideoList = function (searchCondition) {
  //   axios.get(REST_VIDEO_API, {
  //     params: searchCondition
  //   })
  //     .then((res) => {
  //       videoList.value = res.data
  //   })
  // }
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
