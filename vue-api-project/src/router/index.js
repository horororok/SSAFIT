import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'

import UserLogin from '@/components/user/UserLogin.vue'
import UserRegist from '@/components/user/UserRegist.vue'
import MyPageDetail from '@/components/mypage/MyPageDetail.vue'
import MyPageRegist from '@/components/mypage/MyPageRegist.vue'
import MyPageUpdate from '@/components/mypage/MyPageUpdate.vue'
import MyPageUserDetail from '@/components/mypage/MyPageUserDetail.vue'
import MyPageView from '@/views/MyPageView.vue'
import MypageZZIM from '@/components/mypage/MypageZZIM.vue'
import MypageFollow from '@/components/mypage/MypageFollow.vue'
import MyPageRegistUpdate from '@/components/mypage/MyPageRegistUpdate.vue'


import VideoView from '@/views/VideoView.vue'
import VideoList from '@/components/video/VideoList.vue'
import VideoDetail from '@/components/video/VideoDetail.vue'

import ReviewList from '@/components/review/ReviewList.vue'
import ReviewDetail from '@/components/review/ReviewDetail.vue'
import ReviewUpdate from '@/components/review/ReviewUpdate.vue'
import ReviewCreate from '@/components/review/ReviewCreate.vue'

import UserBoardView from '@/views/UserBoardView.vue'
import UserBoardUserDetail from '@/components/userboard/UserBoardUserDetail.vue'

import { computed } from 'vue'
const checkLogin = (to, from, next) => {
  const isLoggedIn = computed(() =>
    !!JSON.parse(sessionStorage.getItem("loginUser")));

    if(!isLoggedIn.value){
      alert("로그인 후 이용 가능합니다.")
      next('/login');
    }else{
      next();
    }
};

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
      path : "/:userId",
      name : "mypage",
      component: MyPageView,
      children: [
        {
          path: "detail",
          name : "detail",
          component: MyPageDetail,
        },
        {
          path: "myregist",
          name: "myregist",
          component: MyPageRegist,
        },
        {
          path: "myregistupdate",
          name: "myregistupdate",
          component: MyPageRegistUpdate,
        },
        {
          path: "mydata",
          name: "mydata",
          component: MyPageUserDetail
        },
        {
          path: "myupdate",
          name: "myupdate",
          component: MyPageUpdate,
        },
        {
          path: "userdetail",
          name: "userdetail",
          component: MyPageUserDetail,
        },
        {
          path: "zzim",
          name: "zzim",
          component: MypageZZIM,
        },
        {
          path: "follow",
          name: "follow",
          component: MypageFollow,
        },
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
          component: VideoList,
          beforeEnter: checkLogin,
        },
        {
          path: ":videoId",
          name: "videoDetail",
          component: VideoDetail
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
    {
      path: '/userboard',
      name: 'userboard',
      component: UserBoardView,
      beforeEnter : checkLogin,
    }
    ,{
      path: "/userboard/:userboardId",
      name: "userboarddetail",
      component: UserBoardUserDetail
    }
  ]
})

export default router
