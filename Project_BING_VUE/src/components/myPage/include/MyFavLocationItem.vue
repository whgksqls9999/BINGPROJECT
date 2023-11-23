<template>
  <div class="my-fav-location-item">
    <div class="fav-location-place">
      장소 : {{ favLocation.place_name }}</div>
    <div class="fav-location-address">주소 : {{ favLocation.address_name }}</div>
  </div>
</template>

<script setup>
const props = defineProps({
  favLocation: Object,
});
</script>

<style scoped>
.my-fav-location-item {
  padding: 5px 10px;
  box-shadow: 0 1px 0.1rem grey;
}

.fav-location-place {
  font-weight: bold;
  font-size: 20px;
}
</style>
