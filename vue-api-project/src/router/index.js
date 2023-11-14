import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import YoutubeView from '@/views/YoutubeView.vue'

import UserLogin from '@/components/user/UserLogin.vue'
import UserRegist from '@/components/user/UserRegist.vue'
import UserList from '@/components/user/UserList.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/youtube',
      name: 'youtube',
      component: YoutubeView
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
  ]
})

export default router
