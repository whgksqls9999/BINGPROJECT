<template>
  <div class="container">
    <h2>게시글 등록</h2>
    <div class="board-write-form">
      <BoardWriteForm />
    </div>
  </div>
</template>

<script setup>
import BoardWriteForm from "../components/board/BoardWriteForm.vue";
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.container{
  margin-top: 70px;
}
.board-write-form {
  display: flex;
  justify-content: center;
}
</style>
