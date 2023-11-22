import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'
import createPersistedState from 'pinia-plugin-persistedstate'

const REST_USER_API = `http://localhost:8080/api-user`
const REST_USERBOARD_API = `http://localhost:8080/userboard`
const REST_FOLLOW_API = `http://localhost:8080/follow`

export const useUserStore = defineStore('user', () => {

  const user = ref({});
  const UserList = ref([]);
  const isLoggedIn = ref(false);
  const loginUserObj = ref({});
  const mypage = ref({});
  
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

  //회원 정보 수정
  const updateUser = function(user){
    axios({
      url: `${REST_USER_API}/mypage`,
      method : "PUT",
      data:{
        user_id : user.user_id,
        id : user.id,
        password : user.password,
        name : user.name,
        email : user.email,
        nickname : user.nickname,
      },
    })
    .then(() => {
      alert("수정 완료");
      router.push({name :'detail'}); 
    })
    .catch((err)=>{
      console.log(err);
      alert("서버 에러");
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
        profile_image : inputUser.profile_image,
      }
    })
    .then(() => {
      alert("등록 완료");
      router.push(`/${inputUser.user_id}/userdetail`); 
    })
    .catch((err)=>{
      console.log(err);
      alert("서버 에러");
    })
  }

    //마이페이지 추가 정보 반환
    const myPageUser = ref({});
    
    const getmyPageUser = function(userId) {
      axios.get(`${REST_USER_API}/mypage/user/${userId}`)
    .then((res) => {
      console.log("마이페이지 추가 정보 응답:", res.data);
      myPageUser.value = res.data;
    })
    .catch((err) => {
      console.log(err);
    })
  }

//마이페이지 추가정보 수정 등록
  const updateMyPageUser = function(user){
    axios({
      url: `${REST_USER_API}/mypage/update`,
      method : "PUT",
      data:{
        age: user.age,
        gender: user.gender,
        self_intro: user.self_intro,
        address: user.address,
        fav_sport: user.fav_sport,
        user_id : user.user_id,
        profile_image : user.profile_image,
      }
    })
    .then(() => {
      alert("수정 완료");
      router.push(`/${user.user_id}/userdetail`); 
    })
    .catch((err)=>{
      console.log(err);
      alert("서버 에러");
    })
  }

  //유저게시판 전체 목록 반환
  const userBoardList = ref([]);
  const getUserBoardList = function() {
    axios.get(`${REST_USERBOARD_API}/list`)
    .then((res) => {
      console.log("유저게시판 전체 목록 데이터: ",res.data);
      userBoardList.value = res.data;
    })
    .catch((err) => {
      console.log("유저게시판 전체 목록 에러: ", err);
    })
  }
  
  //유저 게시판 한명 상세 보기
  const userBoard = ref({});
  const getUserBoardOne = function(user_id) {
    axios.get(`${REST_USERBOARD_API}/detail/${user_id}`)
    .then((res) => {
      console.log("유저게시판 한명 정보 : ", res.data);
      userBoard.value = res.data;
    })
    .catch((err) => {
      console.log(err);
    })
  }

  //본인 제외한 유저게시판 유저 목록 반환
  const friends = ref([]);
  const getFriendList = function(user_id){
    axios.get(`${REST_USERBOARD_API}/friends/${user_id}`)
    .then((res) => {
      console.log("유저게시판 한명 정보 : ", res.data);
      friends.value = res.data;
    })
    .catch((err) => {
      console.log("유저게시판 친구 목록 에러: ", err);
    })
  }


  //팔로우
  const follow  = function(followInfo) {
    axios({
      url : REST_FOLLOW_API,
      method : "POST",
      data : {
        user_from_id : followInfo.user_from_id,
        user_to_id : followInfo.user_to_id,
      },
      headers: {
        'Content-Type': 'application/json',
      }
    })
    .then((res) => {
      if(res.data === 1){
        console.log("팔로우 완료");
      }else if(res.data === 0){
        console.log("팔로우 실패");
      }
    })
    .catch((err)=>{
      console.log("팔로우 실패 : 서버 에러", err);
    })
  }


  //언팔로우
  const unfollow = function(followInfo){
    axios.delete(REST_FOLLOW_API, {
      data: {
        user_from_id : followInfo.user_from_id,
        user_to_id : followInfo.user_to_id,
      },
      headers: {
        'Content-Type': 'application/json',
      }
    })
    .then((res) => {
      if(res.data === 1){
        console.log("언팔로우 완료");
      }else if(res.data === 0){
        console.log("언팔로우 실패");
      }
    })
    .catch((err)=>{
      console.log("언팔로우 실패 : 서버 에러", err);
    })
  }
 
  //팔로잉 목록 반환
  const following = ref([]);
  const getFollowingList = function(id){
    axios.get(`${REST_FOLLOW_API}/following/${id}`)
    .then((res) => {
      console.log("팔로잉 목록 반환: ", res.data);
      following.value = res.data;
    })
    .catch((err) => {
      console.log("팔로잉 목록 에러", err);
    })
  }

  //팔로워 목록 반환
  const follower = ref([]);
  const getFollowerList = function(id){
    axios.get(`${REST_FOLLOW_API}/follower/${id}`)
    .then((res) => {
      console.log("팔로워 목록 반환: ", res.data);
      follower.value = res.data;
    })
    .catch((err) => {
      console.log("팔로워 목록 에러", err);
    })
  }



  return { UserList, user, getUser, getUserList, createUser, setlogin, 
    setlogout, isLoggedIn, loginUserObj, mypage, getmyPage, signupMypage, 
    myPageUser, getmyPageUser, updateUser, userBoard, userBoardList, getUserBoardList, getUserBoardOne,
    friends, getFriendList, follow, unfollow, following, follower, getFollowingList, getFollowerList, updateMyPageUser}
})