import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";
import router from "@/router/index.js"

export const useReplyStore = defineStore("reply", () => {
  const REST_REPLY_API = "http://localhost:1004/reply";
  const REST_BOARD_API = "http://localhost:1004/board";

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
  //작성하고 보드 디테일로 들어가야하니까 보드 번호 받아와야한다
  const registReply = (reply) =>{
    axios.post(`${REST_REPLY_API}/`,reply, {
      headers:{"Content-Type":"application/json"}
    })
    .then(()=>{
      getBoardReplyList(reply.board_id);
    })
    .catch((err)=>{
      console.log(err);
    })
  }

  return { boardReplyList, getBoardReplyList,registReply };
});
