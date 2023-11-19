<template>
  <!--댓글 게시글 한번에-->
  <div class="myboard-global">
    <!--게시글-->
    <div class="myboard-title">
      <h2>{{ nicknameParam }}님의 게시글 목록</h2>
      <br />
      <div class="myboard-container">
        <h5 v-if="myBoards.length == 0">작성한 게시글 목록이 없습니다.</h5>
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
              <!--나중에 boarddetail구현하고 만들것 <td><RouterLink :to="{name: 'BoardDetail', params:{id: board.num}}">{{ board.title }}</RouterLink></td> -->
              <td>{{ board.title }}</td>
              <td>{{ board.view_cnt }}</td>
              <td>{{ board.reg_date }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <!--댓글-->
    <div class="myreply-title">
      <h2>{{ nicknameParam }}님의 댓글 목록</h2>
      <br />
      <div class="myreply-container">
        <h5 v-if="myReplys.length == 0">작성한 댓글 목록이 없습니다.</h5>
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
              <td>{{ reply.content }}</td>
              <!--나중에 replydetail구현하고 만들것 <td><RouterLink :to="{name: 'ReviewDetail', params:{id: reply.num}}">{{ reply.title }}</RouterLink></td> -->
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
import { useRoute } from "vue-router";

// 유저 닉네임 받아오기
const route = useRoute();
const nicknameParam = route.params.nickname;

const store = useMyPageStore();
const myBoards = computed(() => {
  return store.myBoards;
});

const myReplys = computed(() => {
  return store.myReplys;
});

onMounted(() => {
  store.getMyBoards(nicknameParam);
  store.getMyReplys(nicknameParam);
});
</script>

<style scoped>
.myboard-global {
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
  border-radius: 30px;
  background: linear-gradient(
    0deg,
    rgba(200, 209, 209, 0.7) 0%,
    rgba(65, 133, 138, 0.7) 100%
  );
}
.myboard-table,
.myreply-table {
  color: white;
  font-size: 1.3rem;
  font-weight: 500;
  text-align: center;
  border-spacing: 1.3rem;
}
</style>
