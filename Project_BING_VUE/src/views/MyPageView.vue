<template>
  <!-- <h2>마이페이지</h2> -->
  <div class="sidebar">
    <RouterLink :to="{ name: 'main' }">홈</RouterLink>
    <RouterLink :to="{ name: 'myInfo' }">나의 정보</RouterLink>
    <RouterLink :to="{ name: 'myBoards' }">나의 글 목록</RouterLink>
    <RouterLink :to="{ name: 'myFavorite' }">나의 찜 목록</RouterLink>
  </div>
  <div>
    <RouterView />
  </div>
</template>

<script setup>

import MyBoards from '@/components/myPage/MyBoards.vue';
import { useMyPageStore } from '@/stores/myPageStore.js';
import { useRoute } from "vue-router";

const route = useRoute();
const nicknameParam = route.params.nickname;
</script>

<style scoped>
.sidebar {
  margin: 0;
  padding: 0;
  width: 200px;
  background-color: rgba(0, 0, 0, 0.5);
  position: fixed;
  height: 100%;
  overflow: auto;
}

/* Sidebar links */
.sidebar a {
  display: block;
  color: black;
  padding: 16px;
  text-decoration: none;
}

/* Active/current link */
.sidebar a.active {
  background-color: #04AA6D;
  color: white;
}

/* Links on mouse-over */
.sidebar a:hover:not(.active) {
  background-color: #555;
  color: white;
}

/* Page content. The value of the margin-left property should match the value of the sidebar's width property */
div.content {
  margin-left: 200px;
  padding: 1px 16px;
  height: 1000px;
}

/* On screens that are less than 700px wide, make the sidebar into a topbar */
@media screen and (max-width: 700px) {
  .sidebar {
    width: 100%;
    height: auto;
    position: relative;
  }

  .sidebar a {
    float: left;
  }

  div.content {
    margin-left: 0;
  }
}

/* On screens that are less than 400px, display the bar vertically, instead of horizontally */
@media screen and (max-width: 400px) {
  .sidebar a {
    text-align: center;
    float: none;
  }
}</style>
