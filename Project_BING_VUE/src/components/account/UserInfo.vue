<template>
  <div class="userinfo-container">
    <div class="userinfo-box">
      <div class="userinfo-box-top">
        <div class="userinfo-box-img"></div>
        <div class="userinfo-box-info">
          <div>{{ selectedUser.nickname }}</div>
          <div>{{ selectedUser.email }}</div>
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
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/userStore.js";
import { useFavStore } from "@/stores/favStore.js";
import { ref, computed, onMounted } from "vue";

// router, store
const favStore = useFavStore();
const userStore = useUserStore();

// #init
const props = defineProps({
  selected: String,
});

const isLogin = computed(() => userStore.isLogin);
const loginUser = computed(() => userStore.loginUser);
const selectedUser = computed(() => userStore.selectedUser);
const followingList = computed(() => userStore.followingList);
const followerList = computed(() => userStore.followerList);

const init = async () => {
  // 로그인 체크
  await userStore.doLoginCheck();
  if (isLogin.value) {
    // 세션에서 이메일 가져오기
    await userStore.getUserEmail();
  }
  // 선택된 유저의 정보 가져오기
  await userStore.getOtherUser("닉네임");

  // 선택된 유저의 팔로우 목록 불러오기
  await favStore.getFollowerList(selectedUser.value.email);
  await favStore.getFollowingList(selectedUser.value.email);
};

onMounted(() => {
  init();
});
</script>

<style scoped>
* {
  box-sizing: border-box;
}
.userinfo-container {
  position: fixed;
  width: 100%;
  height: 100vh;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.15);
}

.userinfo-box {
  position: absolute;
  width: 15rem;
  height: 7rem;
  top: 15rem;
  left: 35rem;
  background-color: white;
  border: 2px solid #ccc;
}

.userinfo-box-bottom {
  display: flex;
}
</style>
