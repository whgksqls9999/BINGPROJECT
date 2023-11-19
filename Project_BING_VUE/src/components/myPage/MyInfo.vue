<template>
  <div class="myInfo-global">
    <div class="myInfo-container">
      <div class="myInfo-title">
        <h2>내 정보</h2>
      </div>
      {{ nicknameParam }}님의 정보입니다.
      <div class="myInfo-detail">
        <div>아이디 : {{ user.email }}</div>
        <div>이름 : {{ user.name }}</div>
        <div>닉네임 : {{ user.nickname }}</div>
        <div>성별 : {{ user.gender }}</div>
      </div>
      <div>
        <RouterLink :to="{ name: modifyInfo }">내 정보 수정하기</RouterLink>
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
.myInfo-title {
  padding: 10px;
}
.myInfo-global {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50vh; /* 100% 높이로 설정하여 화면 중앙에 정렬 */
}
</style>
