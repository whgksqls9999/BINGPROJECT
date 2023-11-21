<template>
  <div class="my-follow-item-container">
    <div class="my-follow-item-left">
      <img src="@/assets/군싹.jpeg" alt="프로필" width="70" />
    </div>
    <div class="my-follow-item-center">
      <div>
        <strong>{{ person.nickname }}</strong>
      </div>
      <div>{{ person.email }}</div>
    </div>
    <div class="my-follow-item-right">
      <button class="my-follow-item-cancel" @click="doFollowCancel" v-if="type=='Following'">
        팔로우 취소
      </button>
    </div>
  </div>
</template>

<script setup>
import { computed } from "vue";
import { useUserStore } from "@/stores/userStore.js";
// store
const userStore = useUserStore();

const user = computed(() => userStore.user);

// 팔로우 취소
const doFollowCancel = async () => {
  await userStore.doFollowCancel(props.person.follow_id, user.value.email);
  doRenewFollowList();
};

const emit = defineEmits(['renewFollow']);
const  doRenewFollowList = () => {
    emit('renewFollow');
}

const props = defineProps({
  person: Object,
  type: String,
});
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.my-follow-item-container {
  display: flex;
  padding: 10px;
  gap: 15px;
  align-items: center;
  border-bottom: 1px solid #dbdbdb;
  margin-right: 7px;
}

.my-follow-item-center {
  text-overflow: ellipsis;
  width: 12.5rem;
}

.my-follow-item-cancel {
  background-color: white;
  border: 1px solid rgb(236, 117, 137);
  color: rgb(236, 117, 137);
  padding: 1.8px;
  border-radius: 0.2rem;
  width: 3.4rem;
  transition: all 0.1s;
}

.my-follow-item-cancel:hover {
  color: white;
  background-color: rgb(236, 117, 137);
}
img {
  border-radius: 50%;
}
</style>
