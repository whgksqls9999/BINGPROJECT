<template>
  <div class="userinfo-container">
    <div class="userinfo-box">
      <div class="userinfo-box-top">
        <div class="userinfo-box-img">
          <img src="@/assets/군싹.jpeg" width="100" alt="" />
        </div>
        <div class="userinfo-box-info">
          <div>{{ selectedUser.nickname }}</div>
          <div>{{ selectedUser.email }}</div>
          <div v-if="!selfCheck">
            <button
              v-if="isFollowed == ''"
              @click="doFollow"
              class="follow-btn"
            >
              ❤ 팔로우
            </button>
            <button v-else @click="doFollowCancel" class="follow-cancel-btn">
              ❤ 팔로우
            </button>
          </div>
        </div>
      </div>
      <div class="userinfo-box-bottom">
        <div class="userinfo-box-following">
          팔로워
          <br />
          {{ followerList.length }}
        </div>
        <div class="userinfo-box-follower">
          팔로잉
          <br />
          {{ followingList.length }}
        </div>
      </div>
      <button class="userinfo-close-btn" @click="doClose">
        <font-awesome-icon :icon="['fas', 'xmark']" />
      </button>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore.js";
import { useFavStore } from "@/stores/favStore.js";
import { ref, computed, onMounted, watch } from "vue";

// router, store
const favStore = useFavStore();
const userStore = useUserStore();

// #팔로우 하기
const doFollow = async () => {
  const follow = {
    follow_from: loginUser.value.email,
    follow_to: selectedUser.value.email,
  };

  await userStore.doFollow(follow, selectedUser.value.email);
  init();
};

// #팔로우 취소하기
const doFollowCancel = async () => {
  await userStore.doFollowCancel(isFollowed.value, selectedUser.value.email);
  init();
};

// #init
let props = defineProps({
  selected: String,
});

const isLogin = computed(() => userStore.isLogin);
const loginUser = computed(() => userStore.loginUser);
const selectedUser = computed(() => userStore.selectedUser);
const followingList = computed(() => userStore.followingList);
const followerList = computed(() => userStore.followerList);
const isFollowed = ref("");
const selfCheck = ref(false);

const init = async () => {
  selfCheck.value = false;
  // 로그인 체크
  await userStore.doLoginCheck();
  if (isLogin.value) {
    // 세션에서 이메일 가져오기
    await userStore.getUserEmail();
  }
  // 선택된 유저의 정보 가져오기
  // await userStore.getOtherUser("props.");
  // if (props.selected == "") {
  // } else {
  // }

  if (props.selected != "") {
    await userStore.getOtherUser(props.selected);
  }

  // 선택된 유저의 팔로우 목록 불러오기
  await userStore.getFollowerList(selectedUser.value.email);
  await userStore.getFollowingList(selectedUser.value.email);

  console.log(selectedUser.value.email);
  console.log(loginUser.value.email);

  const check = ref(false);
  if (isLogin) {
    if (followerList.value != "") {
      followerList.value.forEach((element) => {
        if (element.follow_from == loginUser.value.email) {
          check.value = true;
          isFollowed.value = element.follow_id;
        }
      });
    }
  }
  if (!check.value) {
    isFollowed.value = "";
  }
  if (!isLogin.value || selectedUser.value.email == loginUser.value.email) {
    selfCheck.value = true;
  }

  console.log(selfCheck.value);
  console.log(isFollowed.value);
};

const emit = defineEmits(["closeWindow"]);
const doClose = () => {
  emit("closeWindow");
};

watch(() => {
  init();
  console.log(props.selected);
});
</script>

<style scoped>
.userinfo-container {
  position: fixed;
  width: 100%;
  height: 100vh;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.05);
  z-index: 3;
}

.userinfo-box {
  position: absolute;
  position: relative;
  width: 15rem;
  height: 10rem;
  top: 25%;
  left: calc(50% - 7.5rem);
  background-color: white;
  border: 2px solid #ccc;
  padding: 1rem;
  animation: fadein 0.3s ease;
  border-radius: 1rem;
  box-shadow: 0 2px 0.3rem black;
}

@keyframes fadein {
  from {
    opacity: 0;
    transform: translateY(1rem);
  }
  to {
    opacity: 1;
    transform: translateY(0rem);
  }
}

.userinfo-box-top {
  display: flex;
}

.userinfo-box-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-left: 1rem;
}

.userinfo-box-top img {
  border-radius: 50%;
}
.userinfo-box-bottom {
  margin-top: 1rem;
  display: flex;
  justify-content: space-evenly;
  text-align: center;
  font-weight: bold;
}

.userinfo-close-btn {
  position: absolute;
  background-color: transparent;
  border: none;
  font-size: 30px;
  left: 7.5rem;
  top: 13rem;
  animation: fadein 0.6s ease;
  animation: move 4s infinite ease;
}

@keyframes move {
  from,
  to {
    transform: translateY(0);
  }
  50% {
    transform: translateY(3px);
  }
}
.follow-cancel-btn,
.follow-btn {
  position: absolute;
  box-shadow: 0 2px 0.1rem #ccc;
  background-color: white;
  border-radius: 0.3rem;
  border: none;
  font-size: 15px;
  color: pink;
  transition: all 0.3s;
  padding: 0.4 0.5rem;
  left: 3.4rem;
  top: 6rem;
}

.follow-btn:hover,
.follow-cancel-btn,
.follow-cancel-btn:active {
  color: white;
  background-color: pink;
}

.follow-cancel-btn:active,
.follow-btn,
.follow-btn:active {
  color: pink;
  background-color: white;
}
</style>
