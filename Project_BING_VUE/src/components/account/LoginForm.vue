<template>
  <!-- <div><h1>로그인 폼</h1></div> -->
  <!-- <div class="container">
    <div class="login-form">
      <div class="title">SIGN IN</div>
        <input type="text" v-model.trim="id" />
        <input type="password" v-model.trim="pw" @keyup.enter="login" />
        <button @click="login">로그인</button>
        <button @click="closeWindow">닫기</button>
    </div>
  </div> -->
  <div class="login-box">
    <h2></h2>
    <form>
      <div class="user-box">
        <input type="text" v-model.trim="id" />
        <label>UserId</label>
      </div>
      <div class="user-box">
        <input type="password" v-model.trim="pw" @keyup.enter="login" />
        <label>Password</label>
      </div>
      <div class="signIn-button">
        <button @click="login">
          <span></span>
          <span></span>
          <span></span>
          로그인
        </button>
        <button @click="closeWindow">
          <span></span>
          <span></span>
          <span></span>
          닫기
        </button>
      </div>
    </form>
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
};
</script>

<style scoped>
.login-box {
  margin-top: 250px;
  position: absolute;
  top: 50%;
  left: 50%;
  width: 400px;
  padding: 40px;
  transform: translate(-50%, -50%);
<<<<<<< HEAD
  background: rgba(0, 0, 0, 0.5);
  box-sizing: border-box;
  box-shadow: 0 15px 25px rgba(0, 0, 0, 0.6);
=======
  background: rgba(0,0,0,.5);
  box-sizing: border-box;
  box-shadow: 0 15px 25px rgba(0,0,0,.6);
>>>>>>> 42ed4eec5e642997ad12a97370ce4e2b9b0d9b2c
  border-radius: 10px;
}

.login-box h2 {
  margin: 0 0 30px;
  padding: 0;
  color: #fff;
  text-align: center;
}

.login-box .user-box {
  position: relative;
}

.login-box .user-box input {
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
.login-box .user-box label {
  position: absolute;
  top:0;
  left: 0;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  pointer-events: none;
  transition: 0.5s;
}

.login-box .user-box input:focus ~ label,
.login-box .user-box input:valid ~ label {
  top: -20px;
  left: 0;
  color: #b6eaec;
  font-size: 12px;
}

.login-box form button {
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
<<<<<<< HEAD
  letter-spacing: 4px;
=======
  letter-spacing: 4px

>>>>>>> 42ed4eec5e642997ad12a97370ce4e2b9b0d9b2c
}

.login-box button:hover {
  background: #c0cdce;
  color: #fff;
  border-radius: 5px;
<<<<<<< HEAD
  box-shadow: 0 0 5px #b4e1e4, 0 0 25px #b4e1e4, 0 0 50px #b4e1e4,
    0 0 100px #b4e1e4;
=======
 border: 1px solid transparent;
  box-shadow: 0 0 5px #c0cdce,
              0 0 25px #c0cdce,
              0 0 50px #c0cdce,
              0 0 100px #c0cdce;
>>>>>>> 42ed4eec5e642997ad12a97370ce4e2b9b0d9b2c
}

.login-box button span {
  position: absolute;
  display: block;
}

.login-box button span:nth-child(1) {
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
  50%,100% {
    left: 100%;
  }
}

.login-box button span:nth-child(2) {
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
  50%,100% {
    top: 100%;
  }
}

.login-box button span:nth-child(3) {
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
  50%,100% {
    right: 100%;
  }
}

.login-box button span:nth-child(4) {
  bottom: -100%;
  left: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(360deg, transparent, #8db4b6);
  animation: btn-anim4 1s linear infinite;
  animation-delay: 0.75s;
}

<<<<<<< HEAD
button {
  background-color: transparent;
  border-style: none;
}

.signIn-button {
  display: flex;
  justify-content: space-around;
}
=======
>>>>>>> 42ed4eec5e642997ad12a97370ce4e2b9b0d9b2c

@keyframes btn-anim4 {
  0% {
    bottom: -100%;
  }
  50%,100% {
    bottom: 100%;
  }
}

<<<<<<< HEAD
=======
.signIn-button{
  display: flex;
  justify-content: space-around;
}

button{
  border:1px solid transparent;
  background-color: transparent;
  border-style: none;
  color: white;
  font-family: 'Noto Sans KR';
}



>>>>>>> 42ed4eec5e642997ad12a97370ce4e2b9b0d9b2c
/* .container {
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
} */
</style>
