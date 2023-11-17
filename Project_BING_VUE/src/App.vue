<template>
  <!-- loading -->
  <TheHeader />
  <RouterView />
  <!-- <Kakaotest /> -->
  <Kakaotest2 />
  <!-- footer -->
</template>

<script setup>
import { RouterView } from "vue-router";
import TheHeader from "@/components/commons/TheHeader.vue";
import Kakaotest from "./components/kakaotest.vue";
import Kakaotest2 from "./components/kakaotest2.vue";
</script>

<style scoped></style>
