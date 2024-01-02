<template>
  <div class="modifyInfo-global">
    <div class="modifyInfo-container">
      <div class="modifyInfo-title">
        <h2>u p d a t e i n f o r m a t i o n</h2>
      </div>
      <div class="modifyInfo_detail">
        <div class="modifyInfo-img">
          <img src="@/assets/군싹.jpeg" />
        </div>
        <div class="modifyInfo-detail-items">
          <div>
            <label for="" id="modifyId"><strong>ID : </strong></label>
            <input type="text" v-model="user.email" readonly />
          </div>
          <div>
            <label for=""><strong>PASSWORD : </strong></label>
            <input type="password" v-model="pw" class="user-pw" />
          </div>
          <div>
            <label for=""><strong>PW CHECK : </strong></label>
            <input type="password" v-model="pwCheck" class="user-pwCheck" />
          </div>
          <div>
            <label for=""><strong>NAME : </strong></label>
            <input type="text" v-model="name" class="user-name" />
          </div>
          <div>
            <label for=""><strong>GENDER : </strong></label>
            <input type="text" v-model="gender" class="user-gender" />
          </div>
          <div>
            <label for=""><strong>NICKNAME : </strong></label>
            <input type="text" v-model="nickname" class="user-nickname" />
          </div>
          <div class="textareazone">
            <label for=""><strong>WITHDRAW TEXT : </strong></label>
            <textarea v-model="withdraw_text" class="user-withdraw-text box1"></textarea>
          </div>
        </div>
      </div>
      <div class="modifyInfo-button-series">
        <div class="modifyInfo-yes">
          <button @click="modifyUser">수정하기</button>
        </div>
        <div class="modifyInfo-no">
          <button @click="() => this.$router.go(-1)">
            취소하기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useUserStore } from "@/stores/userStore.js";
import { useMyPageStore } from "@/stores/myPageStore.js";
import { useRoute } from "vue-router";
import { useRouter } from "vue-router";

const router = useRouter();
const route = useRoute();
const userStore = useUserStore();
const emailParam = ref(route.params.email);

//새로 수정된 애들
// const email = ref('');
const pw = ref("");
const pwCheck = ref("");
const name = ref("");
const gender = ref("");
const nickname = ref("");
const withdraw_text = ref("");

const user = computed(() => userStore.user);
const users = computed(()=>userStore.users);

//비밀번호 일치하는지 검사
const pwConfirm = computed(() => pw.value === pwCheck.value);

const isPwValid = (pw) => {
  return pw.length >= 8 && /[!@#$%^&*(),.?":{}|<>]/g.test(pw);
};
// 수정요청
const modifyUser = () => {
  if (passwordConfirm()) return;

  const updateUser = {
    email: user.value.email,
    pw: pw.value,
    pwCheck: pwCheck.value,
    name: name.value,
    gender: gender.value,
    nickname: nickname.value,
    withdraw_text: withdraw_text.value,
  };

  console.log(updateUser);

  if (!isPwValid(pw.value)) {
    alert('비밀번호는 8자 이상, 특수문자를 포함해야합니다.')
    console.log(pw.value)
    return;
  }

  // if(users.value.length>0){
  //   const userAlready = users.value.some((user)=>user.nickname===nickname.value);
  //   console.log(userAlready);
  //   if(userAlready){
  //     alert('이미 존자하는 닉네임입니다');
  //     console.log(userAlready.value.nickname);
  //     return;
  //   }
  // }

  const userAlready = users.value.some((u)=>u.nickname !== user.value.nickname && u.nickname === nickname.value)
  if(userAlready){
    alert('이미 존재하는 닉네임입니다.')
    return;
  }
  userStore.modifyUser(updateUser);
};

onMounted(() => {
  userStore.getAllUsers();
  emailParam.value = route.params.email;
  userStore.getUserByEmail(emailParam.value);
  //초기값
  // console.log(user.value.email);
  pw.value = user.value.pw;
  name.value = user.value.name;
  gender.value = user.value.gender;
  nickname.value = user.value.nickname;
  withdraw_text.value = user.value.withdraw_text;
  // console.log(user.value.withdraw_text);
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
input {
  color:whitesmoke;
  padding: 8px;
  border-radius: 7px;
  border: none;
  font-size: 18px;
  width: 60%;
  background: rgba(0, 0, 0, 0.7);
}

.modifyInfo-global {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 70px;
  width: 100%;
  height: 100vh;
}

.modifyInfo-container {
  border: 2px solid #dbdbdb;
  color: whitesmoke;
  border-radius: 8px;
  width: 60%;
  background-color: rgba(0, 0, 0, 0.7);
  position: relative;
}

.modifyInfo-title {
  font-size: 25px;
  text-align: center;
  padding: 20px;
  border-bottom: 1px solid #dbdbdb;
}

.modifyInfo_detail {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  padding: 30px;
  gap: 30px;
  /* border-bottom: 1px solid #dbdbdb; */
}

.modifyInfo-img img {
  border-radius: 50%;
  width: 150px;
  height: 150px;
}

.modifyInfo-detail-items {
  display: flex;
  flex-direction: column;
  margin-left: 20px;
}

.modifyInfo-detail-items div {
  margin: 10px 0;
  gap: 10px
}

.modifyInfo-button-series {
  display: flex;
  justify-content: center;
  gap: 20%;
  padding: 15px;
  margin: 10px auto;
}

.modifyInfo-yes button,
.modifyInfo-no button {
  text-decoration: none;
  padding: 10px;
  border-radius: 5px;
  border-style: none;
  color: #262626;
  width: 100%;
  font-weight: bold;
  font-size: 1rem;
}

.modifyInfo-yes button:hover {
  background-color: #aecadb;
  color: white;
}

.modifyInfo-no button:hover {
  color: whitesmoke;
  background-color: rgb(216, 67, 67);
}

textarea {
  color: whitesmoke;
  font-family: "Noto Sans KR";
  border-style: none;
  resize: none;
  font-size: 18px;
  height: 80px;
  width: 70%;
  overflow-y: scroll;
  background-color: rgba(0, 0, 0, 0.7);
}

.textareazone {
  display: flex;
  gap: 10px;
}

.box1::-webkit-scrollbar-thumb {
  background-color: rgba(255, 255, 255, 1);
  /* 스크롤바 둥글게 설정    */
  border-radius: 10px;
  border: 7px solid rgba(0, 0, 0, 0.8);
}

/* 스크롤바 뒷 배경 설정*/
.box1::-webkit-scrollbar-track {
  background-color: rgba(0, 0, 0, 0);
}</style>
