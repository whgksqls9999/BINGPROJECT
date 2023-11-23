<template>
  <tr>
    <td>{{ reply.reply_id }}</td>
    <td v-if="!isModify">{{ reply.content }}</td>
    <td v-else><input v-model="modifyCotnet" /></td>
    <td>{{ reply.writer }}</td>
    <td>{{ reply.reg_date }}</td>
    <div class="reply-button-item" v-if="isReplyOwner">
      <button class="button1" @click="modifyButton" v-if="!isModify">
        수정
      </button>
      <button class="button1" @click="modifyReply" v-else>등록</button>
      <button class="button2" @click="removeReply(reply)">삭제</button>
    </div>
    <div v-else></div>
  </tr>
</template>

<script setup>
import { computed, ref, onMounted } from "vue";
import { useReplyStore } from "@/stores/replyStore.js";
import { useUserStore } from "@/stores/userStore.js";

const replyStore = useReplyStore();
const userStore = useUserStore();

const props = defineProps({
  reply: Object,
});

// 댓글 하나 의존 변수 변할때마다 올려주기
// const replyOne = computed(() => replyStore.reply);

const isReplyOwner = ref(false);

const isModify = ref(false);

const modifyButton = () => {
  isModify.value = !isModify.value;
  modifyCotnet.value = props.reply.content;
};

const modifyCotnet = ref("");

//댓글 삭제하기
const removeReply = async (reply) => {
  await replyStore.removeReply(reply.reply_id);
  await replyStore.getBoardReplyList(reply.board_id);
  isModify.value = !isModify.value;
};

//댓글 수정하기
const modifyReply = async () => {
  await replyStore.updateReply(props.reply);
  await replyStore.getBoardReplyList(props.reply.board_id);
  props.reply.content = modifyCotnet.value;
  if (props.reply.content === "") {
    alert("내용을 입력해주세요!");
    return;
  }
  isModify.value = !isModify.value;
};

const user = computed(() => userStore.user);
const loginUser = computed(() => userStore.loginUser);

onMounted(async () => {
  await userStore.getUserByEmail(loginUser.value.email);
  isReplyOwner.value = user.value.nickname == props.reply.writer;
});
</script>

<style scoped>
input {
  width: 300px;
  height: 30px;
  border-radius: 10px;
  background-color: aliceblue;
  border-style: none;
  padding: 7px;
}

button {
  border-style: none;
  border-radius: 20px;
  box-shadow: 0 0 6px rgba(0, 0, 0, 0.2);
  font-weight: bold;
  transition: background-color 0.3s ease, color 0.3s ease;
}
tr {
  text-align: center;
  line-height: 3;
}
.reply-button-item {
  margin-top: 10px;
  margin-right: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  gap: 8px;
}

.reply-button-item button {
  height: 30px;
  width: 50px;
}

.reply-button-item .button1 {
  color: white;
  background-color: black;
}
.reply-button-item .button1:hover {
  color: black;
  background-color: white;
}
.reply-button-item .button2 {
  color: white;
  background-color: rgb(216, 67, 67);
}
.reply-button-item .button2:hover {
  color: rgb(216, 67, 67);
  background-color: white;
}
</style>
