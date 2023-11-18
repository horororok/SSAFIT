import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'

import UserLogin from '@/components/user/UserLogin.vue'
import UserRegist from '@/components/user/UserRegist.vue'
import UserList from '@/components/user/UserList.vue'
import UserMyPage from '@/components/user/UserMyPage.vue'
import UserMyPageDetail from '@/components/user/UserMyPageDetail.vue'
import UserMyPageRegist from '@/components/user/UserMyPageRegist.vue'

import VideoView from '@/views/VideoView.vue'
import VideoList from '@/components/video/VideoList.vue'
import VideoCreate from '@/components/video/VideoCreate.vue'
import VideoDetail from '@/components/video/VideoDetail.vue'
import VideoUpdate from '@/components/video/VideoUpdate.vue'

import ReviewList from '@/components/review/ReviewList.vue'
import ReviewDetail from '@/components/review/ReviewDetail.vue'
import ReviewUpdate from '@/components/review/ReviewUpdate.vue'
import ReviewCreate from '@/components/review/ReviewCreate.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path:'/login',
      name: 'login',
      component: UserLogin
    },
    {
      path:'/regist',
      name: 'regist',
      component: UserRegist
    },
    {
      path:'/userList',
      name: 'userList',
      component: UserList
    },
    {
      path : "/:userId",
      name : "mypage",
      component: UserMyPage,
      children: [
        {
          path: "detail",
          name : "detail",
          component: UserMyPageDetail,
        },
        {
          path: "myregist",
          name: "myregist",
          component: UserMyPageRegist,
        }
      ]
    },
    {
      path: '/video',
      name: 'video',
      component: VideoView,
      children: [
        {
          path: "",
          name: "videoList",
          component: VideoList
        },
        {
          path: "create",
          name: "videoCreate",
          component: VideoCreate
        },
        {
          path: ":videoId",
          name: "videoDetail",
          component: VideoDetail
        },
        {
          path: "review/update",
          name: "videoUpdate",
          component: VideoUpdate
        },
        {
          path: "review/:videoId",
          name: "videoReviewList",
          component: ReviewList,
        },
        {
          path: ":videoId/review/detail/:reviewId",
          name: "videoReviewDetail",
          component: ReviewDetail
        },
        {
          path: "review/update/:reviewId",
          name: "videoReviewUpdate",
          component: ReviewUpdate
        },
        {
          path: "review/create/:videoId",
          name: "videoReviewCreate",
          component: ReviewCreate
        }
      ]
    },
  ]
})

export default router
