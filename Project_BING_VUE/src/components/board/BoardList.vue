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
  display: flex;
  justify-content: center;
  text-align: center;

  padding: 10px;
}
table {
  padding: 10px;
  columns: #f5f5f5;
  width: 100%;
  text-align: center;
  /* border-spacing: 0 5px 0 5px; */
  border-collapse: collapse;
}

th{
  padding: 10px;
background-color: gray;
  color: #f5f5f5;
}


</style>
