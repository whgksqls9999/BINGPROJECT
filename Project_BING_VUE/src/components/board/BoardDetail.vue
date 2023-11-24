<template>
  <div class="board-detail" :class="{ extend: boardOne.header == '장소추천' }">
    <div
      class="board-detail-global"
      :class="{ extends: boardOne.header == '장소추천' }"
    >
      <div class="board-padding-remove">
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
      </div>
      <div class="board-detail-container">
        <div class="board-detail-inform">
          <!-- <h4>: {{ boardOne.writer }}</h4> -->

          <h4 @click="doInfoPopup(boardOne.writer)" class="board-writer">
            작성자 : {{ boardOne.writer }}
          </h4>
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
          <button @click="prePage" class="nav-button">&lt;</button>
          <button
            class="action-button"
            @click="boardModifyPush"
            v-if="isWriter"
          >
            EDIT
          </button>
          <span v-if="isLogin != null">
            <button
              v-if="isFavored == ''"
              @click="doFavBoard"
              class="fav-button"
            >
              <strong>LIKE</strong>
            </button>
            <button v-else @click="doFavCancel" class="fav-cancel-button">
              🧡
            </button>
          </span>

          <button
            class="action-button"
            id="delete"
            @click="boardDelete"
            v-if="isWriter"
          >
            DELETE
          </button>
          <button @click="nextPage" class="nav-button">&gt;</button>
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
            <BoardDetailReply
              v-for="reply in boardReplyList"
              :key="reply.id"
              class="reply-item"
              :reply="reply"
              :user="user"
            />
          </table>
        </div>
        <div v-if="isLogin" class="comment-container">
          <h3>REPLY</h3>
          <div class="comment-input">
            <input type="text" v-model="replyContent" />
            <button @click="registReply">SUBMIT</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <UserInfo
    :selected="isPopup"
    :class="{ show: isPopup == '' }"
    @close-window="doClose"
  />
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { computed, onMounted, watch, ref } from "vue";
import { useReplyStore } from "@/stores/replyStore.js";
import { useUserStore } from "@/stores/userStore.js";
import { useBoardStore } from "@/stores/boardStore";
import { useFavStore } from "@/stores/favStore.js";
import { useCommonStore } from "@/stores/commonStore";
import BoardDetailMap from "@/components/board/include/BoardDetailMap.vue";
import BoardDetailReply from "@/components/board/include/BoardDetailReply.vue";
import UserInfo from "../account/UserInfo.vue";

watch(async () => {
  await boardStore.getBoard(boardOne.value.board_id);
});

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

//board 다 불러오기
const commBoardList = computed(() => boardStore.commBoardList);

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

  if (replyContent.value === "") {
    alert("내용을 입력해주세요!");
    return;
  }

  replyStore.registReply(reply);
  replyContent.value = "";
};

const prePage = async () => {
  if (boardOne.value.num === 1) {
    alert("이전 게시글이 없습니다.");
    return;
  }

  const preBoardIndex = boardOne.value.num - 2;
  const preBoard = commBoardList.value[preBoardIndex];

  await router.push({
    name: "boardDetail",
    params: {
      community_id: preBoard.community_id,
      board_id: preBoard.board_id,
    },
  });

  await boardStore.updateViewCnt(preBoard.board_id);

  await boardStore.getBoard(preBoard.board_id);
  await replyStore.getBoardReplyList(preBoard.board_id);
  await onMountCheckBoard();
};

const nextPage = async () => {
  console.log(boardOne.value.num);
  console.log("ddd", commBoardList.value);
  if (boardOne.value.num === commBoardList.value.length) {
    alert("다음 게시글이 없습니다.");
    return;
  }

  const nextBoardIndex = boardOne.value.num;
  const nextBoard = commBoardList.value[nextBoardIndex];
  await boardStore.updateViewCnt(nextBoard.board_id);

  if (!nextBoard) {
    alert("다음 게시글이 없습니다.");
    return;
  }

  await router.push({
    name: "boardDetail",
    params: {
      community_id: nextBoard.community_id,
      board_id: nextBoard.board_id,
    },
  });

  await boardStore.getBoard(nextBoard.board_id);
  await replyStore.getBoardReplyList(nextBoard.board_id);
  await onMountCheckBoard();
};

const onMountCheckBoard = async () => {
  // 로그인시 - 유저 정보 가져오기
  if (isLogin.value) {
    await userStore.getUserByEmail(userStore.loginUser.email);
  }

  // 유저 게시글 찜 정보 가져오기
  if (isLogin.value) {
    await favStore.getFavBoardList(userStore.user.nickname);
    // 해당 글을 이미 찜했는지 안했는지 체크
    await favStore.doFavorCheck(user.value.nickname, idParam.value);
  }

  await boardStore.getCommBoardList(boardOne.value.community_id);

  // 작성자인지 아닌지 체크
  isWriter.value = user.value.nickname == boardOne.value.writer;
};

onMounted(async () => {
  //로그인 체크
  await userStore.doLoginCheck();

  //댓글 가져오기
  await replyStore.getBoardReplyList(idParam.value);
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
  await boardStore.getBoardAll();

  // 해당 글을 이미 찜했는지 안했는지 체크
  await favStore.doFavorCheck(user.value.nickname, idParam.value);

  await boardStore.getCommBoardList(boardOne.value.community_id);
  // 헤더 색 검정색
  commonStore.toggleHeaderFixed(false);

  // 작성자인지 아닌지 체크
  isWriter.value = user.value.nickname == boardOne.value.writer;

  commonStore.toggleFooterFixed(true);
});

// 작성자 정보 확인하기
const isPopup = ref("");
const doInfoPopup = (writername) => {
  isPopup.value = writername;
};
const doClose = () => {
  isPopup.value = "";
};
// onUpdated(() => {
//   replyStore.getBoardReplyList(idParam.value);
// });
</script>

<style scoped>
.board-detail {
  margin-top: 70px;
  display: flex;
  justify-content: center;
  text-align: center;
  height: 130vh;
  background-image: url("@/assets/프리다이빙.png");
  background-position: center;
  background-color: rgba(0, 0, 0, 0.5);
  padding: 20px;
}

.board-detail-global {
  position: relative;
  /* background: linear-gradient(-7deg, rgba(180, 202, 221, .7), rgba(0, 0, 0, .7)); */
  background-color: rgba(245, 245, 245, 0.75);
  /* border: 3px solid black; */
  border-radius: 20px;
  padding: 10px 20px;
  width: 60%;
  height: 100vh;
  box-shadow: 0 0 10px rgba(0, 0, 0, 1);
}
.board-padding-remove {
  margin: auto;
  position: absolute;
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-color: rgba(51, 51, 51);
  border-top-right-radius: 10px;
  border-top-left-radius: 10px;
  width: 100%;
  /* height: 13%; */
  height: 135px;
  top: 0;
  left: 0;
  overflow: hidden;
  color: #e5e5e5;
  /* z-index: -1; */
}

.board-detail-container {
  /* background-color: white; */
  /* border: 1px solid #ccc; */
  border-radius: 20px;
  /* border-style: none; */
  padding: 20px;
  margin-top: 110px;
}

.board-detail-location {
  width: 100%;
  border-radius: 30px;
  border: 2px solid rgb(168, 165, 165);
}

.board-detail-content {
  /* background-color: white; */
  font-size: 1.2rem;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 2px solid rgb(168, 165, 165);
  /* border-style: none; */
  border-radius: 30px;
  width: 100%;
  height: 30vh;
  margin-bottom: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.board-detail-inform {
  border: 2px solid rgb(146, 142, 142);
  border-radius: 10px;
  padding: 7px;
  margin-bottom: 10px;
  display: flex;
  justify-content: space-around;
}

.board-detail-buttons {
  margin-left: 5%;
  margin-top: 10px;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}

button {
  border-style: none;
  border-radius: 20px;
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.2);
  transition: 0.3s ease, color 0.3s ease;
}

.nav-button {
  background-color: transparent;
  border-style: none;
  box-shadow: none;
  font-weight: bold;
  color: black;
  padding: 8px 16px;
  margin-right: 5px;
  cursor: pointer;
  font-size: 1.3rem;
}

.nav-button:hover {
  background-color: rgba(255, 255, 255, 0.7);
  color: black;
}

.action-button {
  background-color: transparent;
  font-weight: bold;
  color: rgb(83, 83, 83);
  padding: 8px 16px;
  margin-right: 5px;
  cursor: pointer;
  font-size: 1.3rem;
  box-shadow: none;
}

.action-button:hover {
  color: rgba(61, 184, 61, 0.577);
}

#delete:hover {
  color: rgb(216, 67, 67);
}
.reply-item {
  margin-top: 10px;
}

.board-writer {
  /* margin-top: 20px;
  font-style: italic;
  color: #555; */
  cursor: pointer;
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
  background-color: rgba(0, 0, 0, 0.7);
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
  justify-content: center;
  align-items: center;
  text-align: center;
  gap: 8px;
}

.reply-button-item button {
  height: 30px;
  width: 50px;
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
  height: 33px;
  margin-right: 27px;
  padding: 0 10px;
}

.comment-input {
  margin: 10px;
}

.comment-input button {
  /* padding: 8px; */
  margin: 8px;
  font-size: 16px;
  font-weight: 600;
  background-color: rgba(0, 0, 0, 0.7);
  width: 5rem;
  color: #ffffff;
  height: 35px;
}

.comment-input button:hover {
  background-color: black;
}

.fav-cancel-button:hover,
.fav-button {
  border-style: none;
  box-shadow: none;
  background-color: transparent;
  padding: 8px 16px;
  margin-right: 5px;
  cursor: pointer;
  font-size: 1.3rem;
}
.fav-button:hover,
.fav-cancel-button {
  border-style: none;
  box-shadow: none;
  /* background-color: white; */
  font-size: 1.3rem;
  padding: 8px 16px;
  margin-right: 5px;
  cursor: pointer;
  /* background-color: rgb(216, 67, 67); */
}

.show {
  display: none;
}

.extend {
  height: 150vh;
}
.extends {
  height: 130vh;
}
.board-detail-reply-container {
  height: 23rem;
  overflow-y: scroll;
}
</style>
<!-- .comment-container {
  max-height: 200px; /* 댓글 입력 컨테이너의 최대 높이 설정 */
  overflow-y: auto; /* 세로 스크롤을 추가하여 내용이 넘칠 경우 스크롤이 생성되도록 설정 */
} -->
