<template>
  <div class="board-view-global">
    <div class="board-view-community-title">
      <h1>
        let's MELT
        <span>{{
          idParam == 1 ? "FREEDIVING" : idParam == 2 ? "SKINSCUBA" : ""
        }}</span>
        COMMUNITY
      </h1>
    </div>
    <div class="board-view-container">
      <div class="board-view-top">
        <h2>LIST</h2>
      </div>
      <div class="board-view-middle">
        <div class="board-view-left">
          <RouterLink
            class="category-button free"
            :to="{ name: 'board', params: { community_id: 1 } }"
            >FREEDIVING</RouterLink
          >
          <RouterLink
            class="category-button skin"
            :to="{ name: 'board', params: { community_id: 2 } }"
            >SKINSCUBA</RouterLink
          >
        </div>
        <div class="board-view-center">
          <BoardList :board-list="commBoardList" />
        </div>
      </div>
    </div>
    <br />
    <div class="board-view-right">
      <div class="board-view-search">
        <select v-model="key">
          <option value="title">제목</option>
          <option value="writer">닉네임</option>
        </select>
        <input type="text" v-model="word" @keyup.enter="doSearch" />
        <button @click="doSearch">
          <font-awesome-icon :icon="['fas', 'magnifying-glass']" />
        </button>
        <!-- <button
          @click="
            () => {
              commBoardList.sort((a, b) => b.num - a.num);
            }
          "
        >
          번호 내림차순 정렬
        </button> -->
      </div>
      <RouterLink
        :to="{ name: 'boardWrite', params: { community_id: idParam } }"
        :comm_id="idParam"
      >
        <div class="write-icon">
          <font-awesome-icon :icon="['fas', 'pen-to-square']" />
        </div>
      </RouterLink>
    </div>
  </div>
</template>

<script setup>
import { RouterView, RouterLink, useRoute } from "vue-router";
import { useBoardStore } from "@/stores/boardStore.js";
import { useCommonStore } from "../stores/commonStore";
import { onMounted, computed, ref } from "vue";
import BoardList from "@/components/board/BoardList.vue";
import { watch } from "vue";

// Store
const commonStore = useCommonStore();
const boardStore = useBoardStore();

// 헤더 fixed toggle
onMounted(() => {
  commonStore.toggleHeaderFixed(false);
  commonStore.toggleFooterFixed(true);
});

// community_id 받아오기
const route = useRoute();
const idParam = computed(() => route.params.community_id);

// // 내 커뮤니티가 아닌 다른 커뮤니티 가져오기
// const communityList = computed(() => boardStore.communityList);
// const elseCommunityList = communityList.value.filter(
//   (element) => element.community_id != idParam
// );

// 검색
const key = ref("title");
const word = ref("");
const doSearch = () => {
  let searchCondition = {
    key: key.value,
    word: word.value,
    community_id: idParam.value,
  };

  boardStore.searchBoard(searchCondition);
};

// 게시글 리스트 받아오기
const commBoardList = computed(() => {
  return boardStore.commBoardList;
});

// 커뮤니티 정보 받아오기
const community = computed(() => {
  return boardStore.community;
});

//라우터링크 누를때마다 리스트 갱신
watch(() => {
  boardStore.getCommBoardList(idParam.value);
});
onMounted(() => {
  boardStore.getCommBoardList(idParam.value);
  boardStore.getCommunity(idParam.value);
  boardStore.getCommunityList();
});
</script>

<style scoped>
.board-view-global {
  background-image: url("@/assets/커뮤니티배경.png");
  background-size: cover;
  background-position: center;
  background-color: rgba(0, 0, 0, 0.5);
}
.board-view-search {
  display: flex;
}
.board-view-community-title span {
  background: linear-gradient(
    0deg,
    rgba(173, 202, 219, 1) 0%,
    rgba(255, 255, 255, 0) 41%
  );
}

.board-view-global {
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5);
  padding: 20px;
  overflow-x: hidden;
}

.board-view-community-title {
  color: #f5f5f5;
  text-align: center;
  margin-top: 110px;
}

.board-view-container {
  margin-top: 70px;
  margin-left: auto;
  margin-right: auto;
  border: none;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  width: 70%;
}

.board-view-top {
  background-color: rgba(51, 51, 51, 0.8);
  color: white;
  text-align: center;
  padding: 10px;
  border-bottom: 1px solid #555;
  width: 20%;
  border-top-right-radius: 10px;
}

.board-view-middle {
  display: flex;
}

.board-view-left {
  display: flex;
  flex-direction: column;
  justify-self: center;
  align-items: center;
  width: 20%;
  background-color: rgba(245, 245, 245, 0.8);
  padding: 10px;
}

.category-button {
  font-weight: bold;
  margin-top: 5px;
  display: block;
  width: 70%;
  padding: 12px;
  margin-bottom: 10px;
  background-color: #ddd;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.category-button.free {
  box-shadow: 0px 5px 0px 0px #4c4e4e;
}

.category-button.skin {
  box-shadow: 0px 5px 0px 0px #4c4e4e;
}

.category-button:hover {
  margin-top: 10px;
  margin-bottom: 3px;
}

.category-button.free:hover {
  box-shadow: 0px 0px 0px 0px #4c4e4e;
}

.category-button.skin:hover {
  box-shadow: 0px 0px 0px 0px #4c4e4e;
}

.board-view-center {
  background-color: rgba(255, 255, 255, 0.75);
  flex-grow: 1;
  padding: 10px;
  overflow-y: scroll;
  height: 27rem;
}

.board-view-right {
  width: 100%;
  margin-left: 40%;
  background-color: transparent;
  color: black;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 12rem;
  /* justify-content: ; */
}

.write-icon {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: transparent;
  color: black;
  cursor: pointer;
}

.board-view-right a {
  text-decoration: none;
  color: white;
  font-size: 30px;
  width: 100%;
  text-align: center;
}

a {
  text-decoration: none;
  color: black;
  /* font-size: 30px; */
  width: 100%;
  text-align: center;
}
</style>
