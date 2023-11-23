<template>
  <div class="write-view-container">
    <div class="write-form-container">
      <div class="write-form-cap">
        <div>{{ comm_id == 1 ? 'FREEDIVING' : 'SKINSCUBA' }}</div>
        <div class="title">Post Registration</div>
      </div>
      <BoardWriteForm />
    </div>
  </div>
</template>

<script setup>
import BoardWriteForm from "../components/board/BoardWriteForm.vue";
import { useCommonStore } from "../stores/commonStore";
import { onMounted } from "vue";
import { useRoute } from 'vue-router';
// Store
const commonStore = useCommonStore();

const route = useRoute();
const comm_id = route.params.community_id;

// 헤더 fixed toggle
onMounted(() => {
  commonStore.toggleHeaderFixed(false);
  commonStore.toggleFooterFixed(true);
});
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.write-view-container {
  margin-top: 140px;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.write-form-container {
  width: 50%;
  padding: 1rem;
  border-radius: 1rem;
  overflow: hidden;
  position: relative;
  box-shadow: 0 0 10px black;
}

.title {
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 0.3rem;
  animation: fadein 1s;
  width: 100%;
}
.write-form-cap{
  position: absolute;
  background-color: rgba(51, 51, 51);
  margin-bottom: 5rem;
  top: 0;
  left: 0;
  width: 100%;
  color: #e5e5e5;
  height: 5rem;
  padding-top: 0.4rem;
  text-align: center;
}

@keyframes fadein {
  from {
    opacity: 0;
  }

  to {
    opacity: 1;
  }
}
</style>
