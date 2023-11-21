import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";
import router from "@/router/index.js";

export const useBoardStore = defineStore("board", () => {
  const REST_BOARD_API = "http://localhost:1004/board";
  const REST_COMM_API = "http://localhost:1004/community";
  const REST_FAV_API = "http://localhost:1004/favboard";

  // 전체 커뮤니티 목록 가져오기
  const communityList = ref([]);
  const getCommunityList = () => {
    axios({
      url: `${REST_COMM_API}/list`,
      method: "GET",
    }).then((response) => {
      communityList.value = response.data;
    });
  };

  // 커뮤니티 정보 가져오기
  const community = ref("");
  const getCommunity = (community_id) => {
    axios({
      url: `${REST_COMM_API}/${community_id}`,
      method: "GET",
    }).then((response) => {
      community.value = response.data;
    });
  };

  // 커뮤니티 게시글 가져오기
  const commBoardList = ref([]);
  const getCommBoardList = (community_id) => {
    axios({
      url: `${REST_BOARD_API}/comm/${community_id}`,
      mathod: "GET",
    }).then((response) => {
      commBoardList.value = response.data;
    });
  };

  // 게시글 등록하기
  const registBoard = (board, comm_id) => {
    axios
      .post(`${REST_BOARD_API}/insert`, board, {
        headers: {
          "Content-Type": `application/json`,
        },
      })
      .then(() => {
        alert("게시글이 등록되었습니다.");
        router.push({ path: `/board/${comm_id}` });
      })
      .catch((err) => console.log(err));
  };

  return {
    commBoardList,
    getCommBoardList,
    community,
    getCommunity,
    communityList,
    getCommunityList,
    registBoard,
  };
});
