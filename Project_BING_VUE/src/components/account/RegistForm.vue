<template>
  <div class="regist-container" @click.stop="deleteClick">
    <div class="regist-form">
      <h2></h2>
      <form v-on:submit.prevent>
        <div class="user-box">
          <input type="text" v-model="email" />
          <label for="">Id</label>
        </div>
        <div class="user-box">
          <input type="text" v-model="pw" />
          <label for="">pw</label>
        </div>
        <div class="user-box">
          <input type="text" v-model="pwCheck" />
          <label for="">pwCheck</label>
        </div>
        <div class="user-box">
          <input type="text" v-model="name" />
          <label for="">name</label>
        </div>
        <div class="user-box">
          <input type="text" v-model="gender" />
          <label for="">gender</label>
        </div>
        <div class="user-box">
          <input type="text" v-model="nickname" />
          <label for="">nickname</label>
        </div>
        <div class="user-box">
          <input type="text" v-model="withdraw_text" />
          <label for="">withdraw text</label>
        </div>
        <div class="regist-button">
          <button @click="registUser">
            <span></span>
            <span></span>
            <span></span>
            등록하기
          </button>
          <button @click="closeWindow">
            <span></span>
            <span></span>
            <span></span>
            취소하기
          </button>
        </div>
      </form>
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

const closeWindow = () => {
  userStore.changeForm(0);
}

const deleteClick = (e) => {
  if (e.target.className === "regist-container") {
    closeWindow();
  }
}

// const cancleRegist = () =>{
//   email.value === "",
//   pw.value === "",
//   name.value === "",
//   gender.value === "",
//   nickname.value === "",
//   withdraw_text.value === "",
//   cancleRegist();
// }

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
/* .regist-container {
  width: 100%;
  height: 100vh;
  position: fixed;
  background-color: rgba(128, 128, 128, 0.3);
  top: 0;
  left: 0;
}

.regist-form {
  display: flex;
  flex-direction: column;
  width: 20rem;
  position: fixed;
  top: 15rem;
  left: 20rem;
} */
.regist-container {
  background-color: rgba(128, 128, 128, 0.4);
  width: 100%;
  height: 100vh;
  position: absolute;
  top: 0;
  z-index: 1;
}
.regist-form {
  margin-top: 250px;
  position: fixed;
  top: 10%;
  left: 50%;
  width: 400px;
  padding: 40px;
  transform: translate(-50%, -50%);
  background: rgba(0, 0, 0, 0.7);
  box-sizing: border-box;
  box-shadow: 0 15px 25px rgba(0, 0, 0, 0.6);
  border-radius: 10px;
  z-index: 2;
}

.regist-form h2 {
  margin: 0 0 30px;
  padding: 0;
  color: #fff;
  text-align: center;
}

.regist-form .user-box {
  position: relative;
}

.regist-form .user-box input {
  width: 100%;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  margin-bottom: 30px;
  border: none;
  border-bottom: 1px solid #fff;
  outline: none;
  background: transparent;
}
.regist-form .user-box label {
  position: absolute;
  top: 0;
  left: 0;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  pointer-events: none;
  transition: 0.5s;
}

.regist-form .user-box input:focus ~ label,
.regist-form .user-box input:valid ~ label {
  top: -20px;
  left: 0;
  color: #b6eaec;
  font-size: 12px;
}

.regist-form form button {
  position: relative;
  display: inline-block;
  padding: 10px 20px;
  color: #c0cdce;
  font-size: 16px;
  text-decoration: none;
  text-transform: uppercase;
  overflow: hidden;
  transition: 0.5s;
  margin-top: 40px;
  letter-spacing: 4px;
}

.regist-form button:hover {
  background: #c0cdce;
  color: #fff;
  border-radius: 5px;
  border: 1px solid transparent;
  box-shadow: 0 0 5px #c0cdce, 0 0 25px #c0cdce, 0 0 50px #c0cdce,
    0 0 100px #c0cdce;
}

.regist-form button span {
  position: absolute;
  display: block;
}

.regist-form button span:nth-child(1) {
  top: 0;
  left: -100%;
  width: 100%;
  height: 2px;
  background: linear-gradient(90deg, transparent, #8db4b6);
  animation: btn-anim1 1s linear infinite;
}

@keyframes btn-anim1 {
  0% {
    left: -100%;
  }
  50%,
  100% {
    left: 100%;
  }
}

.regist-form button span:nth-child(2) {
  top: -100%;
  right: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(180deg, transparent, #8db4b6);
  animation: btn-anim2 1s linear infinite;
  animation-delay: 0.25s;
}

@keyframes btn-anim2 {
  0% {
    top: -100%;
  }
  50%,
  100% {
    top: 100%;
  }
}

.regist-form button span:nth-child(3) {
  bottom: 0;
  right: -100%;
  width: 100%;
  height: 2px;
  background: linear-gradient(270deg, transparent, #8db4b6);
  animation: btn-anim3 1s linear infinite;
  animation-delay: 0.5s;
}

@keyframes btn-anim3 {
  0% {
    right: -100%;
  }
  50%,
  100% {
    right: 100%;
  }
}

.regist-form button span:nth-child(4) {
  bottom: -100%;
  left: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(360deg, transparent, #8db4b6);
  animation: btn-anim4 1s linear infinite;
  animation-delay: 0.75s;
}

@keyframes btn-anim4 {
  0% {
    bottom: -100%;
  }
  50%,
  100% {
    bottom: 100%;
  }
}

.regist-button {
  display: flex;
  justify-content: space-around;
}

button {
  border: 1px solid transparent;
  background-color: transparent;
  border-style: none;
  color: white;
  font-family: "Noto Sans KR";
}
</style>
