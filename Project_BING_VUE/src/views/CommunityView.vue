<template>
  <div class="container">
    <div class="comm-form">
      <div class="comm-title">SELECT COMMUNITY</div>
      <div class="comm-menu">
        <RouterLink :to="{ name: 'board', params: { community_id: '1' } }" class="freediving">FREEDIVING</RouterLink>
        <div class="freediving-desc desc">
          WELCOME TO FREEDIVING WORLD!
        </div>
        <RouterLink :to="{ name: 'board', params: { community_id: '2' } }" class="skinscuba">SKINSCUBA</RouterLink>
        <div class="skinscuba-desc desc">
          WELCOME TO SKINSCUBA WORLD!
        </div>
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
  margin-top: 5%;
  text-align: center;
}

.desc{
  position: absolute;
  top: 200%;
  margin-top: 1rem;
  transition: all 1s ease;
  opacity: 0;
  font-size: 2rem;
  width: 40rem;
}

.freediving:hover +
.freediving-desc,
.skinscuba:hover +
.skinscuba-desc{
  opacity: 1;
}

.comm-form{
  box-shadow: 0 1px 0.3rem grey;
  width: 24rem;
  padding: 1rem 0;
  border-radius: 0.3rem;
}

.comm-title{
  margin-bottom: 1rem;
  font-size: 24px;
  font-weight: bold;
}

.comm-menu{
  display: flex;
  justify-content: center;
  gap: 4rem;
  height: 100%;
  position: relative;
}

a{
  text-decoration: none;
  color: white;
  background-color: black;
  border-radius: 2rem;
  padding: 1rem;
  transition: all 1s ease;
  position: relative;
  opacity: 0.85;
}

a:hover{
  background-color: black;
  padding: 0.8rem;
  opacity: 1;
  font-size: 18px;
  box-shadow: 0 0 1rem black;
}

a:active{
  background-color: black;  
  font-size: 20px;
  opacity: 0.85;
}

*{
  box-sizing: border-box;
}
</style>
