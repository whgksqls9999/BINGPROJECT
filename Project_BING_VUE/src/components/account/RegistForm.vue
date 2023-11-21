<template>
  <div class="regist-container">
    <div class="regist-form">
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
      <label for="">withdraw text</label>
      <input type="text" v-model="withdraw_text" />
      <button @click="registUser">등록하기</button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useUserStore } from "@/stores/userStore.js";

const email = ref("");
const pw = ref("");
const pwCheck = ref("");
const name = ref("");
const gender = ref("");
const nickname = ref("");
const withdraw_text = ref("");
const pwConfirm = computed(() => pw.value == pwCheck.value);

// 회원가입 요청
const userStore = useUserStore();
const users = computed(() => userStore.users);

onMounted(() => {
  userStore.getAllUsers;
});

const passwordConfirm = () => {
  if (!pwConfirm.value) {
    console.log("ADSFSAF");
    alert("비밀번호가 일치하지 않습니다.");
    return true;
  }
  return false;
};

const isPwValid = (pwd) => {
  return pwd.length >= 8 && /[!@#$%^&*(),.?":{}|<>]/g.test(pwd);
};

const registUser = async () => {
  if (
    email.value === "" ||
    pw.value === "" ||
    name.value === "" ||
    gender.value === "" ||
    nickname.value === "" ||
    withdraw_text === ""
  ) {
    alert("모든 정보를 입력해주세요.");
    return;
  }

  if (passwordConfirm()) return;

  if (!isPwValid(pw.value)) {
    alert("비밀번호는 8자리 이상, 특수문자를 포함해야 합니다.");
    return;
  }
  await userStore.getAllUsers();
  console.log(email.value);
  console.log(users.value);
  console.log(userStore.users);
  if (users.value.length > 0) {
    const userAlready = users.value.some((user) => user.email === email.value);
    console.log(userAlready);
    if (userAlready) {
      alert("이미 존재하는 아이디입니다");
      return;
    }
  }

  let user = {
    email: email.value,
    pw: pw.value,
    name: name.value,
    gender: gender.value,
    nickname: nickname.value,
    withdraw_text: withdraw_text.value,
  };

  userStore.registUser(user);
};
</script>

<style scoped>
.regist-container{
  width: 100%;
  height: 100vh;
  position: fixed;
  background-color: rgba(128, 128, 128, 0.3);
  top: 0;
  left: 0;
}

.regist-form{
  display: flex;
  flex-direction: column;
  width: 20rem;
  position: fixed;
  top: 15rem;
  left: 20rem;
}
</style>
