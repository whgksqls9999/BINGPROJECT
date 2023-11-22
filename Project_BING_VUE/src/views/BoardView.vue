<template>
  <div class="board-view-container">
    <div class="board-view-top">
      <h2>게시글 리스트</h2>
    </div>
    <div class="board-view-middle">
      <div class="board-view-left">
        <button>FREEDIVING</button>
        <button>SKINSCUBA</button>
      </div>
      <div class="board-view-center">
        <BoardList :board-list="commBoardList" />
      </div>
      <div class="board-view-right">
        <RouterLink
          :to="{ name: 'boardWrite', params: { community_id: idParam } }"
          :comm_id="idParam"
        >
          <div><font-awesome-icon :icon="['fas', 'pen-to-square']" /></div
        ></RouterLink>
      </div>
    </div>
  </div>
</template>

<script setup>
import { RouterView, RouterLink, useRoute } from "vue-router";
import { useBoardStore } from "@/stores/boardStore.js";
import { useCommonStore } from "../stores/commonStore";
import { onMounted, computed } from "vue";
import BoardList from "@/components/board/BoardList.vue";

// Store
const commonStore = useCommonStore();
const boardStore = useBoardStore();

// 헤더 fixed toggle
onMounted(() => {
  commonStore.toggleHeaderFixed(false);
});

// community_id 받아오기
const route = useRoute();
const idParam = route.params.community_id;

// // 내 커뮤니티가 아닌 다른 커뮤니티 가져오기
// const communityList = computed(() => boardStore.communityList);
// const elseCommunityList = communityList.value.filter(
//   (element) => element.community_id != idParam
// );

// 게시글 리스트 받아오기
const commBoardList = computed(() => {
  return boardStore.commBoardList;
});

// 커뮤니티 정보 받아오기
const community = computed(() => {
  return boardStore.community;
});

onMounted(() => {
  boardStore.getCommBoardList(idParam);
  boardStore.getCommunity(idParam);
  boardStore.getCommunityList();
});
</script>

<style scoped>
.board-view-container {
  margin-top: 140px;
  margin-left: auto;
  margin-right: auto;
  height: 100vh;
  border: 1px solid black;
  width: 90rem;
}

.board-view-top {
  margin-left: 17rem;
  border-bottom: 0.2rem solid black;
  width: 40rem;
  margin-bottom: 0.5rem;
  padding-bottom: 0.5rem;
}

.board-view-middle {
  border: 1px solid black;
  display: flex;
  justify-content: start;
}

.board-view-left {
  width: 17rem;
  padding-left: 10rem;
  border: 1px solid black;
}

.board-view-center {
  border: 1px solid black;
}
.board-view-right {
  border: 1px solid black;
  display: fixed;
  background-color: black;
  color: white;
  text-decoration: none;
  width: 4rem;
  height: 4rem;
  border-radius: 50%;
}

a {
  text-decoration: none;
  color: white;
  font-size: 30px;
  width: 100%;
  text-align: center;
}
</style>
