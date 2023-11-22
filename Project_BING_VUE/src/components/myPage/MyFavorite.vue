<template>
  <!--장소 게시글 한번에-->
  <div class="myFavorite-global">
    <h2>{{ user.nickname }}님의 찜한 장소 목록</h2>
    <br />
    <div class="myFavLocation-container">
      <h3 v-if="myFavLocations.length == 0">
        {{ user.nickname }}님이 찜한 장소가 없습니다.
      </h3>
      <div v-else class="myFavLoction-map"></div>
      <!--찜한 장소 지도에 MARKER로 뜨겠지? 그거 받아오면 될 듯 아 그럼 또 DB 추가해야됨?-->
    </div>
    <!--게시글-->
    <div class="myFavoriteBoard-container">
      <h2>{{ user.nickname }}님의 찜한 게시글 목록</h2>
      <br />
      <h3 v-if="myFavBoards.length == 0">
        {{ user.nickname }}님이 찜한 게시글이 없습니다.
      </h3>
      <table v-else class="myFavoriteBoard-table">
        <thead>
          <tr>
            <th>번호</th>
            <th>내용</th>
            <th>작성자</th>
            <th>작성일</th>
          </tr>
        </thead>
        <!--key값.....:key뭘로잡아....-->
        <tbody v-for="favboard in myFavBoards" :key="favorite_boardId">
          <tr>
            <td>{{ favboard.favorite_boardId }}</td>
            <td>{{ favboard.content }}</td>
            <!--나중에 Boarddetail구현하고 만들것 <td><RouterLink :to="{name: 'BoardDetail', params:{id: favboard.num}}">{{ favboard.title }}</RouterLink></td> -->
            <td>{{ favboard.writername }}</td>
            <td>{{ favboard.reg_date }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { onMounted, computed, ref } from "vue";
import { useMyPageStore } from "@/stores/myPageStore.js";
import { useUserStore } from "@/stores/userStore.js";
import { useRoute } from "vue-router";

// route, store
const route = useRoute();
const userStore = useUserStore();
const store = useMyPageStore();

// 유저 정보 받아오기
const user = computed(() => userStore.user);

// 유저 이메일 받아오기
const emailParam = route.params.email;

//내가 찜한 장소 불러오기
const myFavLocations = computed(() => {
  return store.myFavLocations;
});

//내가 찜한 게시글 불러오기
const myFavBoards = computed(() => {
  return store.myFavBoards;
});

onMounted(async () => {
  // store.getMyFavLocations(); 이것을 살려야 한다 지도 API 必
  await userStore.getUserByEmail(emailParam);
  await store.getMyFavBoards(user.value.nickname);
  console.log(user.value);
  // console.log(myFavBoards.value);
  // console.log(emailParam);
});
</script>

<style scoped>
.myFavorite-global {
  margin-top: 150px;
  display: grid;
  grid-template-rows: 1fr 1fr;
  row-gap: 100px;
}

.myFavLocation-container,
.myFavoriteBoard-container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.myFavoriteBoard-table {
  color: black;
  font-size: 1.3rem;
  font-weight: 500;
  text-align: center;
  border-spacing: 1.3rem;
}
</style>
