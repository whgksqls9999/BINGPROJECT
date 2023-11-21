import { defineStore } from "pinia";
import { ref, computed } from "vue";
import axios from "axios";
import router from "@/router/index.js";
import { useFavStore } from "@/stores/favStore";

export const useUserStore = defineStore("user", () => {
  // Store
  const favStore = useFavStore();

  //API
  const REST_USER_API = "http://localhost:1004/user";
  const REST_FOLLOW_API = "http://localhost:1004/follow";

  // 유저 정보 가져오기(닉네임)
  const user = ref({});
  const getUser = (nickname) => {
    axios.get(`${REST_USER_API}/nickname/${nickname}`).then((response) => {
      user.value = response.data;
    });
  };

  // 유저 정보 가져오기(이메일)
  const getUserByEmail = (email) => {
    axios
      .get(`${REST_USER_API}/email/${email}`)
      .then((response) => (user.value = response.data));
  };

  // 유저 목록 가져오기
  const users = ref([]);
  const getAllUsers = () => {
    axios.get(`${REST_USER_API}/`).then((response) => {
      users.value = response.data;
    });
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
        if (response.data["access-token"] == null) {
          alert("ID 또는 비밀번호를 확인해주세요.");
        } else {
          sessionStorage.setItem("access-token", response.data["access-token"]);
          const token = response.data["access-token"].split(".");
          loginUser.value = JSON.parse(atob(token[1]));
          showForm.value = 0;

          // 기본 정보 가져오기
          getUserByEmail(loginUser.value.email);
          getFollowerList(loginUser.value.email);
          getFollowingList(loginUser.value.email);
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
      .then((response) => {
        alert("환영합니다!");
        router.push({ name: "main" });
      })
      .catch((err) => {
        console.log(err);
        alert("회원가입 중 에러가 발생했습니다");
      });
  };

  // 로그인 체크
  const doLoginCheck = () => {
    return sessionStorage.getItem("access-token");
  };

  //회원정보 수정
  const modifyUser = (update) => {
    axios
      .put(`${REST_USER_API}/${update.email}`, update, {
        headers: { "Content-Type": "application/json" },
      })
      .then((response) => {
        router.push({ name: "myInfo", params: { email: update.email } });
      })
      .catch((err) => console.log(err));
  };

  //회원 탈퇴
  const deleteUser = (removeEmail) => {
    axios
      .delete(`${REST_USER_API}/${removeEmail}`)
      .then((response) => {
        sessionStorage.removeItem("access-token");
        loginUser.value = '';
        alert("회원님의 탈퇴가 완료되었습니다.");
        router.push({ name: "main" });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // 팔로워 정보 가져오기
  const followerList = ref([]);
  const getFollowerList = (email) => {
    axios.get(`${REST_FOLLOW_API}/${email}/follower`).then((response) => {
      followerList.value = response.data;
    });
  };

  // 팔로잉 정보 가져오기
  const followingList = ref([]);
  const getFollowingList = async (email) => {
    await axios
      .get(`${REST_FOLLOW_API}/${email}/following`)
      .then((response) => {
        followingList.value = response.data;
      });
  };

  // 팔로잉 취소하기
  const doFollowCancel = async (follow_id, email) => {
    await axios
      .delete(`${REST_FOLLOW_API}/${follow_id}`)
      .then(() => {
        getFollowerList(email);
        getFollowingList(email);
      })
      .catch((err) => console.log(err));
  };

    // 세션에서 유저 이메일 가져오기
    const getUserEmail = () => {
      if (!sessionStorage.getItem("access-token")) return;
      loginUser.value = JSON.parse(
        atob(sessionStorage.getItem("access-token").split(".")[1])
      );
      return loginUser.value.email;
    };

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
    deleteUser,
    users,
    getAllUsers,
    getFollowerList,
    getFollowingList,
    followerList,
    followingList,
    doFollowCancel,
    getUserEmail
  };
});
