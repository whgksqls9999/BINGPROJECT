import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";

export const useBoardStore = defineStore("board", () => {
  const REST_BOARD_API = "http://localhost:1004/board";

  // 커뮤니티 게시글 가져오기
  const commBoardList = ref("");
  const getCommBoardList = (community_id) => {
    axios
      .get(`${REST_BOARD_API}/comm/${community_id}`)
      .then((response) => (commBoardList.value = response.data));
  };

  return { commBoardList, getCommBoardList };
});
