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
        <h3>{{ boardOne.title }}</h3>
      </div>
      <div class="board-detail-container">
        <div class="board-detail-inform">
          <!-- <h4>: {{ boardOne.writer }}</h4> -->
          <h4>작성자 : {{ boardOne.writer }}</h4>
          <h4>조회수 : {{ boardOne.view_cnt }}</h4>
          <h4>작성일 : {{ boardOne.reg_date }}</h4>
          <h4>좋아요 수 : {{ boardOne.fav_cnt }}</h4>
        </div>
        <div class="board-detail-content">
          <p>{{ boardOne.content }}</p>
        </div>
        <div
          class="board-detail-location"
          v-if="boardOne.header === '장소추천'"
        >
          <!--지도뿌리기-->
          <BoardDetailMap :location="boardOne.location_id" />
          <!-- <img src="@/assets/PHOTO_0057.jpg" /> -->
        </div>
        <div class="board-detail-buttons">
          <!-- 이전, 수정, 삭제, 다음 버튼 -->
          <button class="nav-button">이전</button>
          <button
            class="action-button"
            @click="boardModifyPush"
            v-if="isWriter"
          >
            수정
          </button>
          <button
            class="action-button"
            id="delete"
            @click="boardDelete"
            v-if="isWriter"
          >
            삭제
          </button>
          <span v-if="isLogin != null">
            <button
              v-if="isFavored == ''"
              @click="doFavBoard"
              class="fav-button"
            >
              좋아요
            </button>
            <button v-else @click="doFavCancel" class="fav-cancel-button">
              좋아요
            </button>
          </span>
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
                <th></th>
              </tr>
            </thead>
            <tbody
              v-for="reply in boardReplyList"
              :key="reply.id"
              class="reply-item"
            >
              <tr>
                <td>{{ reply.reply_id }}</td>
                <td>{{ reply.content }}</td>
                <td>{{ reply.writer }}</td>
                <td>{{ reply.reg_date }}</td>
                <div class="reply-button-item">
                  <button class="button1" @click="modifyReply">수정</button>
                  <button class="button2" @click="removeReply(reply)">
                    삭제
                  </button>
                </div>
              </tr>
            </tbody>
          </table>
        </div>
        <div v-if="isLogin" class="comment-container">
          <h3>댓글 작성란</h3>
          <div class="comment-input">
            <input type="text" v-model="replyContent" />
            <button @click="registReply">등록하기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { computed, onMounted, onUpdated, ref } from "vue";
import { useReplyStore } from "@/stores/replyStore.js";
import { useUserStore } from "@/stores/userStore.js";
import { useBoardStore } from "@/stores/boardStore";
import { useFavStore } from "@/stores/favStore.js";
import { useCommonStore } from "@/stores/commonStore";
import BoardDetailMap from "@/components/board/include/BoardDetailMap.vue";
// Store
const commonStore = useCommonStore();

// route, store 임포트
const route = useRoute();
const router = useRouter();
const userStore = useUserStore();
const replyStore = useReplyStore();
const boardStore = useBoardStore();
const favStore = useFavStore();

// 게시글 찜하기
const doFavBoard = async () => {
  let favBoard = {
    favorite_boardId: 0,
    board_id: idParam.value,
    writername: user.value.nickname,
    community_id: boardOne.value.community_id,
  };

  favStore.doFavBoard(favBoard, user.value.nickname);
  boardOne.value.fav_cnt++;
  await boardStore.updateBoard(boardOne.value);
  boardStore.getBoard(idParam.value);
};

// 게시글 찜 취소하기
const doFavCancel = async () => {
  favStore.doFavBoardCancel(
    isFavored.value,
    user.value.nickname,
    idParam.value
  );
  boardOne.value.fav_cnt--;
  await boardStore.updateBoard(boardOne.value);
  boardStore.getBoard(idParam.value);
};
// 로그인 체크용 변수
const isLogin = computed(() => userStore.isLogin);

// favBoardList : 해당 유저의 찜 게시글 목록
const favBoardList = computed(() => favStore.favBoardList);

// 찜 여부 체크
const isFavored = computed(() => favStore.isFavored);

// idParam : 게시글 id
const idParam = computed(() => route.params.board_id);

// boardRepyList : 댓글 목록
const boardReplyList = computed(() => replyStore.boardReplyList);

// boardOne board_id로 선택해 온 게시글 하나, 의존하는 변수 변할때마다 올려줄거임~!
const boardOne = computed(() => boardStore.boardOne);

//글 작성자 맞으면 수정 삭제 버튼 띄우기위해서 user 불러오기
const user = computed(() => userStore.user);

// 글 작성자 체크
const isWriter = ref(false);

// const reply = computed(() => replyStore.reply);
//수정 누르면 수정 form으로 이동
const boardModifyPush = () => {
  router.push({
    name: "boardModify",
    params: {
      community_id: boardOne.community_id,
      board_id: idParam.value,
    },
  });
};

const boardDelete = () => {
  boardStore.deleteBoard(idParam.value);
};

// 댓글 작성 관련 //
const replyContent = ref("");

//댓글 작성하기
const registReply = () => {
  const reply = {
    board_id: boardOne.value.board_id,
    community_id: boardOne.value.community_id,
    writer: user.value.nickname,
    content: replyContent.value,
    reg_date: new Date().toISOString(),
  };

  replyStore.registReply(reply);
  replyContent.value = "";
};

//댓글 삭제하기
const removeReply = (reply) => {
  console.log(reply.reply_id);
  replyStore.removeReply(reply.reply_id);
  replyStore.getBoardReplyList(reply.board_id);
};

onMounted(async () => {
  //로그인 체크
  await userStore.doLoginCheck();

  //댓글 가져오기
  replyStore.getBoardReplyList(idParam.value);
  // await boardStore.updateViewCnt(idParam.value);

  //게시글 정보 가져오기
  await boardStore.getBoard(idParam.value);

  // 로그인시 - 유저 정보 가져오기
  if (isLogin.value) {
    await userStore.getUserByEmail(userStore.loginUser.email);
  }

  // 유저 게시글 찜 정보 가져오기
  if (isLogin.value) {
    await favStore.getFavBoardList(userStore.user.nickname);
  }

  // 해당 글을 이미 찜했는지 안했는지 체크
  await favStore.doFavorCheck(user.value.nickname, idParam.value);

  // 헤더 색 검정색
  commonStore.toggleHeaderFixed(false);
  // 로그인 상태인지 체크
  userStore.doLoginCheck();
  // 작성자인지 아닌지 체크
  isWriter.value = user.value.nickname == boardOne.value.writer;
});

// onUpdated(() => {
//   replyStore.getBoardReplyList(idParam.value);
// });
</script>

<style scoped>
.board-detail {
  min-height: 110vh;
  margin-top: 120px;
  text-align: center;
  height: 1000px;
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

board-detail-location {
  width: 100%;
  border-radius: 30px;
  border: 2px solid rgb(168, 165, 165);
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
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.2);
}

.nav-button {
  background-color: rgba(173, 202, 219, 0.7);
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
  font-size: 0.9em;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.25);
  width: 100%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;
}

th {
  text-align: center;
}

thead {
  font-size: 15px;
  font-weight: bold;
  color: #fff;
  background-color: rgba(173, 202, 219, 0.7);
}

td,
th {
  padding: 1em;
  vertical-align: middle;
}

td {
  text-align: center;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
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
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
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
.reply-button-item {
  margin-top: 10px;
  margin-right: 10px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  text-align: center;
  gap: 8px;
}

.reply-button-item button {
  height: 30px;
  width: 80%;
}

.reply-button-item .button1 {
  color: white;
  background-color: black;
}
.reply-button-item .button1:hover {
  color: black;
  background-color: white;
}
.reply-button-item .button2 {
  color: white;
  background-color: rgb(216, 67, 67);
}
.reply-button-item .button2:hover {
  color: rgb(216, 67, 67);
  background-color: white;
}

.reply-button-item .comment-container {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.comment-container input {
  padding: 8px;
  border-radius: 14px;
  width: 80%;
  height: 35px;
  font-size: 18px;
}

input {
  margin-right: 27px;
}

.comment-input {
  margin: 10px;
}

.comment-input button {
  /* padding: 8px; */
  margin: 8px;
  font-size: 16px;
  font-weight: 600;
  background-color: rgba(173, 202, 219, 0.7);
  width: 12%;
  color: #ffffff;
  height: 35px;
}

.comment-input button:hover {
  background-color: white;
  color: black;
}

.fav-cancel-button:hover,
.fav-button {
  background-color: rgb(255, 255, 255);
  font-weight: bold;
  color: rgb(216, 67, 67);
  padding: 8px 16px;
  margin-right: 5px;
  cursor: pointer;
}

.fav-button:hover,
.fav-cancel-button {
  background-color: rgb(216, 67, 67);
  font-weight: bold;
  color: white;
  padding: 8px 16px;
  margin-right: 5px;
  cursor: pointer;
}
</style>
<!-- .comment-container {
  max-height: 200px; /* 댓글 입력 컨테이너의 최대 높이 설정 */
  overflow-y: auto; /* 세로 스크롤을 추가하여 내용이 넘칠 경우 스크롤이 생성되도록 설정 */
} -->
