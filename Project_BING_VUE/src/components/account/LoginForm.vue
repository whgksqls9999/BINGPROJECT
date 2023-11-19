<template>
  <!-- <div><h1>로그인 폼</h1></div> -->
  <div class="container">
    <div class="login-form">
      <div class="title">SIGN IN</div>
      <input type="text" v-model.trim="id" />
      <input type="password" v-model.trim="pw" @keyup.enter="login"/>
      <button @click="login">로그인</button>
      <button @click="closeWindow">닫기</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/userStore.js";
const userStore = useUserStore();

const id = ref("");
const pw = ref("");

const login = function () {
  userStore.userLogin(id.value, pw.value);
};

const closeWindow = () => {
  userStore.changeForm(0);
}
</script>

<style scoped>
.container {
  background-color: rgba(0, 0, 0, 0.5);
  width: 100vw;
  height: 100vh;
  position: absolute;
  top: 0;
  z-index: 1;
  display: flex;
  justify-content: center;
  animation: fadein 0.7s ease;
}

.login-form{
  background-color: white;
  width: 25rem;
  height: 30rem;
  opacity: 1;
  border: 1rem solid black;
  margin-top: 10%;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 4%;
  gap: 2rem;
}

@keyframes fadein{
  from{
    opacity: 0;
  }to{
    opacity: 1;
  }
}

.title{
  font-size: 50px;
  font-weight: bold;
}

input {
  box-shadow: 0 1px 0.3rem black;
}
</style>
