<template>
  <div class="container">
    <div class="comm-form">
      <!-- <div class="comm-title"></div> -->
      <div class="comm-menu">
        <RouterLink :to="{ name: 'board', params: { community_id: '1' } }" class="freediving">FREEDIVING</RouterLink>
        <div class="freediving-desc desc">
          WELCOME TO <br><strong style="font-size: 50px;">FREEDIVING</strong> WORLD!
        </div>
        <RouterLink :to="{ name: 'board', params: { community_id: '2' } }" class="skinscuba">SKINSCUBA</RouterLink>
        <div class="skinscuba-desc desc">
          WELCOME TO <br><strong style="font-size: 50px;">SKINSCUBA</strong> WORLD!
        </div>
        <div class="comm-title"> SELECT COMMUNITY </div>
        <!-- <div class="comm-desc"> SHARE YOUR WORLD </div> -->
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const fadeout = ref('false');
</script>

<style scoped>
body {
  height: 100vh;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  /* justify-content: center; */
  margin-top: 70px;
  padding-top: 20px;
  text-align: center;
  height: 90vh;
}
@keyframes fadein{
  from{
    opacity: 0;
  }
  to{
    opacity: 1;
  }
}

@keyframes delayed-fadein{
  0%{
    opacity: 0;
  }
  33%{
    opacity: 0;
  }
  100%{
    opacity: 1;
  }
}


*{
  box-sizing: border-box;
}
.desc {
  position: absolute;
  top: 100%;
  margin-top: 1rem;
  transition: all 0.5s;
  opacity: 0;
  font-size: 2rem;
  width: 50rem;
  background-color: white;
  height: 30rem;
  padding: 1rem;
  text-align: left;
}

.freediving-desc{
  border-top: 1rem solid black;
  border-left: 1rem solid black;
}
.skinscuba-desc{
  border-top: 1rem solid black;
  border-left: 1rem solid black;
}

.freediving:hover+.freediving-desc,
.skinscuba:hover+.skinscuba-desc {
  opacity: 1;
  transform: translateY(1rem);
}

.freediving:hover ~ .comm-title,
.skinscuba:hover ~ .comm-title{
  opacity: 0;
  transform: translateY(-1rem);
}

.comm-form {
  width: 24rem;
}

.comm-title {
  padding: 1rem;
  text-align: left;
  margin-top: 2rem;
  border-top: 1rem solid black;
  border-left: 1rem solid black;
  top: 100%;
  position: absolute;
  margin-bottom: 0.2rem;
  font-size: 50px;
  font-weight: bold;
  width: 50rem;
  height: 30rem;
  opacity: 1;
  transition: all 0.7s ease;
  animation: fadein 1s;
}

.comm-menu {
  display: flex;
  justify-content: center;
  gap: 1rem;
  height: 100%;
  position: relative;
}

/* .comm-desc{
  position: absolute;
  top: 980%;
  font-size: 40px;
  width: 30rem;
  animation: delayed-fadein 2s;
} */
a {
  text-decoration: none;
  color: white;
  background-color: black;
  border-radius: 2rem;
  padding: 1rem;
  transition: all 0.8s ease;
  position: relative;
  opacity: 0.85;
  animation: delayed-fadein 1.5s;
}

a:hover {
  background-color: rgb(255, 255, 255);
  color: black;
  opacity: 1;
  box-shadow: 0 0 0.6rem black;
}

a:active {
  background-color: black;
  /* font-size: 20px; */
  opacity: 0.85;
  color: white;
}

* {
  box-sizing: border-box;
}
</style>
