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
import { useCommonStore } from "../stores/commonStore";
// Store
const commonStore = useCommonStore();

// 헤더 fixed toggle
onMounted(() => {
  commonStore.toggleHeaderFixed(false);
});
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.container {
  margin-top: 70px;
}
.board-write-form {
  display: flex;
  justify-content: center;
}
</style>
