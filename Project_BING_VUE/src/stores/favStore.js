import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";
import router from "@/router/index.js";

export const useFavStore = defineStore("fav", () => {
  const REST_FAVBOARD_API = "http://localhost:1004/favboard";
  const REST_FAVLOCATION_API = "http://localhost:1004/favlocation";

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
    const check = false;
    await getFavBoardList(nickname);
    console.log(favBoardList.value);
    favBoardList.value.forEach((element) => {
      console.log(element.board_id, board_id);
      if (element.board_id == board_id) {
        isFavored.value = board_id;
        check = true;
        return;
      }
    });
    if (!check) {
      isFavored.value = null;
    }
    // isFavored.value = null;
  };

  const doFavBoardCancel = () => {};

  return {
    doFavBoard,
    favBoardList,
    getFavBoardList,
    isFavored,
    doFavorCheck,
    doFavBoardCancel,
  };
});
