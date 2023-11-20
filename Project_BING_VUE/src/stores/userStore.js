import { defineStore } from "pinia";
import { ref, computed } from "vue";
import axios from "axios";
import router from "@/router/index.js";
export const useUserStore = defineStore("user", () => {
  const REST_USER_API = "http://localhost:1004/user";

  // 유저 정보 가져오기(닉네임)
  const user = ref({});
  const getUser = (nickname) => {
    axios
      .get(`${REST_USER_API}/nickname/${nickname}`)
      .then((response) => {user.value = response.data;
      console.log(response.data)});
  };

  // 유저 정보 가져오기(이메일)
  const getUserByEmail = (email) => {
    axios
      .get(`${REST_USER_API}/email/${email}`)
      .then((response) => (user.value = response.data));
  };

  // header 출력 폼 결정
  const showForm = ref("");
  const changeForm = (num) => {
    showForm.value = num;
  };

  // 로그인 요청
  const loginUser = ref("");
  const userLogin = (id, pw) => {
    axios
      .post(`${REST_USER_API}/login`, { email: id, pw: pw })
      .then((response) => {
        console.log(response.data["access-token"]);
        if (response.data["access-token"] == null) {
          alert("ID 또는 비밀번호를 확인해주세요.");
        } else {
          sessionStorage.setItem("access-token", response.data["access-token"]);
          const token = response.data["access-token"].split(".");
          console.log(JSON.parse(atob(token[1])));
          loginUser.value = JSON.parse(atob(token[1]));
          console.log(loginUser.value);
          showForm.value = 0;
          router.push({ name: "main" });
        }
      })
      .catch((err) => console.log(err));
  };

  // 로그아웃 요청
  const userLogout = () => {
    sessionStorage.removeItem("access-token");
    loginUser.value = null;
    alert("로그아웃 되었습니다.");
    router.push({ name: "main" });
  };

  // 회원가입 요청 - 하는중,,
  const registUser = (user) => {
    axios
      .post(`${REST_USER_API}/`, user, {
        headers: { "Content-Type": "application/json" },
      })
      .then((response) => console.log(response))
      .catch((err) => console.log(err));
  };

  // 로그인 체크
  const doLoginCheck = () => {
    return sessionStorage.getItem("access-token");
  };

//회원정보 수정          
  const modifyUser = (update) =>{
    console.log(update);
    axios
      .put(`${REST_USER_API}/${update.email}`, update, {
        headers: { "Content-Type": "application/json" }
      })
      .then((response) => {
        console.log(response);
        console.log(update.nickname)
        
        router.push({name:'myInfo',params:{nickname:update.nickname}});
      })
      .catch((err) => console.log(err));
  } 

  //회원 탈퇴
  const deleteUser=(removeEmail)=>{
    axios.delete(`${REST_USER_API}/${removeEmail}`)
    .then((response)=>{
      console.log('탈퇴완료');
      alert('회원님의 탈퇴가 완료되었습니다.');
      router.push({name:'main'})
    }).catch((err)=>{
      console.log(err)
    })
  }

  return {
    userLogin,
    loginUser,
    showForm,
    userLogout,
    changeForm,
    registUser,
    user,
    getUser,
    doLoginCheck,
    getUserByEmail,
    modifyUser,
    deleteUser
  }; 
});
