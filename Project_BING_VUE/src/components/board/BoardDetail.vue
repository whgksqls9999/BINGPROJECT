<template>
    <div>
        <h3>게시글 상세</h3>
        {{ idParam }}번 게시글
    </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
import { computed } from 'vue';
const route = useRoute();
const idParam = computed(() => route.params.board_id);
</script>

<style scoped>

</style>