import { defineStore } from "pinia";
import { ref, computed } from "vue";
import axios from "axios";
import router from "@/router/index.js";

export const useUserStore = defineStore("user", () => {
  const REST_USER_API = "http://localhost:1004/user";

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
        // console.log(response);
        sessionStorage.setItem("access-token", response.data["access-token"]);
        const token = response.data["access-token"].split(".");
        let id = token[1];
        id = JSON.parse(atob(id));
        loginUser.value = JSON.parse(atob(token[1]));
        showForm.value = 0;
        router.push({ name: "main" });
      })
      .catch((err) => console.log(err));
  };

  // 로그아웃 요청
  const userLogout = () => {
    sessionStorage.removeItem("access-token");
    loginUser.value = null;
  };

  // 회원가입 요청
  const regist = (user) => {
    console.log(user);
    axios
      .post(`${REST_USER_API}/`, null, {
        headers: { "Content-Type": "application/json" },
        data: user,
      })
      .then((response) => console.log(response))
      .catch((err) => console.log(err));
  };
  return { userLogin, loginUser, showForm, userLogout, changeForm, regist };
});
