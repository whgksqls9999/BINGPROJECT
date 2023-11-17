<template>
  <div>
    <div class="weather">
      <TheWeather />
    </div>
    <RouterLink :to="{ name: 'main' }">메인</RouterLink>
    <RouterLink :to="{ name: 'community' }">커뮤니티</RouterLink>
    <button v-if="!loginUser" @click="changeForm(1)">Sign In</button>
    <button v-if="!loginUser" @click="changeForm(2)">Sign Up</button>
    <RouterLink :to="{ name: 'myPage', params: { nickname: '닉네임' } }"
      >마이 페이지</RouterLink
    >
    <button v-if="loginUser" @click="logout()">로그아웃</button>
  </div>
  <div>
    <LoginForm v-if="showForm == 1" />
    <RegistForm v-if="showForm == 2" />
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
.weather {
  font-size: 20px;
  font-weight: 700;
}
</style>
