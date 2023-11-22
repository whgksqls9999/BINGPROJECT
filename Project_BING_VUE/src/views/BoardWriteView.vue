<template>
  <div class="write-view-container">
    <div class="title">게시글 등록</div>
    <BoardWriteForm />
  </div>
</template>

<script setup>
import BoardWriteForm from "../components/board/BoardWriteForm.vue";
import { useCommonStore } from "../stores/commonStore";
import { onMounted } from "vue";
// Store
const commonStore = useCommonStore();

// 헤더 fixed toggle
onMounted(() => {
  commonStore.toggleHeaderFixed(false);
  commonStore.toggleFooterFixed(true);
});
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.write-view-container {
  margin-top: 140px;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.title {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 0.3rem;
  animation: fadein 1s;
  width: 40rem;
}

@keyframes fadein {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>
