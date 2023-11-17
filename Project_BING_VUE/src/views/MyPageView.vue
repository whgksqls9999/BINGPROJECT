<template>
  <h2>마이페이지</h2>
  <div>
    <RouterLink :to="{ name: 'myBoards' }">나의 글 목록</RouterLink>
    <RouterLink :to="{ name: 'myFavorite' }">나의 찜 목록</RouterLink>
    <RouterLink :to="{ name: 'myBoards' }">나의 게시글</RouterLink>
  </div>
  <RouterView />
</template>

<script setup></script>

<style scoped></style>
