<template>
  <div class="my-follow-container-outer"></div>
  <div class="my-follow-container">
    <div class="my-follow-box">
      <div class="my-follow-box-title">
        <h2>{{ type }}</h2>
        <div class="my-follow-box-close-btn" @click="closeWindow">
          <font-awesome-icon :icon="['fas', 'square-xmark']" />
        </div>
      </div>
      <div class="my-follow-box-list">
        <MyFollowItem
          v-for="person in followList"
          :person="person"
          :type="type"
          @renew-follow="renewFollow"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore.js";
import { useFavStore } from "@/stores/favStore.js";
import MyFollowItem from "./MyFollowItem.vue";
import { computed, onMounted, ref, watch } from "vue";

// store, emit
const userStore = useUserStore();
const favStore = useFavStore();
const emit = defineEmits(["closeWindow"]);

const props = defineProps({
  toggle: Array,
  email: String,
  type: String,
});

const followingList = computed(() => {
  return userStore.followingList;
});
const followerList = computed(() => userStore.followerList);
const followList = ref(props.toggle);

// 팔로우 취소 후 목록 갱신
const renewFollow = async () => {
  await userStore.getFollowingList(props.email);
  followList.value = followingList.value;
};

// 팔로우 목록 창 닫기
const closeWindow = () => {
  emit("closeWindow");
};

if (props.type == "Follower") {
  followList.value = followerList.value;
} else if (props.type == "Following") {
  followList.value = followingList.value;
}

onMounted(() => {});

// onMounted(() => {
//   userStore.getFollowingList(props.email);
//   userStore.getFollowerList(props.email);
// })
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.my-follow-container-outer {
  z-index: 3;
  height: 100vh;
  width: 100vw;
  background-color: rgba(128, 128, 128, 0.3);
  position: fixed;
  top: 0;
  left: 0;
  z-index: 2;
}
.my-follow-container {
  position: absolute;
  top: -2rem;
  left: 25%;
  width: 400px;
  height: 33rem;
  background-color: rgba(0,0,0,0.8);
  border: 1px solid #dbdbdb;
  padding: 0.3rem;
  border-radius: 0.5rem;
  z-index: 3;
}

.my-follow-box-title {
  border-bottom: 1px solid #dbdbdb;
  padding-left: 5px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.my-follow-box-list {
  height: 30rem;
  overflow-y: scroll;
}

.my-follow-box-close-btn {
  /* background-color: white; */
  /* border: 1px solid rgb(236, 117, 137);
  border-radius: 0.4rem; */
  padding: 4px;
  color: rgb(216, 67, 67);
  font-size: 24px;
}

.my-follow-box-close-btn:hover {
  color:rgb(216, 67, 67);
}

.my-follow-box-close-btn:active {
  color: rgb(216, 67, 67);
}
</style>
