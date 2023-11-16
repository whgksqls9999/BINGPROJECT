<template>
  <div>커뮤니티 선택</div>
  <RouterLink :to="{ name: 'board', params: { board_id: '1' } }"
    >프리다이빙</RouterLink
  >
  <RouterLink :to="{ name: 'board', params: { board_id: '2' } }"
    >스킨스쿠버</RouterLink
  >
</template>

<script setup></script>

<style scoped></style>
