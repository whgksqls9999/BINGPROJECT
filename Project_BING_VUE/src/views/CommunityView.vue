<template>
  <div class="container">
    <div class="comm-form">
      <!-- <div class="comm-title"></div> -->
      <div class="comm-menu">
        <RouterLink :to="{ name: 'board', params: { community_id: '1' } }" class="freediving">FREEDIVING</RouterLink>
        <div class="freediving-desc desc">
          WELCOME TO FREEDIVING WORLD!
        </div>
        <RouterLink :to="{ name: 'board', params: { community_id: '2' } }" class="skinscuba">SKINSCUBA</RouterLink>
        <div class="skinscuba-desc desc">
          WELCOME TO SKINSCUBA WORLD!
        </div>
        <div class="comm-title"> SELECT COMMUNITY </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
body {
  height: 100vh;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 2%;
  text-align: center;
}
*{
  box-sizing: border-box;
}
.desc {
  position: absolute;
  top: 100%;
  margin-top: 1rem;
  transition: all 0.7s ease;
  opacity: 0;
  font-size: 2rem;
  width: 40rem;
  background-color: white;
  height: 20rem;
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
  width: 40rem;
  height: 20rem;
  opacity: 1;
  transition: all 0.7s ease;
}

.comm-menu {
  display: flex;
  justify-content: center;
  gap: 1rem;
  height: 100%;
  position: relative;
}

a {
  text-decoration: none;
  color: white;
  background-color: black;
  border-radius: 2rem;
  padding: 1rem;
  transition: all 0.8s ease;
  position: relative;
  opacity: 0.85;
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
