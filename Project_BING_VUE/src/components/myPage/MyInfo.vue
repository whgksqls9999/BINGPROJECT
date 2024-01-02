<template>
  <div class="myInfo-global">
    <div class="myInfo-container">
      <div class="myInfo-title">
        <h2>I n f o r m a t i o n</h2>
      </div>
      <div class="myInfo-detail">
        <div class="myInfo-img">
          <img src="@/assets/user.png" />
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
        </div>
        <div class="myInfo-detail-series">
          <div><strong>ID : </strong> {{ user.email }}</div>
          <div><strong>NAME : </strong> {{ user.name }}</div>
          <div><strong>NICKNAME : </strong> {{ user.nickname }}</div>
          <div><strong>GENDER : </strong> {{ user.gender }}</div>
          <div><strong>WITHDRAW TEXT : </strong> {{ user.withdraw_text }}</div>
        </div>
      </div>
      <div class="myInfo-series">
        <div class="myInfo-link">
          <RouterLink :to="{ name: 'modifyInfo' }">CHANGE INFO</RouterLink>
        </div>
        <div class="myInfo-withdraw">
          <RouterLink :to="{ name: 'withdraw' }">LEAVE</RouterLink>
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

onMounted(() => {
  userStore.getUserByEmail(emailParam);
  userStore.getFollowerList(emailParam);
  userStore.getFollowingList(emailParam);
});

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
const doOpenFollowerList = () => {
  userStore.getFollowingList(emailParam)
    .then(() => {
      followListToggle.value = followerList.value;
    })
    followType.value = "Follower";
  };

// 팔로잉 리스트 열기
const doOpenFollowingList = () => {
  userStore.getFollowerList(emailParam)
    .then(() => {
      followListToggle.value = followingList.value;
  })
  followType.value = "Following";
};

// 팔로워(잉) 리스트 닫기
const doCloseFollowList = () => {
  followListToggle.value = "";
};

</script>
<style scoped>
.myInfo-global {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 150px;
}

.myInfo-container {
  border: none;
  border-radius: 20px;
  width: 90%;
  color: #dbdbdb;
  background-color: rgba(0, 0, 0, 0.7);
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
  justify-content: space-evenly;
  padding: 30px;
  gap: 30px;
  /* border-bottom: 1px solid #dbdbdb; */
}

.myInfo-follow {
  border-bottom: 1px solid #dbdbdb;
  display: flex;
  justify-content: center;
  padding-bottom: 0.5rem;
  margin-top: 15px;
}

table {
  width: 150px;
  /* padding-top: 20px; */
  /* padding-bottom: 10px; */
  text-align: center;
  border-collapse: separate;
  border-spacing: 3px;
  border-top: 1px solid #dbdbdb;
}
.myInfo-img {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.myInfo-img img {
  border-radius: 50%;
  width: 150px;
  height: 150px;
}

.myInfo-detail-series {
  letter-spacing: 2px;
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
  /* border: 1px solid #dbdbdb; */
  border-radius: 5px;
  color: #dbdbdb;
}

.myInfo-link a:hover {
  background-color: #aecadb;
  color: white;
}
.myInfo-withdraw a:hover {
  color: #dbdbdb;
  background-color: rgb(216, 67, 67);
}

.follower,
.following {
  cursor: pointer;
  color: #dbdbdb;
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
