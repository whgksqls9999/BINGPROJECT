<template>
  <!-- <h2>마이페이지</h2> -->
  <div class="menu-global">
    <div class="menu-route">
      <RouterLink id="home" :to="{ name: 'main' }">홈</RouterLink>
      <RouterLink id="myInfo" :to="{ name: 'myInfo' }">나의 정보</RouterLink>
      <RouterLink id="myBoards" :to="{ name: 'myBoards' }"
        >나의 글 목록</RouterLink
      >
      <RouterLink id="myFavorite" :to="{ name: 'myFavorite' }"
        >나의 찜 목록</RouterLink
      >
    </div>
    <transition name="fade" mode="out-in">
      <div class="myrouter-container" :key="route.name">
        <RouterView />
      </div>
    </transition>
  </div>
</template>

<script setup>
import MyBoards from "@/components/myPage/MyBoards.vue";
import { useMyPageStore } from "@/stores/myPageStore.js";
import { useRoute } from "vue-router";
import { ref, onMounted } from "vue";
import { useCommonStore } from "../stores/commonStore";

// 헤더 fixed toggle
const commonStore = useCommonStore();
onMounted(() => {
  commonStore.toggleHeaderFixed(false);
});

const route = useRoute();
const isNavOpen = ref(false);
const nicknameParam = route.params.nickname;
</script>

<style scoped>
* {
  box-sizing: border-box;
  font-weight: lighter;
}
.myrouter-container {
  margin-left: 160px;
  display: flex;
  flex-direction: column;
  /* justify-content: center; */
  align-items: center;
}
.menu-route {
  margin-top: 70px;
  height: 100%; /* Full-height: remove this if you want "auto" height */
  width: 160px; /* Set the width of the sidebar */
  position: fixed; /* Fixed Sidebar (stay in place on scroll) */
  z-index: 1; /* Stay on top */
  top: 0; /* Stay at the top */
  left: 0;
  background-color: rgba(0, 0, 0, 0.7); /* Black */
  overflow-x: hidden; /* Disable horizontal scroll */
  padding-top: 20px;
}

.menu-route a {
  padding: 6px 8px 6px 16px;
  text-decoration: none;
  font-size: 1.35em;
  color: #818181;
  display: block;
}

.menu-route a:hover {
  color: #f1f1f1;
}

@media screen and (max-width: 450px) {
  .menu-route {
    background-color: rgba(0, 0, 0, 0.4);
    width: 50%;
    padding-top: 15px;
  }
  .menu-route a {
    font-size: 15px;
    color: black;
  }
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
