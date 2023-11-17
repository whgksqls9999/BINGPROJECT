<template>
  <h2>마이페이지</h2>
  <div>
    <RouterLink :to="{ name: 'myInfo' }">나의 정보</RouterLink>
    <RouterLink :to="{ name: 'myBoards' }">나의 글 목록</RouterLink>
    <RouterLink :to="{ name: 'myFavorite' }">나의 찜 목록</RouterLink>
  </div>
  <RouterView />
</template>

<script setup>
import { useRoute } from "vue-router";

const route = useRoute();
const nicknameParam = route.params.nickname;
</script>

<style scoped></style>
