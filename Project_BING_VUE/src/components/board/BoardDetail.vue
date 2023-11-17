<template>
  <div>
    <h3>게시글 상세내용</h3>
    {{ idParam }}번 게시글
    <div>댓글</div>
    <div v-for="reply in boardReplyList">{{ reply }}</div>
    <div v-if="isLogin">
      <h3>댓글 작성란</h3>
      <input type="text" v-model="replyContent" />
      <button>등록하기</button>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { computed, onMounted, onUpdated, ref } from "vue";
import { useReplyStore } from "@/stores/replyStore.js";
import { useUserStore } from "@/stores/userStore.js";

// route, store 임포트
const route = useRoute();
const userStore = useUserStore();
const replyStore = useReplyStore();

// idParam : 게시글 id
const idParam = computed(() => route.params.board_id);

// boardRepyList : 댓글 목록
const boardReplyList = computed(() => replyStore.boardReplyList);

// 로그인 체크용 변수
const isLogin = userStore.doLoginCheck();

// 댓글 작성 관련 //
const replyContent = ref("");

onMounted(() => {
  replyStore.getBoardReplyList(idParam.value);
});

onUpdated(() => {
  replyStore.getBoardReplyList(idParam.value);
});
</script>

<style scoped></style>
