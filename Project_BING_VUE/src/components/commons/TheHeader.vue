<template>
  <div class="header-global">
    <div class="header-container">
      <div class="weather">
        <TheWeather />
      </div>
      <div class="header-project-name">
        <p>HABING</p>
      </div>
      <div class="header-router-container">
        <RouterLink :to="{ name: 'main' }">HOME</RouterLink>
        <RouterLink :to="{ name: 'community' }">COMMUNITY</RouterLink>
        <button v-if="!loginUser" @click="changeForm(1)">SIGN IN</button>
        <button v-if="!loginUser" @click="changeForm(2)">SIGN UP</button>
        <RouterLink
          v-if="loginUser"
          :to="{ name: 'myPage', params: { nickname: '닉네임' } }"
          >MYPAGE</RouterLink
        >
        <button v-if="loginUser" @click="logout()">로그아웃</button>
      </div>
    </div>
    <div>
      <LoginForm v-if="showForm == 1" />
      <RegistForm v-if="showForm == 2" />
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "../../stores/userStore";
import { RouterLink } from "vue-router";
import { ref, computed, onMounted } from "vue";
import LoginForm from "@/components/account/LoginForm.vue";
import RegistForm from "@/components/account/RegistForm.vue";
import TheWeather from "@/components/commons/TheWeather.vue";
const userStore = useUserStore();
const loginUser = computed(() => userStore.loginUser);
const headerShow = ref(true);
const footerShow = ref(false);
// 페이지 로딩 시 로그인 유저 체크
onMounted(() => {
  userStore.loginUser = sessionStorage.getItem("access-token");
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
  font-weight: 600;
  color: white;
  text-align: center;
  animation: glow 1s ease-in-out infinite alternate;
}
@-webkit-keyframes glow {
  from {
    text-shadow: 0 0 5px #87cefa, 0 0 10px #87cefa, 0 0 15px #87cefa,
      0 0 20px #708090, 0 0 25px #2f4f4f, 0 0 30px #000000;
  }
  to {
    text-shadow: 0 0 10px #87cefa, 0 0 15px #87cefa, 0 0 20px #87cefa,
      0 0 25px #708090, 0 0 30px #2f4f4f, 0 0 35px #000000, 0 0 40px #000000;
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
</style>
