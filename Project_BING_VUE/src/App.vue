<template>
  <div class="app-global">
    <TheLoading
      :class="{ 'app-loading': loadingEnd, 'loading-end': !loadingEnd }"
      @click="doLoadingEnd"
    />
    <!-- loading -->
    <div class="app-header">
      <TheHeader class="router-header" />
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
import { ref } from "vue";
import { RouterView } from "vue-router";
import TheHeader from "@/components/commons/TheHeader.vue";
import TheFooter from "./components/commons/TheFooter.vue";
import TheLoading from "./components/commons/TheLoading.vue";

const loadingEnd = ref(true);
const doLoadingEnd = () => {
  loadingEnd.value = !loadingEnd.value;
};
</script>

<style scoped>
* {
  font-family: Poppins, "Noto Sans KR", sans-serif;
}
.loading-end {
  display: none;
}
.app-loading {
  position: fixed;
  background-color: white;
  width: 100%;
  z-index: 101;
}
.app-header {
  position: fixed;
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
</style>
