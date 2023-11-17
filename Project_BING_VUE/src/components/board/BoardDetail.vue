<template>
    <div>
        <h3>게시글 상세</h3>
        {{ idParam }}번 게시글
        <div>댓글</div>
        <div v-for="reply in boardReplyList">{{ reply }}</div>
    </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
import { computed, onMounted, watch } from 'vue';
import { useReplyStore } from '@/stores/replyStore.js';

const route = useRoute();
const idParam = computed(() => route.params.board_id);

const replyStore = useReplyStore();
const boardReplyList = computed(() => replyStore.boardReplyList);

onMounted(() => {
    replyStore.getBoardReplyList(idParam.value);
})

console.log(route);
// watch(route.path,() => {
//     // replyStore.getBoardReplyList(idParam.value);
//     console.log('hi');
// })
</script>

<style scoped>

</style>