import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

const REST_MYPAGE_API = "http://localhost:1004/mypage";

export const useMyPageStore = defineStore("mypage", () => {
  //내가 쓴 글 목록
  //담아 올 배열이 필요
  const myBoards = ref([]);
  const getMyBoards = (email) => {
    axios({
      url: `${REST_MYPAGE_API}/pickboard/${email}`,
      method: "GET",
    }).then((response) => {
      const tmp = response.data;
      myBoards.value = tmp;
    });
  };

  //내가 쓴 댓글 목록
  const myReplys = ref([]);
  const getMyReplys = (writer) => {
    axios({
      url: `${REST_MYPAGE_API}/pickreply/${writer}`,
      method: "GET",
    }).then((response) => {
      const tmp = response.data;
      myReplys.value = tmp;
    });
  };
  //내가 찜한 장소
  const myFavLocations = ref([]);
  const getMyFavLocations = (user_email) => {
    axios({
      url: `${REST_MYPAGE_API}/pickfavlocation/${user_email}`,
      method: "GET",
    }).then((response) => {
      const tmp = response.data;
      myFavLocations.value = tmp;
    });
  };

  //내가 찜한 게시글
  const myFavBoards = ref([]);
  const getMyFavBoards = async (user_email) => {
    await axios({
      url: `${REST_MYPAGE_API}/pickfavboard/${user_email}`,
      method: "GET",
    }).then((response) => {
      const tmp = response.data;
      myFavBoards.value = tmp;
    });
  };
  //내가 팔로우한 사람은 다른데서 구현

  //개인정보 변경 -> userStore 완료
  return {
    getMyBoards,
    myBoards,
    getMyReplys,
    myReplys,
    getMyFavLocations,
    myFavLocations,
    getMyFavBoards,
    myFavBoards,
  };
});
