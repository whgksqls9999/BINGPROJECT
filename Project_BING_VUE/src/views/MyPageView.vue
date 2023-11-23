<template>
  <!-- <h2>마이페이지</h2> -->
  <div class="menu-global">
    <div class="menu-hovering">
      <font-awesome-icon :icon="['fas', 'bars']" />
    </div>
    <div class="menu-route">
      <RouterLink id="home" :to="{ name: 'main' }">HOME</RouterLink>
      <RouterLink id="myInfo" :to="{ name: 'myInfo' }">MY INFO</RouterLink>
      <RouterLink id="myBoards" :to="{ name: 'myBoards' }">MY BOARD</RouterLink>
      <RouterLink id="myFavorite" :to="{ name: 'myFavorite' }"
        >MY FAVORITE</RouterLink
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
import { useCommonStore } from "@/stores/commonStore.js";

// store
const commonStore = useCommonStore();

// footer fixed toggle

// 헤더 fixed toggle
onMounted(() => {
  commonStore.toggleHeaderFixed(false);
  commonStore.toggleFooterFixed(true);
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
.menu-global {
  margin-top: 70px;
  background-image: url("@/assets/tutle.png");
  background-position: center;
  background-size: cover;
  height: 100vh;
}
.myrouter-container {
  /* margin-left: 160px; */
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
  z-index: 2; /* Stay on top */
  top: 0; /* Stay at the top */
  left: -10rem;
  background-color: rgba(0, 0, 0, 0.7); /* Black */
  overflow-x: hidden; /* Disable horizontal scroll */
  padding-top: 50px;
  transition: all 0.8s ease;
}

.menu-hovering {
  position: fixed;
  top: 80px;
  left: 10px;
  font-size: 2rem;
  animation: menu-hovering 2s ease infinite;
  transition: all 0.5s ease;
  z-index: 3;
}

.menu-hovering:hover + .menu-route,
.menu-route:hover {
  transform: translateX(10rem);
}

.menu-hovering:hover,
.menu-route:hover ~ .menu-hovering {
  transform: rotate(90deg);
}

@keyframes menu-hovering {
  from,
  to {
    color: black;
  }
  50% {
    color: rgb(80, 80, 80);
  }
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
