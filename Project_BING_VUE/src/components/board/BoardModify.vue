<template>
  <div class="board-detail">
    <div class="board-detail-global">
      <div class="board-detail-category">
        <h3>
          {{
            boardOne.community_id === 1
            ? "FREEDIVING"
            : boardOne.community_id === 2
              ? "SKINSCUBA"
              : boardOne.community_id
          }}
        </h3>
        <p>{{ boardOne.header }}</p>
      </div>
      <div class="board-detail-title">
        <input type="text" v-model="title" />
      </div>
      <div class="board-detail-container">
        <div class="board-detail-inform">
          <h4>작성자 : {{ boardOne.writer }}</h4>
          <h4>조회수 : {{ boardOne.view_cnt }}</h4>
          <h4>작성일 : {{ boardOne.reg_date }}</h4>
          <h4>좋아요 수 : {{ boardOne.fav_cnt }}</h4>
        </div>
        <div class="board-detail-content">
          <textarea v-model="content">{{ boardOne.content }}</textarea>
        </div>
        <div class="board-detail-buttons">
          <!-- 이전, 수정, 삭제, 다음 버튼 -->
          <button class="action-button" @click="updateBoard">수정</button>
          <button class="action-button" id="delete" @click="() => this.$router.go(-1)">
            취소
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { computed, onMounted, ref } from "vue";
import { useUserStore } from "@/stores/userStore.js";

import { useBoardStore } from "@/stores/boardStore";

// route, store 임포트
const route = useRoute();
const router = useRouter();
const userStore = useUserStore();
const boardStore = useBoardStore();

//로그인 정보
const loginUser = computed(() => userStore.loginUser);

// idParam : 게시글 id
const idParam = computed(() => route.params.board_id);

// boardOne board_id로 선택해 온 게시글 하나, 의존하는 변수 변할때마다 올려줄거임~!
const boardOne = computed(() => boardStore.boardOne);

//새로 작성되는 글 내용
const content = ref("");
const title = ref("");
//업데이트 요청하기
const updateBoard =  () => {
  const boardData = {
    content: content.value,
    board_id: idParam.value,
    title: title.value,
  };
  boardOne.reg_date = boardOne.is_modified;
   boardStore.updateBoard(boardData);
  router.push({
    name: "boardDetail",
    params: { community_id: boardOne.community_id, board_id: idParam.value },
  });
};

onMounted(async () => {
  await boardStore.getBoard(idParam.value);
  userStore.getUserEmail();
  if (loginUser.value !== '') {
    await userStore.getUserByEmail(loginUser.value.email);
  }
  content.value = boardOne.value.content;
  title.value = boardOne.value.title;
  // console.log("Content set in onMounted:", content.value);
});
</script>

<style scoped>
textarea {
  padding: 1rem;
  border-radius: 30px;
  resize: none;
  width: 100%;
  height: 100%;
  border-style: none;
  font-size: 18px;
  font-family: "Noto Sans KR";
}

textarea:focus {
  outline: none;
  border: 2px solid black;
}

.board-detail {
  margin-top: 120px;
  text-align: center;
}

.board-detail-global {
  border: 3px solid black;
  border-radius: 20px;
  padding: 10px 20px;
  width: 70%;
  margin: auto;
}

.board-detail-container {
  border: 1px solid #ccc;
  padding: 20px;
  margin-top: 20px;
}

.board-detail-content {
  overflow: hidden;
  font-size: 1.2rem;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 2px solid rgb(168, 165, 165);
  border-radius: 30px;
  width: 100%;
  height: 30vh;
  margin-bottom: 20px;
}

.board-detail-inform {
  border: 2px solid rgb(146, 142, 142);
  border-radius: 10px;
  padding: 10px;
  margin-bottom: 10px;
  display: flex;
  justify-content: space-around;
}

.board-detail-buttons {
  margin-top: 10px;
}

button {
  border-style: none;
  border-radius: 20px;
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.2);
}

.action-button {
  background-color: black;
  font-weight: bold;
  color: white;
  padding: 8px 16px;
  margin-right: 5px;
  cursor: pointer;
}

.action-button:hover {
  background-color: rgba(255, 255, 255, 0.7);
  color: black;
}

#delete:hover {
  background-color: rgb(216, 67, 67);
  color: white;
}
</style>
