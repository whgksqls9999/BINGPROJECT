<template>
    <div>
        <RouterLink :to="{ name: myBoards }">내 게시글</RouterLink>
        <RouterLink :to="{ name: myReplys }">내 댓글</RouterLink>
        <RouterLink :to="{ name: myFavor }">내 좋아요</RouterLink>
    </div>
    <RouterView />
</template>

<script setup>
import MyBoards from '@/components/myPage/MyBoards.vue';


</script>

<style scoped></style>