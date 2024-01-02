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

  // 게시글 찜 취소
  const doFavBoardCancel = async (favorite_boardId, nickname, board_id) => {
    await axios
      .delete(`${REST_FAVBOARD_API}/deletefavboard/${favorite_boardId}`)
      .then((response) => {
        doFavorCheck(nickname, board_id);
      })
      .catch((err) => console.log(err));
  };

  // 장소 찜 정보 가져오기
  const favLocationList = ref([]);
  const getFavLocationList = async (nickname) => {
    await axios
      .get(`${REST_FAVLOCATION_API}/${nickname}`)
      .then((response) => (favLocationList.value = response.data))
      .catch((err) => console.log(err));
  };

  // 이미 찜한 장소인지 체크
  const isFavoredLocation = ref("");
  const doFavLocationCheck = async (nickname, location_id) => {
    const check = ref(false);
    await getFavLocationList(nickname);
    favLocationList.value.forEach((element) => {
      if (element.location_id == location_id) {
        isFavoredLocation.value = element.favorite_locationId;
        check.value = true;
      }
    });
    if (!check.value) {
      isFavoredLocation.value = "";
    }
  };

  // 장소 찜 등록
  const doFavLocation = async (favLocation, nickname) => {
    await axios
      .post(`${REST_FAVLOCATION_API}/addfavlocation`, null, {
        headers: { "Content-Type": "application/json" },
        params: favLocation,
      })
      .then(() => {
        doFavLocationCheck(nickname, favLocation.location_id);
      })
      .catch((err) => console.log(err));
  };

  // 장소 찜 취소
  const doFavLocationCancel = async (
    favorite_locationId,
    nickname,
    location_id
  ) => {
    await axios
      .delete(
        `${REST_FAVLOCATION_API}/deletefavlocation/${favorite_locationId}`
      )
      .then((response) => {
        doFavLocationCheck(nickname, location_id);
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
    favLocationList,
    getFavLocationList,
    isFavoredLocation,
    doFavLocationCheck,
    doFavLocationCancel,
    doFavLocation,
  };
});
