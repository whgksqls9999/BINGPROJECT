<template>
  <div><h1>로그인 폼</h1></div>
  <div>
    <input type="text" v-model.trim="id" />
    <input type="password" v-model.trim="pw" />
    <button @click="login">로그인</button>
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
</script>

<style scoped></style>
