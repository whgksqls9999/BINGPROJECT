<template>
  <tr>
    <td>{{ board.num }}</td>
    <td>
      <RouterLink
        :to="{ name: 'boardDetail', params: { board_id: board.board_id } }"
        >{{ board.title }}</RouterLink
      >
    </td>
    <td>{{ board.writer }}</td>
    <td>{{ board.reg_date }}</td>
    <td>{{ board.view_cnt }}</td>
    <td>{{ board.fav_cnt }}</td>
  </tr>
</template>

<script setup>
const props = defineProps({
  board: Object,
});
</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
}
</style>
