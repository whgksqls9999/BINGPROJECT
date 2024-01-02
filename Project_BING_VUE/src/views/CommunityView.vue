<template>
  <div>
    <div class="communitymain-title">
      <h2>당신의 커뮤니티를 녹여주세요</h2>
    </div>
    <div class="container">
      <RouterLink
        data-text="freediving"
        :to="{ name: 'board', params: { community_id: '1' } }"
        class="freediving link"
        >FREEDIVING</RouterLink
      >
      <br />
      <RouterLink
        data-text="skinscuba"
        :to="{ name: 'board', params: { community_id: '2' } }"
        class="skinscuba link"
        >SKINSCUBA</RouterLink
      >
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useCommonStore } from "@/stores/commonStore.js";

// 헤더 fixed toggle
const commonStore = useCommonStore();
onMounted(() => {
  commonStore.toggleHeaderFixed(false);
  commonStore.toggleFooterFixed(false);
});
</script>

<style scoped>
a {
  text-decoration: none;
  color: transparent;
}
.communitymain-title {
  position: absolute;
  text-align: center;
  top: 30vh;
  left: 50%;
  transform: translateX(-50%);
}

h2 {
  z-index: 100;
  font-size: 2.3rem;
  opacity: 0;
  animation: fadeIn 1s forwards, blink 2s infinite; /* fadeIn 애니메이션 + 반짝이는 애니메이션 추가 */
  text-shadow: 0 0 20px rgba(255, 255, 255, 0.8); /* glow 효과 설정 */
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  background-image: url("@/assets/커뮤니티배경.jpg");
  background-size: cover;
  background-position: center;
  display: flex;
  padding-top: 20px;
  height: 120vh;
}

.link {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 160px;
  height: 50px;
  line-height: 50px;
  font-size: 20px;
  text-align: center;
  font-family: sans-serif;
  text-decoration: none;
  text-transform: uppercase;
  letter-spacing: 2px;
  background: #383838;
  z-index: 0;
  margin: 0 10px;
}

.freediving:before,
.skinscuba:before {
  content: attr(data-text);
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  text-align: center;
  transition: 0.5s;
  transform-origin: bottom;
}

.freediving {
  margin-left: -230px;
}

.skinscuba {
  margin-left: 230px;
}
.freediving:before {
  background: #383838;
  color: #fff;
  font-weight: 600;
  transform: translatey(-100%) rotatex(90deg);
}

.freediving:hover:before {
  transform: translatey(0) rotatex(0deg);
}

.skinscuba:before {
  background: #383838;
  font-weight: 600;
  color: #fff;
  transform: translatey(-100%) rotatex(90deg);
}

.skinscuba:hover:before {
  transform: translatey(0) rotatex(0deg);
}

.link:after {
  content: attr(data-text);
  font-weight: 600;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  text-align: center;
  color: #fff;
  transition: 0.5s;
  transform-origin: top;
}

.link:hover:after {
  transform: translatey(100%) rotatex(90deg);
}

/* 추가된 애니메이션 스타일 */
@keyframes fadeIn {
  to {
    opacity: 1;
  }
}

@keyframes blink {
  0%,
  100% {
    opacity: 1;
  }
  50% {
    opacity: 0;
  }
}
</style>

<!-- 
/* a {
  text-decoration: none;
  color: white;
  background-color: rgba(0, 0, 0, 0.6);
  border-radius: 20px;
  width: 150px;
  height: 30px;
  text-align: center;
  padding: 20px;
  margin: 40px;
} */ -->
