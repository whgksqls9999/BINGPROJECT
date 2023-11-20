<template>
  <div class="modifyInfo-global">
    <div class="modifyInfo-title">
      <h2>개인 정보 수정</h2>
    </div>
    <div class="modifyInfo_container">
      <label for="">아이디</label>
      <input type="text" v-model="email" readonly/>
      <label for="">비밀번호</label>
      <input type="text" v-model="pw" class="user-pw"/>
      <label for="">비밀번호 확인</label>
      <input type="text" v-model="pwCheck" class="user-pwCheck"/>
      <label for="">이름</label>
      <input type="text" v-model="name" class="user-name"/>
      <label for="">성별</label>
      <input type="text" v-model="gender" class="user-gender"/>
      <label for="">닉네임</label>
      <input type="text" v-model="nickname" class="user-nickname"/>
      <label for="">탈퇴 방지 문구</label>
      <input type="text" v-model="withdraw_text" class="user-withdraw-text"/>
      <button @click="modifyUser">수정하기</button>
      <button @click="() => { router.push(`/user/${emailParam}`) }">취소하기</button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useUserStore } from "@/stores/userStore.js";
import {useMyPageStore} from "@/stores/myPageStore.js";
import { useRoute} from "vue-router";
import {  useRouter } from "vue-router";


const router = useRouter();
const route = useRoute();
const userStore = useUserStore();
const emailParam = route.params.email;

//새로 수정된 애들
const updateUserPw = computed(() => {
  return document.querySelector('.user-pw');
});
const updateUserPwCheck = computed(() => {
  return document.querySelector('.user-pwCheck');
});
const updateUserName = computed(() => {
  return document.querySelector('.user-name');
});
const updateUserGender = computed(() => {
  return document.querySelector('.user-gender');
});
const updateUserNickname = computed(() => {
  return document.querySelector('.user-nickname');
});
const updateUserWithdrawText = computed(() => {
  return document.querySelector('.user-withdraw-text');
});

const user = computed(() => userStore.user);

//비밀번호 일치하는지 검사
const pwConfirm = computed(() => updateUserPw.value === updateUserPwCheck.value);

// 수정요청
const modifyUser = (() => {
  if (!passwordConfirm()) return;
  console.log("에엥")
  const updateUser = {
    email: user.value.email,
    pw: updateUserPw.value,
    pwCheck:updateUserPwCheck.value,
    name: updateUserName.value,
    gender: updateUserGender.value,
    nickname: updateUserNickname.value,
    withdraw_text: updateUserWithdrawText.value,
  };

  userStore.modifyUser(updateUser);
});

onMounted(()=>{
  userStore.getUserByEmail(emailParam);
});

const passwordConfirm = () => {
  if (!pwConfirm.value) {
    console.log("ADSFSAF");
    alert("비밀번호가 일치하지 않습니다.");
    return true;
  }
  return false;
};
</script>

<style scoped>
.modifyInfo-global {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 150px;
}
</style>