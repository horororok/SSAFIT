import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_USER_API = `http://localhost:8080/api-user`

export const useUserStore = defineStore('user', () => {

  const user = ref(null);
  const UserList = ref([]);

  const loginUser = ref(null);
  //지금은 새로고침하면 로그인 상태 새로고침되어서 다시 null됨 
  //토큰 사용해서 유지시켜주기 jwt

  //유저 목록  
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

  //로그인
  const setlogin = ((newUser) => {
    axios.get(`${REST_USER_API}/user/` + newUser.id)
        .then((res) => {
          const responseUser = res.data

          if(responseUser !== null && responseUser.password === newUser.password){
            loginUser.value = responseUser
            // localStorage.setItem("loginUser", JSON.stringify(responseUser))
            alert("로그인 성공")
            router.push("/")
          }else{
            alert("아이디 또는 비밀번호가 올바르지 않습니다.")
          }
        })
        .catch(() => {
          alert("로그인 실패 : 서버 에러")
        })
  })

  //로그아웃
  const setlogout = () => {
    loginUser.value = null;
    alert("로그아웃 되었습니다.")
    localStorage.removeItem("loginUser");
    router.push("/")
  };

  return { UserList, user, getUserList, createUser, loginUser, setlogin, setlogout}
})
