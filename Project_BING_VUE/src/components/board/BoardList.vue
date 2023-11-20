<template>
  <div class="board-list-container">
    <table>
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>글쓴이</th>
          <th>작성일</th>
          <th>조회수</th>
          <th>찜</th>
        </tr>
      </thead>
      <tbody>
        <BoardListItem v-for="board in boardList" :board="board" />
      </tbody>
    </table>
  </div>
</template>

<script setup>
import BoardListItem from "@/components/board/BoardListItem.vue";

const props = defineProps({
  boardList: Array,
});
</script>

<style scoped>
.board-list-container {
  width: 50rem;
}
table {
  width: 100%;
  text-align: center;
}
</style>
