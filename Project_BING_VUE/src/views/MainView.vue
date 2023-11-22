<template>
  <div class="body">
    <h2>Section One Text Block Reveal</h2>
    <h3>Text</h3>
  </div>
</template>

<script setup>
import { useCommonStore } from "@/stores/commonStore.js";
import { onMounted } from "vue";
import { onBeforeRouteLeave } from "vue-router";

// 헤더 fixed toggle
const commonStore = useCommonStore();
onMounted(() => {
  commonStore.toggleHeaderFixed(true);
});
onBeforeRouteLeave(() => {
  commonStore.toggleHeaderFixed(false);
});
</script>

<style scoped>
.body {
  height: 5000px;
  background: linear-gradient(-7deg, rgb(180, 202, 221), rgb(0, 0, 0));
}
</style>
