import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";
import router from "@/router/index.js";
import { useBoardStore } from "@/stores/boardStore.js";

export const useFavStore = defineStore("fav", () => {
  const REST_FAVBOARD_API = "http://localhost:1004/favboard";
  const REST_FAVLOCATION_API = "http://localhost:1004/favlocation";

  const boardStore = useBoardStore();

  // 게시글 찜하기
  const doFavBoard = (favBoard, nickname) => {
    axios
      .post(`${REST_FAVBOARD_API}/addfavboard`, null, {
        headers: { "Content-Type": "application/json" },
        params: favBoard,
      })
      .then(() => {
        doFavorCheck(nickname, favBoard.board_id);
        alert("게시글을 찜했습니다.");
      })
      .catch((err) => console.log(err));
  };

  // 해당 유저 게시글 찜 리스트 가져오기
  const favBoardList = ref([]);
  const getFavBoardList = async (nickname) => {
    await axios
      .get(`${REST_FAVBOARD_API}/${nickname}`)
      .then((response) => {
        favBoardList.value = response.data;
      })
      .catch((err) => console.log(err));
  };

  //이미 찜한 게시물인지 체크
  const isFavored = ref("");
  const doFavorCheck = async (nickname, board_id) => {
    const check = ref(false);
    await getFavBoardList(nickname);
    console.log(nickname, board_id, favBoardList.value);
    favBoardList.value.forEach((element) => {
      if (element.board_id == board_id) {
        isFavored.value = element.favorite_boardId;
        check.value = true;
        return;
      }
    });
    if (!check.value) {
      isFavored.value = "";
    }
  };

  // 찜 취소
  const doFavBoardCancel = (favorite_boardId, nickname, board_id) => {
    axios
      .delete(`${REST_FAVBOARD_API}/deletefavboard/${favorite_boardId}`)
      .then((response) => {
        doFavorCheck(nickname, board_id);
        alert("게시글 찜을 취소했습니다.");
      })
      .catch((err) => console.log(err));
  };

  return {
    doFavBoard,
    favBoardList,
    getFavBoardList,
    isFavored,
    doFavorCheck,
    doFavBoardCancel,
  };
});
