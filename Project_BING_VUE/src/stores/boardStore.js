import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";

export const useBoardStore = defineStore("board", () => {
  const REST_BOARD_API = "http://localhost:1004/board";
  const REST_COMM_API = "http://localhost:1004/community";

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

  return {
    commBoardList,
    getCommBoardList,
    community,
    getCommunity,
    communityList,
    getCommunityList,
  };
});
