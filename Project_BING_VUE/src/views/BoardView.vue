<template>
  <div>게시글 리스트 등등</div>
  <div>{{ idParam }} 게시판입니다.</div>
  <RouterLink :to="{ name: 'boardDetail', params: { 'board_id': board.board_id } }" v-for="board in commBoardList">{{
    board.title }}
  </RouterLink>
  <RouterView />
</template>

<script setup>
import { RouterView, useRoute } from "vue-router";
import { useBoardStore } from "@/stores/boardStore.js";
import { onMounted, computed } from "vue";
import BoardDetail from "../components/board/BoardDetail.vue";

const route = useRoute();
const idParam = route.params.community_id;

const boardStore = useBoardStore();
const commBoardList = computed(() => {
  return boardStore.commBoardList;
})

console.log(commBoardList.value[0]);
onMounted(() => {
  boardStore.getCommBoardList(idParam);
})
</script>

<style scoped></style>
