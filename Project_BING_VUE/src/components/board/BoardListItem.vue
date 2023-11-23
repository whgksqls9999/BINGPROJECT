<template>
  <tr>
    <td>{{ board.num }}</td>
    <td>
      <RouterLink
        :to="{ name: 'boardDetail', params: { board_id: board.board_id } }"
        @Click="async () => {await boardStore.updateViewCnt(board.board_id)}">{{ board.title }}</RouterLink
      >
    </td>
    <td>{{ board.writer }}</td>
    <td>{{ board.reg_date }}</td>
    <td>{{ board.view_cnt }}</td>
    <td>{{ board.fav_cnt }}</td>
  </tr>
</template>

<script setup>
import { useBoardStore } from '@/stores/boardStore';
const boardStore = useBoardStore();

const props = defineProps({
  board: Object,
});
</script>

<style scoped>
tr{
  /* color: #f5f5f5; */
}

td{
  padding: 10px;
}
a {
  text-decoration: none;
  color: black;
  /* color: #f5f5f5; */
}
</style>
