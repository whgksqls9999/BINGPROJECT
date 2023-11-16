<template>
  <div><h2>회원가입 폼</h2></div>
  <div>
    <label for="">email</label>
    <input type="text" v-model="email" />
    <label for="">pw</label>
    <input type="text" v-model="pw" />
    <label for="">pwCheck</label>
    <input type="text" v-model="pwCheck" />
    <label for="">name</label>
    <input type="text" v-model="name" />
    <label for="">gender</label>
    <input type="text" v-model="gender" />
    <label for="">nickname</label>
    <input type="text" v-model="nickname" />
    <button @click="registUser">등록하기</button>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useUserStore } from "@/stores/userStore.js";

const email = ref("");
const pw = ref("");
const pwCheck = ref("");
const name = ref("");
const gender = ref("");
const nickname = ref("");
const pwConfirm = computed(() => pw.value == pwCheck.value);

// 회원가입 요청
const userStore = useUserStore();
const registUser = () => {
  if (passwordConfirm()) return;

  let user = {
    email: email.value,
    pw: pw.value,
    name: name.value,
    gender: gender.value,
    nickname: nickname.value,
  };

  userStore.registUser(user);
};

const passwordConfirm = () => {
  if (!pwConfirm.value) {
    console.log("ADSFSAF");
    alert("비밀번호가 일치하지 않습니다.");
    return true;
  }
  return false;
};
</script>

<style scoped></style>
