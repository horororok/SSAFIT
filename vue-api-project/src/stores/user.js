import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_USER_API = `http://localhost:8080/api-user`

export const useUserStore = defineStore('user', () => {

  //유저 목록  
  const UserList = ref([]);
  const getUserList = function () {
    axios.get(`${REST_USER_API}/users`)
      .then((res) => {
        UserList.value = res.data
      })
      .catch((err) => {
        console.log(err);
      })
  }


  //사용자 등록(회원가입)
  const createUser = function(user) {
    axios({
      url : `${REST_USER_API}/signup`,
      method: "POST",
      params:{
        id: user.id,
        password: user.password,
        name: user.name,
        email: user.email,
        phone_num : user.phone_num,
        nickname: user.nickname,
      },
    })
    .then(() => {
      alert("등록 완료");
      router.push("/"); //홈으로 가기 (나중에 수정)
    })
    .catch((err)=>{
      console.log(err);
    })

  }


  return { UserList, getUserList, createUser}
})
