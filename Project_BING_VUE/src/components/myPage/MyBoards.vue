<template>
  <!--댓글 게시글 한번에-->
  <div>
    <!--게시글-->
    <div>
      <h5 v-if="myBoards.length == 0">작성한 게시글 목록이 없습니다.</h5>
      <table v-else>
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
    <!--댓글-->
    <div>
      <h5 v-if="myReplys.length == 0">작성한 댓글 목록이 없습니다.</h5>
      <table v-else>
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
            <td>{{ reply.num }}</td>
            <td>{{ reply.content }}</td>
            <!--나중에 replydetail구현하고 만들것 <td><RouterLink :to="{name: 'ReviewDetail', params:{id: reply.num}}">{{ reply.title }}</RouterLink></td> -->
            <td>{{ reply.writer }}</td>
            <td>{{ reply.reg_date }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { onMounted, computed, ref } from "vue";
import { useMyPageStore } from "@/stores/myPageStore.js";
import { useRoute } from "vue-router";
const store = useMyPageStore();
const myBoards = computed(() => {
  return store.myBoards;
});

const myReplys = computed(() => {
  return store.myReplys;
});

onMounted(() => {
  store.getMyBoards();
  store.getMyReplys();
});
</script>

<style scoped></style>
