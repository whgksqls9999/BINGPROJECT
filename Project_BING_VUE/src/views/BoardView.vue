<template>
  <div>게시글 리스트 등등</div>
  <div>{{ idParam }} 게시판입니다.</div>
  <div v-for="board in commBoardList">{{ board }}</div>
  <RouterView />
</template>

<script setup>
import { RouterView, useRoute } from "vue-router";
import { useBoardStore } from "@/stores/boardStore.js";
import { onMounted, computed } from "vue";
const route = useRoute();
const idParam = route.params.board_id;
console.log(idParam);


const boardStore = useBoardStore();
const commBoardList = computed(() => {
  return boardStore.commBoardList;
})

onMounted(() => {
  boardStore.getCommBoardList(idParam); 
})
</script>

<style scoped></style>
