import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import YoutubeView from '@/views/YoutubeView.vue'
import BoardView from '@/views/BoardView.vue'

import BoardList from '@/components/board/BoardList.vue'
import BoardCreate from '@/components/board/BoardCreate.vue'
import BoardDetail from '@/components/board/BoardDetail.vue'
import BoardUpdate from '@/components/board/BoardUpdate.vue'

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
    {
      path: '/board',
      name: 'board',
      component: BoardView,
      children: [
        {
          path: "/boardlist",
          name: "boardList",
          component: BoardList
        },
        {
          path: "create",
          name: "boardCreate",
          component: BoardCreate
        },
        {
          path: ":id",
          name: "boardDetail",
          component: BoardDetail
        },
        {
          path: "update",
          name: "boardUpdate",
          component: BoardUpdate
        },
      ]
    },
  ]
})

export default router
