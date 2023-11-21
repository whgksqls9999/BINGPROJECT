<template>
  <div class="myInfo-global">
    <div class="myInfo-container">
      <div class="myInfo-title">
        <h2>{{ user.nickname }}님의 정보</h2>
      </div>
      <div class="myInfo-detail">
        <div class="myInfo-img">
          <img src="@/assets/군싹.jpeg" />
        </div>
        <div class="myInfo-detail-series">
          <div><strong>아이디:</strong> {{ user.email }}</div>
          <div><strong>이름:</strong> {{ user.name }}</div>
          <div><strong>닉네임:</strong> {{ user.nickname }}</div>
          <div><strong>성별:</strong> {{ user.gender }}</div>
          <div><strong>탈퇴 문구:</strong> {{ user.withdraw_text }}</div>
        </div>
      </div>
      <div class="myInfo-follow">
        <table>
          <thead>
            <tr>
              <th class="following" @click="doOpenFollowingList">팔로잉</th>
              <th class="follower" @click="doOpenFollowerList">팔로워</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td class="following" @click="doOpenFollowingList">
                {{ followingList.length }}
              </td>
              <td class="follower" @click="doOpenFollowerList">
                {{ followerList.length }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="myInfo-series">
        <div class="myInfo-link">
          <RouterLink :to="{ name: 'modifyInfo' }">내 정보 수정하기</RouterLink>
        </div>
        <div class="myInfo-withdraw">
          <RouterLink :to="{ name: 'withdraw' }">해빙 탈퇴하기</RouterLink>
        </div>
      </div>
      <MyFollow
        v-if="followListToggle !== ''"
        :toggle="followListToggle"
        :type="followType"
        :email="emailParam"
        @close-window="doCloseFollowList"
      />
    </div>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { useUserStore } from "@/stores/userStore.js";
import { computed, onMounted, ref } from "vue";
import MyFollow from "./include/MyFollow.vue";

// 유저 닉네임 받아오기
const route = useRoute();
const emailParam = route.params.email;

// 유저 정보 가져오기
const userStore = useUserStore();
const user = computed(() => userStore.user);

// 팔로워 정보 가져오기
const followerList = computed(() => userStore.followerList);
const followingList = computed(() => userStore.followingList);

// 팔로잉, 팔로워 목록 창
const followListToggle = ref("");
const followType = ref("");

// 팔로워 리스트 열기
const doOpenFollowerList = async() => {
  await userStore.getFollowingList(emailParam);
  followListToggle.value = followerList.value;
  followType.value = "Follower";
};

// 팔로잉 리스트 열기
const doOpenFollowingList = async() => {
  await userStore.getFollowerList(emailParam);
  followListToggle.value = followingList.value;
  followType.value = "Following";
};

// 팔로워(잉) 리스트 닫기
const doCloseFollowList = () => {
  followListToggle.value = "";
};

// // 팔로워 목록 갱신
// const doRenewFollower = async () =>  {
//   await userStore.getFollowerList(emailParam);
//   followListToggle.value = followerList.value;
// }

// 팔로잉 목록 갱신
const doRenewFollowing = async () => {
  await userStore.getFollowingList(emailParam);
  followListToggle.value = followingList.value;
}

onMounted(() => {
  userStore.getUserByEmail(emailParam);
  userStore.getFollowerList(emailParam);
  userStore.getFollowingList(emailParam);
});
</script>
<style scoped>
.myInfo-global {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 150px;
}

.myInfo-container {
  border: 3px solid #dbdbdb;
  border-radius: 8px;
  width: 500px;
  background-color: white;
  position: relative;
}

.myInfo-title {
  font-size: 25px;
  text-align: center;
  padding: 20px;
  border-bottom: 1px solid #dbdbdb;
}

.myInfo-detail {
  display: flex;
  align-items: center;
  justify-content: space-around;
  padding: 20px;
  /* border-bottom: 1px solid #dbdbdb; */
}

.myInfo-follow {
  border-bottom: 1px solid #dbdbdb;
  display: flex;
  justify-content: center;
  padding-bottom: 0.5rem;
  margin-top: -15px;
}

table {
  text-align: center;
  border-collapse: separate;
  border-spacing: 110px 0;
}

.myInfo-img img {
  border-radius: 50%;
  width: 100px;
  height: 100px;
}

.myInfo-detail-series {
  display: flex;
  flex-direction: column;
  margin-left: 20px;
}

.myInfo-detail-series div {
  margin: 10px 0;
}

.myInfo-series {
  display: flex;
  justify-content: space-around;
  padding: 15px;
  margin: 10px auto;
}

.myInfo-link a,
.myInfo-withdraw a {
  text-decoration: none;
  padding: 10px;
  border: 1px solid #dbdbdb;
  border-radius: 5px;
  color: #262626;
}

.myInfo-link a:hover {
  background-color: #aecadb;
  color: white;
}
.myInfo-withdraw a:hover {
  color: whitesmoke;
  background-color: rgb(216, 67, 67);
}

.follower,
.following {
  cursor: pointer;
}
</style>

<!-- <style scoped>
.myInfo-global {
  border: 3px solid black;
  border-radius: 30px;
  padding: 20px;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 150px;
  height: 50vh;
  width: 50vh;
}

img {
  border-radius: 50%;
  width: 100px;
}
.myInfo-detail-series {
  letter-spacing: 3px;
  font-size: 20px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.myInfo-title {
  font-size: 24px;
  /* color: white; */
}

.myInfo-detail {
  padding: 15px;
  border: 3px solid rgba(128, 123, 123, 0.5);
  border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px;
  gap: 2.5rem;
  font-weight: 700;
}

.myInfo-detail div {
  margin-bottom: 10px;
}
.myInfo-series {
  display: flex;
  justify-content: center;
}

.myInfo-link {
  margin-right: 10px;
  margin-top: 20px;
}

.myInfo-link a {
  text-decoration: none;
  padding: 10px;
  background-color: #333;
  color: white;
  border-radius: 5px;
}

.myInfo-link a:hover {
  background-color: #555;
}
.myInfo-withdraw {
  margin-top: 20px;
}

.myInfo-withdraw a {
  text-decoration: none;
  padding: 10px;
  background-color: #e02b2b;
  color: white;
  border-radius: 5px;
}

.myInfo-withdraw a:hover {
  background-color: #ff0000;
}
</style> -->
