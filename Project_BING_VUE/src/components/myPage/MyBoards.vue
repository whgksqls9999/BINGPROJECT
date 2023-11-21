<template>
  <!--댓글 게시글 한번에-->
  <div class="myboard-global">
    <!--게시글-->
    <div class="myboard-title">
      <h2>✍ {{ nickname }}님의 게시글 목록</h2>
      <br />
      <div class="myboard-container">
        <h3 v-if="myBoards.length == 0">작성한 게시글 목록이 없습니다.</h3>
        <table class="myboard-table" v-else>
          <thead>
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>조회수</th>
              <th>작성일</th>
            </tr>
          </thead>
          <tbody v-for="board in myBoards" :key="board.num">
            <tr>
              <td>{{ board.num }}</td>
              <!-- <td>{{ board.title }}</td> -->
              <td>
                <RouterLink
                  :to="{
                    name: 'boardDetail',
                    params: {
                      community_id: board.community_id,
                      board_id: board.board_id,
                    },
                  }"
                  @click="async () => {await boardStore.updateViewCnt(board.board_id)}"
                  >{{ board.title }}</RouterLink
                >
              </td>
              <td>{{ board.view_cnt }}</td>
              <td>{{ board.reg_date }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <!--댓글-->
    <div class="myreply-title">
      <h2>📋 {{ nickname }}님의 댓글 목록</h2>
      <br />
      <div class="myreply-container">
        <h3 v-if="myReplys.length == 0">작성한 댓글 목록이 없습니다.</h3>
        <table class="myreply-table" v-else>
          <thead>
            <tr>
              <th>번호</th>
              <th>내용</th>
              <th>작성자</th>
              <th>작성일</th>
            </tr>
          </thead>
          <tbody v-for="reply in myReplys" :key="reply.writer">
            <tr>
              <td>{{ reply.reply_id }}</td>
              <td>
                <RouterLink
                  :to="{
                    name: 'replyDetail',
                    params: {
                      board_id: reply.board_id,
                      reply_id: reply.reply_id,
                    },
                  }"
                  >{{ reply.content }}</RouterLink
                >
              </td>
              <td>{{ reply.writer }}</td>
              <td>{{ reply.reg_date }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, computed, ref } from "vue";
import { useMyPageStore } from "@/stores/myPageStore.js";
import { useUserStore } from "@/stores/userStore";
import { useRoute } from "vue-router";
import { useBoardStore } from "@/stores/boardStore";
import { useCommonStore } from "@/stores/commonStore";

const boardStore = useBoardStore();

// 헤더 fixed toggle
const commonStore = useCommonStore();

// 유저 닉네임 받아오기
const route = useRoute();
const userStore = useUserStore();
const emailParam = route.params.email;

const store = useMyPageStore();
const myBoards = computed(() => {
  return store.myBoards;
});

const myReplys = computed(() => {
  return store.myReplys;
});

const nickname = computed(() => {
  return userStore.user.nickname;
});

onMounted(() => {
  userStore.getUserByEmail(emailParam);
  store.getMyBoards(nickname.value);
  store.getMyReplys(nickname.value);
  commonStore.toggleHeaderFixed(false);
});
</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
}
.myboard-global {
  margin-top: 150px;
  display: grid;
  grid-template-rows: 1fr 1fr;
  row-gap: 100px;
}
h2 {
  font-family: Orbit;
}
.myboard-container,
.myreply-container {
  display: flex;
  justify-content: center;
  padding: 10px;
  /* border-radius: 30px; */
  border-top: 3px solid black;
  border-left: 3px solid black;
}
.myboard-table,
.myreply-table {
  color: rgb(63, 60, 60);
  font-size: 1.3rem;
  font-weight: 500;
  text-align: center;
  border-spacing: 1.3rem;
}
</style>
