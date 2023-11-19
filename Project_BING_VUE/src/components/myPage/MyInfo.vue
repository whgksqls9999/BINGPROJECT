<template>
  <div class="myInfo-global">
    <div class="myInfo-container">
      <div class="myInfo-title">
        <h2>{{ nicknameParam }}님의 정보</h2>
      </div>
      <div class="myInfo-detail">
        <div class="myInfo-img">
          <img src="@/assets/군싹.jpeg" />
        </div>
        <div class="myInfo-detail-series">
          <div><strong>아이디:</strong> {{ user.email }}</div>
          <div><strong>이름:</strong> {{ user.name }}</div>
          <div><strong>닉네임:</strong> {{ user.nickname }}</div>
          <div><strong>성별:</strong> {{ user.gender }}</div>
        </div>
      </div>
      <div class="myInfo-series">
        <div class="myInfo-link">
          <RouterLink :to="{ name: 'modifyInfo' }">내 정보 수정하기</RouterLink>
        </div>
        <div class="myInfo-withdraw">
          <RouterLink :to="{ name: 'withdraw' }">해빙 탈퇴하기</RouterLink>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { useUserStore } from "@/stores/userStore.js";
import { computed, onMounted } from "vue";

// 유저 닉네임 받아오기
const route = useRoute();
const nicknameParam = route.params.nickname;

// 유저 정보 가져오기
const userStore = useUserStore();
const user = computed(() => userStore.user);

onMounted(() => {
  userStore.getUser(nicknameParam);
});
</script>

<style scoped>
/* @font-face {
  font-family: "DungGeunMo";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/DungGeunMo.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
} */
.myInfo-global {
  border: 3px solid black;
  border-radius: 30px;
  padding: 20px;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 150px;
  height: 50vh;
  width: 50vh;
}

/* .myInfo-container {
  background: linear-gradient(
    0deg,
    rgba(200, 209, 209, 0.7) 0%,
    rgba(65, 133, 138, 0.7) 100%
  );
  border-radius: 30px;
  padding: 20px;
  text-align: center;
} */
img {
  border-radius: 50%;
  width: 100px;
}
.myInfo-detail-series {
  letter-spacing: 3px;
  font-size: 20px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.myInfo-title {
  font-size: 24px;
  /* color: white; */
}

.myInfo-detail {
  padding: 15px;
  border: 3px solid rgba(128, 123, 123, 0.5);
  border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px;
  gap: 2.5rem;
  font-weight: 700;
}

.myInfo-detail div {
  margin-bottom: 10px;
}
.myInfo-series {
  display: flex;
  justify-content: center;
}

.myInfo-link {
  margin-right: 10px;
  margin-top: 20px;
}

.myInfo-link a {
  text-decoration: none;
  padding: 10px;
  background-color: #333;
  color: white;
  border-radius: 5px;
}

.myInfo-link a:hover {
  background-color: #555;
}
.myInfo-withdraw {
  margin-top: 20px;
}

.myInfo-withdraw a {
  text-decoration: none;
  padding: 10px;
  background-color: #e02b2b;
  color: white;
  border-radius: 5px;
}

.myInfo-withdraw a:hover {
  background-color: #ff0000;
}
</style>
