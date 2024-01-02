import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";
import router from "@/router/index.js";

export const useReplyStore = defineStore("reply", () => {
  const REST_REPLY_API = "http://localhost:1004/reply";
  const REST_BOARD_API = "http://localhost:1004/board";

  // 특정 게시글에 달린 댓글 목록 가져오기
  const boardReplyList = ref([]);
  const getBoardReplyList = async (board_id) => {
    await axios({
      url: `${REST_REPLY_API}/board/${board_id}`,
      method: "GET",
    })
      .then((response) => (boardReplyList.value = response.data))
      .catch((err) => console.log(err));
  };

  const reply = ref({});
  const getReply = (reply_id) => {
    axios({
      url: `${REST_REPLY_API}/${reply_id}`,
      method: "GET",
    }).then((response) => {
      reply.value = response.data;
    });
  };

  // 댓글 작성하기
  //작성하고 보드 디테일로 들어가야하니까 보드 번호 받아와야한다
  const registReply = (reply) => {
    axios
      .post(`${REST_REPLY_API}/`, reply, {
        headers: { "Content-Type": "application/json" },
      })
      .then(() => {
        //작성 다 끝나고 나서 불러오기
        getBoardReplyList(reply.board_id);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const removeReply = async (replyId) => {
    try {
      if (replyId) {
        await axios.delete(`${REST_REPLY_API}/${replyId}`);
        console.log("댓글 삭제 성공"); // 서버 응답 확인
        alert("댓글이 삭제되었습니다.");
      } else {
        console.error("reply.reply_id is not defined");
      }
    } catch (error) {
      console.log(error);
    }
  };

  //댓글 수정하기
  const updateReply = async (reply) => {
    await axios
      .put(`${REST_REPLY_API}/${reply.reply_id}`, reply, {
        headers: { "Content-Type": "application/json" },
      })
      .then((response) => {
        console.log(response);
        getBoardReplyList(reply.board_id);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return {
    boardReplyList,
    getBoardReplyList,
    registReply,
    removeReply,
    reply,
    getReply,
    updateReply,
  };
});
