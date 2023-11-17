import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";

export const useReplyStore = defineStore("reply", () => {
  const REST_REPLY_API = "http://localhost:1004/reply";

  // 특정 게시글에 달린 댓글 목록 가져오기
  const boardReplyList = ref([]);
  const getBoardReplyList = (board_id) => {
    axios({
      url: `${REST_REPLY_API}/board/${board_id}`,
      method: "GET",
    })
      .then((response) => (boardReplyList.value = response.data))
      .catch((err) => console.log(err));
  };

  // 댓글 작성하기
  return { boardReplyList, getBoardReplyList };
});
