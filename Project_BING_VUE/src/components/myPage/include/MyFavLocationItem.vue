<template>
  <div class="my-fav-location-item">
    <div>{{ favLocation.place_name }}</div>
    <div>{{ favLocation.address_name }}</div>
  </div>
</template>

<script setup>
const props = defineProps({
  favLocation: Object,
});
</script>

<style scoped></style>
