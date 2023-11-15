import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_USER_API = `http://localhost:8080/api-user`

export const useUserStore = defineStore('user', () => {

  const user = ref(null);
  const UserList = ref([]);

  // const loginUser = ref(null);

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
      alert("서버 에러");
    })
  }

  //로그인 
  const setlogin = function(loginUser) {
    axios({
      url :  `${REST_USER_API}/login`,
      method: "POST",
      params:{
        id : loginUser.id,
        password: loginUser.password,
      },
    })
    .then((res) => {
      console.log(res)
      const responseUser = res.data
      // console.log(responseUser)

      if(responseUser !== ""){
        localStorage.setItem("loginUser", responseUser);
        alert("로그인 성공!");
        router.push("/")
      }else{
        alert("아이디 또는 비밀번호가 올바르지 않습니다.")
      } 
    })
    .catch((err)=>{
      console.log(err);
      alert("로그인 실패 : 서버 에러");
    })
  }
  
  //로그아웃
  const setlogout = () => {
    axios({
      url :  `${REST_USER_API}/logout`,
      method: "GET",
    })
    .then((res) => {
      console.log(res); 
      alert("로그아웃 되었습니다.")
      localStorage.removeItem("loginUser");
      router.push("/")
    })
    .catch((err)=>{
      console.log(err);
      alert("로그아웃 실패 : 서버 에러");
    })
  };

  //localStorage에 로그인 유저 저장해서 새로고침해도 로그인 상태 유지됨
  //근데 TheHeaderVav.vue에서 로그인 / 로그아웃 바뀌는 거 바로 안바뀌고 새로고침 한 번 해야 적용됨
  //그리고 백에서 로그인/로그아웃하면 세션에 저장, 삭제 관련 질문


  //로그인
  // const setlogin = ((newUser) => {
  //   axios.get(`${REST_USER_API}/user/` + newUser.id)
  //       .then((res) => {
  //         const responseUser = res.data

  //         if(responseUser !== null && responseUser.password === newUser.password){
  //           loginUser.value = responseUser
  //           // localStorage.setItem("loginUser", JSON.stringify(responseUser))
  //           alert("로그인 성공")
  //           router.push("/")
  //         }else{
  //           alert("아이디 또는 비밀번호가 올바르지 않습니다.")
  //         }
  //       })
  //       .catch(() => {
  //         alert("로그인 실패 : 서버 에러")
  //       })
  // })

  

  //로그아웃
  // const setlogout = () => {
  //   loginUser.value = null;
  //   alert("로그아웃 되었습니다.")
  //   localStorage.removeItem("loginUser");
  //   router.push("/")
  // };

  // return { UserList, user, getUserList, createUser, loginUser, setlogin, setlogout}
  return { UserList, user, getUserList, createUser, setlogin, setlogout }
})
