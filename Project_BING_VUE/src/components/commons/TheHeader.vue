<template>
  <div class="header-global">
    <div class="header-container" :class="{ fixed: !isHeaderFixed }">
      <div class="weather">
        <TheWeather />
      </div>
      <div class="header-project-name">
        <p>HABING</p>
      </div>
      <div class="header-router-container">
        <RouterLink :to="{ name: 'main' }" @click="scrollInit">HOME</RouterLink>
        <RouterLink :to="{ name: 'community' }">COMMUNITY</RouterLink>
        <button v-if="!loginUser" @click="changeForm(1)">SIGN IN</button>
        <button v-if="!loginUser" @click="changeForm(2)">SIGN UP</button>
        <RouterLink v-if="loginUser" :to="{ name: 'myPage', params: { email: loginUser.email } }">MYPAGE</RouterLink>
        <button v-if="loginUser" @click="logout()">LOGOUT</button>
      </div>
    </div>
    <div>
      <LoginForm v-if="showForm == 1" />
      <RegistForm v-if="showForm == 2" />
    </div>
  </div>
</template>

<script setup>
import { useCommonStore } from "../../stores/commonStore";
import { useUserStore } from "../../stores/userStore";
import { RouterLink } from "vue-router";
import { ref, computed, onMounted } from "vue";
import LoginForm from "@/components/account/LoginForm.vue";
import RegistForm from "@/components/account/RegistForm.vue";
import TheWeather from "@/components/commons/TheWeather.vue";

const commonStore = useCommonStore();
const userStore = useUserStore();
const loginUser = computed(() => userStore.loginUser);
const headerShow = ref(true);
const footerShow = ref(false);

// 헤더 fixed 상태
const isHeaderFixed = computed(() => commonStore.isHeaderFixed);

//스크롤 초기화
const scrollInit = () => {
  scrollTo(0, 0);
};

// 페이지 로딩 시 로그인 유저 체크
onMounted(() => {
  userStore.loginUser = JSON.parse(
    atob(sessionStorage.getItem("access-token").split(".")[1])
  );
});

// 로그인/회원가입 모달
const showForm = computed(() => userStore.showForm);
const changeForm = function (num) {
  userStore.changeForm(num);
};
// 로그아웃
const logout = () => {
  userStore.userLogout();
};
</script>

<style scoped>
a {
  text-decoration: none;
  color: white;
}

button {
  font-size: 17px;
  padding: 0;
  border: none;
  font-family: Poppins, "Noto Sans KR", sans-serif;
  background-color: transparent;
  color: white;
  cursor: pointer;
}

.header-container {
  height: 70px;
  display: flex;
  justify-content: space-between;
  background-color: transparent;
  padding: 0 10px;
}

.header-project-name {
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 30px;
  font-weight: bold;
  color: white;
  text-align: center;
  animation: glow 1s ease-in-out infinite alternate;
}

/* 추가된 부분 */
@media screen and (max-width: 700px) {
  .header-project-name {
    display: none;
  }

  .header-router-container {
    width: 100%;
    justify-content: center;
  }
}

@-webkit-keyframes glow {
  from {
    text-shadow: 0 0 10px #fff, 0 0 15px rgba(149, 207, 247, 0.8),
      0 0 20px rgba(149, 207, 247, 0.8), 0 0 25px rgba(149, 207, 247, 0.8),
      0 0 30px rgba(149, 207, 247, 0.8);
  }

  to {
    text-shadow: 0 0 15px rgba(0, 38, 83, 0.8), 0 0 20px rgba(0, 38, 83, 0.8),
      0 0 25px rgba(0, 38, 83, 0.8), 0 0 30px rgba(0, 38, 83, 0.8),
      0 0 35px rgba(0, 38, 83, 0.8);
  }
}

.header-router-container {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  gap: 20px;
  width: 30%;
}

.weather {
  width: 33%;
  display: flex;
  align-items: center;
  font-size: 17px;
  font-weight: 500;
}

.fixed {
  position: fixed;
  background-color: black;
  width: 100%;
  left: 0;
  top: 0;
  right: 0;
}
</style>
