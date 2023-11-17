<template>
  <div class="app-global">
    <!-- loading -->
    <div class="app-header">
      <TheHeader />
    </div>
    <div class="app-routerview-container">
      <RouterView />
    </div>
    <div class="app-footer">
      <TheFooter />
    </div>
  </div>
</template>

<script setup>
import { RouterView } from "vue-router";
import TheHeader from "@/components/commons/TheHeader.vue";
import TheFooter from "./components/commons/TheFooter.vue";
</script>

<style scoped>
* {
  font-family: Poppins, "Noto Sans KR", sans-serif;
}
.app-header {
  position: sticky;
  width: 100%;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
}
.app-footer {
  position: fixed;
  width: 100%;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 100;
}
.app-routerview-container {
  /* position: relative; */
  /* margin-top: 7vh; */
  /* margin-bottom: -7vh;  */
}
</style>
