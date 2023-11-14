<template>
    <div>
      <h4>비디오 상세</h4>
      <hr>
      <div>{{ store.video.title }}</div>
      <div>{{ store.video.url }}</div>
      <div>{{ store.video.channel_name }}</div>
      <div>{{ store.video.view_cnt }}</div>
      <div>{{ store.video.part }}</div>
  
      <div>삭제 수정 버튼은 리뷰로 갈 예정</div>
      <button @click="deleteVideo">삭제</button>
      <button @click="updateVideo">수정</button>
      
      <iframe
        v-if="store.video.url"
        width="560"
        height="315"
        :src="videoURL"
        title="YouTube video player"
        frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
        allowfullscreen
      ></iframe>
      <button>리뷰보기</button>
      <button>리뷰작성</button>
      <button @click="goToVideoList">목록으로</button>
    </div>
  </template>
  
  <script setup>
  import { useRoute, useRouter } from 'vue-router'
  import { useVideoStore } from "@/stores/video";
  import { onMounted , computed} from "vue";
  import axios from 'axios'
  
  const store = useVideoStore()
  
  const route = useRoute();
  const router = useRouter();
  onMounted(() => {
    store.getVideo(route.params.id)
  })
  
  const deleteVideo = function () {
    axios.delete(`http://localhost:8080/api-video/video/${route.params.id}`)
      .then(() => {
        router.push({ name: 'videoList' })
      })
  }
  
  const updateVideo = function () {
    router.push({ name: 'videoUpdate' })
  }
  
const goToVideoList = function () {
    router.push({ name: 'videoList' })
  }

  const videoURL = computed(() => {
    return store.video.url ? `https://www.youtube.com/embed/${store.video.url}` : '';
  });
  </script>
  

  <style scoped></style>
  