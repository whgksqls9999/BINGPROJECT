<template>

  <div class="board-detail">
    <div class="board-detail-global">
      <h3>{{ boardOne.community_id === 1 ? 'FREEDIVING' : (boardOne.community_id === 2 ? 'SKINSCUBA' :
        boardOne.community_id) }}</h3>
      <p>{{ boardOne.header }}</p>
      <div class="board-detail-container">
        <div class="board-detail-inform">
          <h4>작성자 : {{ boardOne.writer }}</h4>
          <h4>조회수 : {{ boardOne.view_cnt }}</h4>
          <h4>작성일 : {{ boardOne.reg_date }}</h4>
          <h4>좋아요 수 : {{ boardOne.fav_cnt }}</h4>
        </div>
        <div class="board-detail-content">
          <p>{{ boardOne.content }}</p>
        </div>
        <div class="board-detail-buttons">
          <!-- 이전, 수정, 삭제, 다음 버튼 -->
          <button class="nav-button">이전</button>
          <button class="action-button">수정</button>
          <button class="action-button" id="delete">삭제</button>
          <button class="nav-button">다음</button>
        </div>
      </div>
      <div class="board-detail-reply-container">
        <div class="board-detail-reply-title">
          <h3>댓글</h3>
        </div>
        <div class="board-detail-table">
          <table>
            <thead>
              <tr>
                <th>번호</th>
                <th>내용</th>
                <th>작성자</th>
                <th>작성일</th>
              </tr>
            </thead>
            <tbody v-for="reply in boardReplyList" :key="reply.id" class="reply-item">
              <tr>
                <td>{{ reply.reply_id }}</td>
                <td>{{ reply.content }}</td>
                <td>{{ reply.writer }}</td>
                <td>{{ reply.reg_date }}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div v-if="isLogin" class="comment-container">
          <h3>댓글 작성란</h3>
          <div class="comment-input">
            <input type="text" v-model="replyContent" />
            <button @click="submitReply">등록하기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { computed, onMounted, onUpdated, ref } from "vue";
import { useReplyStore } from "@/stores/replyStore.js";
import { useUserStore } from "@/stores/userStore.js";

import { useBoardStore } from "@/stores/boardStore";

import { useFavStore } from "@/stores/favStore.js";


// route, store 임포트
const route = useRoute();
const userStore = useUserStore();
const replyStore = useReplyStore();

const boardStore = useBoardStore();
const favStore = useFavStore();

// 게시글 찜하기
const doFavBoard = () => {
  let favBoard = {
    favorite_boardId: 0,
    board_id: idParam.value,
    writername: user.value.nickname,
  };

  favStore.doFavBoard(favBoard, user.value.nickname);
};

// 게시글 찜 취소하기
const doFavCancel = () => {
  favStore.doFavBoardCancel(
    isFavored.value,
    user.value.nickname,
    idParam.value
  );
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
// boardOne board_id로 선택해 온 게시글 하나, 의존하는 변수 변할때마다 올려줄거임~!
const boardOne = computed(() => boardStore.boardOne);
//글 작성자 맞으면 수정 삭제 버튼 띄우기위해서 user 불러오기
const user = computed(() => userStore.user);
// 로그인 체크용 변수
const isLogin = userStore.doLoginCheck();

// 댓글 작성 관련 //
const replyContent = ref("");
//게시글 관련


onMounted(async () => {
  replyStore.getBoardReplyList(idParam.value);
  boardStore.getBoard(idParam.value);
});
  await userStore.getUserByEmail(userStore.loginUser.email);
  await favStore.getFavBoardList(userStore.user.nickname);
  userStore.isFavored = await favStore.doFavorCheck(
    user.value.nickname,
    idParam.value
  );
});

// onUpdated(() => {
//   replyStore.getBoardReplyList(idParam.value);
// });
</script>

<style scoped>
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
  min-height: 90vh;
}

.board-detail-container {
  border: 1px solid #ccc;
  padding: 20px;
  margin-top: 20px;
}

.board-detail-content {
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
  box-shadow: 0 0 6px rgba(0, 0, 0, .2);
}

.nav-button {
  background-color: rgba(173, 202, 219, .7);
  color: white;
  font-weight: bold;
  padding: 8px 16px;
  margin-right: 5px;
  cursor: pointer;
}

.nav-button:hover {
  background-color: #cfddd5;
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

.reply-item {
  margin-top: 10px;
}

.board-writer {
  margin-top: 20px;
  font-style: italic;
  color: #555;
}

.board-detail-table {
  padding: 7px;
  margin-bottom: 7px;
  text-align: center;
  display: flex;
  justify-content: space-evenly;
}

.board-detail-reply-title {
  padding: 10px;

}

table {
  /* text-align: center; */
  border: 1px #a39485 solid;
  font-size: .9em;
  box-shadow: 0 2px 5px rgba(0, 0, 0, .25);
  width: 100%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;
}

th {
  text-align: left;
}

thead {
  font-size: 15px;
  font-weight: bold;
  color: #fff;
  background-color: rgba(173, 202, 219, .7);
}

td,
th {
  padding: 1em;
  vertical-align: middle;
}

td {
  border-bottom: 1px solid rgba(0, 0, 0, .1);
  padding-left: 1em;
  background: #ffffff;
}

@media all and (max-width: 768px) {

  table,
  thead,
  tbody,
  th,
  td,
  tr {
    display: block;
  }

  th {
    text-align: right;
  }

  table {
    position: relative;
    padding-bottom: 0;
    border: none;
    box-shadow: 0 0 10px rgba(0, 0, 0, .2);
  }

  thead {
    float: left;
    white-space: nowrap;
  }

  tbody {
    overflow-x: auto;
    overflow-y: hidden;
    position: relative;
    white-space: nowrap;
  }

  tr {
    display: inline-block;
    vertical-align: top;
  }

  th {
    border-bottom: 1px solid #a39485;
  }

  td {
    border-bottom: 1px solid #e5e5e5;
  }


}

.comment-container {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.comment-container input {
  border-radius: 14px;
  width: 80%;
  height: 30px;
  font-size: 18px;
}

input {
  margin-right: 27px;
}


.comment-input {
  margin: 10px;
}

.comment-input button {
  font-size: 16px;
  font-weight: 600;
  background-color: rgba(173, 202, 219, .7);
  width: 12%;
  color: #ffffff;
  height: 32px;
}
.comment-input button:hover{
  background-color: white;
  color: black;
}

</style>
<!-- .comment-container {
  max-height: 200px; /* 댓글 입력 컨테이너의 최대 높이 설정 */
  overflow-y: auto; /* 세로 스크롤을 추가하여 내용이 넘칠 경우 스크롤이 생성되도록 설정 */
} -->
