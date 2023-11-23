<template>
  <!--댓글 게시글 한번에-->
  <div class="myboard-global">
    <!--게시글-->
    <div class="myboard-title">
      <div class="myboard-container">
        <div class="myboard-container-title">
          <h2>✍ {{ user.nickname }}님의 게시글 목록</h2>
          <br />
        </div>
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
                  @click="
                    async () => {
                      await boardStore.updateViewCnt(board.board_id);
                    }
                  "
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
      <div class="myreply-container">
        <div class="myreply-container-title">
          <h2>📋 {{ user.nickname }}님의 댓글 목록</h2>
          <br />
        </div>
        <h3 v-if="myReplys.length == 0">작성한 댓글 목록이 없습니다.</h3>
        <table class="myreply-table" v-else>
          <thead>
            <tr>
              <th>번호</th>
              <th>내용</th>
              <th>작성일</th>
            </tr>
          </thead>
          <tbody v-for="reply in myReplys" :key="reply.writer">
            <tr>
              <td>{{ reply.reply_id }}</td>
              <td>
                <RouterLink
                  :to="{
                    name: 'boardDetail',
                    params: {
                      community_id: reply.community_id,
                      board_id: reply.board_id,
                    },
                  }"
                  >{{ reply.content }}</RouterLink
                >
              </td>
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
import UserInfo from "../account/UserInfo.vue";

// store, route
const boardStore = useBoardStore();
const commonStore = useCommonStore();
const userStore = useUserStore();
const route = useRoute();

// 유저 닉네임 받아오기
const emailParam = route.params.email;
const store = useMyPageStore();

// 내 글 목록
const myBoards = computed(() => {
  return store.myBoards;
});

// 내 댓글 목록
const myReplys = computed(() => {
  return store.myReplys;
});

// 내 정보
const user = computed(() => userStore.user);

// 내 닉네임
const nickname = computed(() => {
  return userStore.user.nickname;
});

onMounted(async () => {
  await userStore.getUserByEmail(emailParam);
  store.getMyBoards(user.value.nickname);
  store.getMyReplys(user.value.nickname);
  commonStore.toggleHeaderFixed(false);
});
</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
}
.myboard-global {
  margin-top: 40px;
  display: grid;
  grid-template-rows: 1fr 1fr;
  row-gap: 50px;
}
h2 {
  font-family: Orbit;
}
.myboard-container,
.myreply-container {
  width: 100%;
  background-color: rgba(245, 245, 245, 0.8);
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 7px;
  border-radius: 20px;
  overflow-y: scroll;
  max-height: 400px;
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
