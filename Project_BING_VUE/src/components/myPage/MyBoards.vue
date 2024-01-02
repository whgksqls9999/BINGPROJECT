<template>
  <!--댓글 게시글 한번에-->
  <div class="myboard-global">
    <!--게시글-->
    <div class="myboard-title">
      <div class="myboard-container-title">
        <h2>my post</h2>
        <br />
      </div>
      <div class="myboard-container">
        <h3 v-if="myBoards.length == 0">작성한 게시글 목록이 없습니다.</h3>
        <table class="myboard-table" v-else>
          <thead>
            <tr>
              <th>커뮤니티</th>
              <th>번호</th>
              <th>제목</th>
              <th>조회수</th>
              <th>작성일</th>
            </tr>
          </thead>
          <tbody v-for="board in myBoards" :key="board.num">
            <tr>
              <td>
                {{ board.community_id == 1 ? "freediving" : "skinscuba" }}
              </td>
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
      <div class="myreply-container-title">
        <h2>reply</h2>
        <br />
      </div>
      <div class="myreply-container">
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
import { onMounted, computed } from "vue";
import { useMyPageStore } from "@/stores/myPageStore.js";
import { useUserStore } from "@/stores/userStore";
import { useRoute } from "vue-router";
import { useBoardStore } from "@/stores/boardStore";
import { useCommonStore } from "@/stores/commonStore";

// store, route
const boardStore = useBoardStore();
const commonStore = useCommonStore();
const userStore = useUserStore();
const route = useRoute();

// 유저 닉네임 받아오기
const emailParam = route.params.email;
const store = useMyPageStore();

// 내 글 목록
const myBoards = computed(() => store.myBoards);

// 내 댓글 목록
const myReplys = computed(() => store.myReplys);

// 내 정보
const user = computed(() => userStore.user);

// 내 닉네임
const nickname = computed(() => userStore.user.nickname);

onMounted(() => {
  userStore.getUserByEmail(emailParam).then(() => {
    store.getMyBoards(user.value.nickname);
    store.getMyReplys(user.value.nickname);
  })
  commonStore.toggleHeaderFixed(false);
});
</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
}
.myboard-global {
  width: 60%;
  margin-top: 70px;
  display: grid;
  grid-template-rows: 1fr 1fr;
  row-gap: 50px;
  height: 120vh;
}
h2 {
  font-family: Orbit;
}
.myboard-container,
.myreply-container {
  width: 100%;
  background-color: rgba(245, 245, 245, 0.8);
  display: flex;
  justify-content: center;
  padding: 7px;
  border-bottom-left-radius: 20px;
  overflow-y: scroll;
  max-height: 400px;
}

.myreply-container-title,
.myboard-container-title {
  background-color: rgba(0, 0, 0, 0.7);
  padding-top: 10px;
  padding-left: 20px;
  color: whitesmoke;
  border-top-right-radius: 20px;
  border-top-left-radius: 20px;
}

.myboard-table,
.myreply-table {
  color: rgb(63, 60, 60);
  font-size: 1.1rem;
  font-weight: 500;
  text-align: center;
  border-spacing: 3rem;
  width: 100%;
}
</style>
