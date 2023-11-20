<template>
    <div>
        <div>좋아요 누른 영상 리스트</div>
        <ul>
            <li v-for="likedVideo in likedVideos" :key="likedVideo.id">
                <router-link :to="{ name: 'detail', params: { videoId: likedVideo.id } }">
                    {{ likedVideo.title }}
                </router-link>
            </li>
        </ul>
    </div>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import {useVideoStore} from '@/stores/video'
import {useUserStore} from '@/stores/user'

const userStore = useUserStore()
const videoStore = useVideoStore()

const likedVideos = ref([])

const getLikedVideos = ()=>{
    const userId = userStore.loginUserObj.user_id;
    const response = videoStore.getLikedVideos(userId)
    likedVideos.value = response.data
}

onMounted (()=>{
    getLikedVideos()
})


</script>

<style scoped>

</style>