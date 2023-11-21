<template>
  <div class="board-detail-container">
    <h3>게시글 상세내용</h3>
    {{ idParam }}번 게시글 {{}}
    <div>댓글</div>
    <div v-for="reply in boardReplyList">{{ reply }}</div>
    <div v-if="isLogin">
      <h3>댓글 작성란</h3>
      <input type="text" v-model="replyContent" />
      <button>등록하기(기능 아직 구현안함)</button>
      <button @click="doFavBoard" v-if="isFavored == null">찜하기</button>
      <button @click="doFavCancel" v-else>찜취소하기</button>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { computed, onMounted, onUpdated, ref } from "vue";
import { useReplyStore } from "@/stores/replyStore.js";
import { useUserStore } from "@/stores/userStore.js";
import { useFavStore } from "@/stores/favStore.js";

// route, store 임포트
const route = useRoute();
const userStore = useUserStore();
const replyStore = useReplyStore();
const favStore = useFavStore();

// 게시글 찜하기
const doFavBoard = () => {
  let favBoard = {
    favorite_boardId: 0,
    board_id: idParam.value,
    writername: user.value.nickname,
  };

  favStore.doFavBoard(favBoard, user.value.nickname);

  console.log(isFavored.value);
};

// 게시글 찜 취소하기
const doFavCancel = () => {
  favStore.doFavBoardCancel();
};

// favBoardList : 해당 유저의 찜 게시글 목록
const favBoardList = computed(() => favStore.favBoardList);

// 찜 여부 체크
const isFavored = computed(() => favStore.isFavored);

// 유저 정보
const user = computed(() => userStore.user);

// idParam : 게시글 id
const idParam = computed(() => route.params.board_id);

// boardRepyList : 댓글 목록
const boardReplyList = computed(() => replyStore.boardReplyList);

// 로그인 체크용 변수
const isLogin = userStore.doLoginCheck();

// 댓글 작성 관련 //
const replyContent = ref("");

onMounted(async () => {
  replyStore.getBoardReplyList(idParam.value);
  await userStore.getUserByEmail(userStore.loginUser.email);
  await favStore.getFavBoardList(userStore.user.nickname);
  userStore.isFavored = await favStore.doFavorCheck(
    user.value.nickname,
    idParam.value
  );
});

onUpdated(() => {
  replyStore.getBoardReplyList(idParam.value);
});
</script>

<style scoped>
.board-detail-container {
  margin-top: 70px;
}
</style>
