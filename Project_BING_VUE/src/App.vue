<template>
  <div class="app-global">
    <TheLoading
      :class="{ 'app-loading': loadingEnd, 'loading-end': !loadingEnd }"
      @click="doLoadingEnd"
    />
    <!-- loading -->
    <div class="app-header" :class="{fixed: isHeaderFixed}">
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
import { ref,computed } from "vue";
import { RouterView } from "vue-router";
import TheHeader from "@/components/commons/TheHeader.vue";
import TheFooter from "./components/commons/TheFooter.vue";
import TheLoading from "./components/commons/TheLoading.vue";
import { useCommonStore } from "@/stores/commonStore";

// 헤더 fixed 상태
const commonStore = useCommonStore();
const isHeaderFixed = computed(() => commonStore.isHeaderFixed);

// loading 클릭 시 꺼지게하기
const loadingEnd = ref(true);
const doLoadingEnd = () => {
  loadingEnd.value = !loadingEnd.value;
  window.scrollTo(0,0);
};
</script>

<style scoped>
* {
  font-family: Poppins, "Noto Sans KR", sans-serif;
}
.fixed{
  position: fixed;
}
.loading-end {
  display: none;
}
.app-loading {
  position: fixed;
  background-color: black;
  width: 100%;
  z-index: 101;
}
.app-header {
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
