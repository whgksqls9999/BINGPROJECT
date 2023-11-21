<template>
  <div class="my-follow-container">
    <div class="my-follow-box">
      <div class="my-follow-box-title">
        <h2>{{ type }}</h2>
        <div class="my-follow-box-close-btn" @click="closeWindow">
          <font-awesome-icon :icon="['fas', 'square-xmark']" />
        </div>
      </div>
      <div class="my-follow-box-list">
        <MyFollowItem v-for="person in toggle" :person="person" :type="type" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore.js";
import MyFollowItem from "./MyFollowItem.vue";

// store
const userStore = useUserStore();

// 팔로우 목록 창 닫기
const emit = defineEmits(["closeWindow"]);
const closeWindow = () => {
  emit("closeWindow");
};

const props = defineProps({
  toggle: Object,
  type: String,
});
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
.my-follow-container {
  position: absolute;
  top: 13rem;
  left: 50px;
  width: 400px;
  height: 10rem;
  background-color: white;
  border: 2px solid #dbdbdb;
  padding: 0.3rem;
  border-radius: 0.5rem;
}

.my-follow-box-title {
  border-bottom: 2px solid #dbdbdb;
  padding-left: 5px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.my-follow-box-list {
  height: 7rem;
  overflow-y: scroll;
}

.my-follow-box-close-btn {
  background-color: white;
  /* border: 1px solid rgb(236, 117, 137);
  border-radius: 0.4rem; */
  padding: 4px;
  color: rgb(236, 117, 137);
  font-size: 24px;
}

.my-follow-box-close-btn:hover {
  color: rgb(231, 87, 111);
}

.my-follow-box-close-btn:active {
  color: rgb(236, 117, 137);
}
</style>
