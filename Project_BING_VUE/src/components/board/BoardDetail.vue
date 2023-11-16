<template>
    <div>
        <h2>보드디테일</h2>
        {{ idParam }}번 게시글
    </div>
</template>

<script setup>
import {useRoute} from 'vue-router';
import {computed} from 'vue';

const route = useRoute();
const idParam = computed(() => {return route.params.board_id})

</script>

<style scoped>

</style>