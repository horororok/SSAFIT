import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'
import createPersistedState from 'pinia-plugin-persistedstate'

const REST_USER_API = `http://localhost:8080/api-user`

export const useUserStore = defineStore('user', () => {

  const user = ref({});
  const UserList = ref([]);
  const isLoggedIn = ref(false);
  const loginUserObj = ref({});
  const mypage = ref({});
  const myPageUser = ref({});

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

  //유저 한명 반환
  const getUser = function (id) {
    axios.get(`${REST_USER_API}/user/${id}`)
    .then((res) => {
      user.value = res.data
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
      data:{
        id: user.id,
        password: user.password,
        name: user.name,
        email: user.email,
        nickname: user.nickname,
      },
    })
    .then(() => {
      alert("등록 완료");
      router.push("/"); 
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
      data:{
        id : loginUser.id,
        password: loginUser.password,
      },
    })
    .then((res) => {
      console.log(res)
      const responseUser = res.data

      if(responseUser !== ""){
       // 사용자 정보를 sessionStorage에 저장
       sessionStorage.setItem("loginUser", JSON.stringify({
        id: responseUser.id,
        user_id: responseUser.user_id,
        nickname: responseUser.nickname,
      }));
       // loginUserObj 업데이트
       loginUserObj.value = {  
        id: responseUser.id,
        user_id: responseUser.user_id,
        // email : responseUser.email,
        // name : responseUser.name,
        nickname: responseUser.nickname,
        isLoggedIn: true,};
       isLoggedIn.value = true;
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
      isLoggedIn.value = false;
      sessionStorage.removeItem("loginUser");
      router.push("/")
    })
    .catch((err)=>{
      console.log(err);
      alert("로그아웃 실패 : 서버 에러");
    })
  };
  const persistedUser = JSON.parse(sessionStorage.getItem('loginUser'));
  if (persistedUser) {
    loginUserObj.value = persistedUser;
    isLoggedIn.value = true;
  }

  //마이페이지 회원 정보 보기
  const getmyPage = function(userId) {
    axios.get(`${REST_USER_API}/mypage/${userId}`)
    .then((res) => {
      console.log("응답:", res.data);
      mypage.value = res.data;
    })
    .catch((err) => {
      console.log(err);
    })
  }

  //마이페이지 추가 정보 등록
  const signupMypage = function(inputUser) {
    axios({
      url : `${REST_USER_API}/mypage/signup`,
      method: "POST",
      data: {
        age : inputUser.age,
        gender : inputUser.gender,
        self_intro : inputUser.self_intro,
        address : inputUser.address,
        fav_sport : inputUser.fav_sport,
        user_id : inputUser.user_id,
      }
    })
    .then(() => {
      alert("등록 완료");
      router.push("/"); 
    })
    .catch((err)=>{
      console.log(err);
      alert("서버 에러");
    })
  }

    const getmyPageUser = function(userId) {
      axios.get(`${REST_USER_API}/mypage/user/${userId}`)
    .then((res) => {
      console.log("응답:", res.data);
      myPageUser.value = res.data;
    })
    .catch((err) => {
      console.log(err);
    })
  }


  

  return { UserList, user, getUser, getUserList, createUser, setlogin, 
    setlogout, isLoggedIn, loginUserObj, mypage, getmyPage, signupMypage, myPageUser, getmyPageUser}
})