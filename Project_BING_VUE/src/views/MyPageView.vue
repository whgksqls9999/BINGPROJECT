<template>
    <div>
        <RouterLink :to="{ name: myBoards }">나의 글 목록</RouterLink>
        <RouterLink :to="{ name: myFavorLocation }">내가 찜한 장소</RouterLink>
        <RouterLink :to="{ name: myFavorBoard }">내가 찜한 게시글</RouterLink>

    </div>
    <RouterView />
</template>

<script setup>
import MyBoards from '@/components/myPage/MyBoards.vue';
import {useMyPageStore} from '@/stores/mypage.js';


</script>

<style scoped></style>