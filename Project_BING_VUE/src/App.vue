<template>
  <!-- loading -->
  <TheHeader />
  <RouterView />
  <!-- footer -->
</template>

<script setup>
import { RouterView } from "vue-router";
import TheHeader from "@/components/commons/TheHeader.vue";
</script>

<style scoped></style>
