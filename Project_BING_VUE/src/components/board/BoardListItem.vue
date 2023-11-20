<template>
  <tr>
    <td>{{ board.num }}</td>
    <td>{{ board.title }}</td>
    <td>{{ board.writer }}</td>
    <td>{{ board.reg_date }}</td>
    <td>{{ board.view_cnt }}</td>
    <!-- <td>{{ board.fav_cnt }}</td> -->
  </tr>
</template>

<script setup>
const props = defineProps({
  board: Object,
});
</script>

<style scoped></style>
