<template>
  <div class="container">
    <h1>{{ community.community_name }} 게시판입니다.</h1>
    <div>
      <RouterLink :to="{ name: 'board', params: { community_id: community.community_id } }"
        v-for="community in elseCommunityList">{{ community.community_name }}</RouterLink>
    </div>

    <RouterLink :to="{ name: 'boardDetail', params: { board_id: board.board_id } }" v-for="board in commBoardList">{{
      board.board_id }}번 게시글</RouterLink>
    <RouterView />
    <div>
      <RouterLink :to="{ name: 'boardWrite', params: { 'community_id': idParam } }" :comm_id="idParam">글 작성</RouterLink>
    </div>
  </div>
</template>

<script setup>
import { RouterView, RouterLink, useRoute } from "vue-router";
import { useBoardStore } from "@/stores/boardStore.js";
import { onMounted, computed } from "vue";

const boardStore = useBoardStore();

// community_id 받아오기
const route = useRoute();
const idParam = route.params.community_id;

// 내 커뮤니티가 아닌 다른 커뮤니티 가져오기
const communityList = computed(() => boardStore.communityList);
const elseCommunityList = communityList.value.filter(
  (element) => element.community_id != idParam
);

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
.container{
  margin-top: 70px;
}
</style>
