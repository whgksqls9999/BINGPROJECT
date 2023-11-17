<template>
  <div>
    <h2>내 정보</h2>
    {{ nicknameParam }}님의 정보입니다.
    <div>
      {{ user }}
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

<style scoped></style>
