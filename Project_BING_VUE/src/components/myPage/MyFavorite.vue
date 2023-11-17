<template>
  <!--장소 게시글 한번에-->
  <div>
    <!--장소-->
    {{ nicknameParam }}님의 정보입니다.
    <div>
      <h4 v-if="myFavLocations.length == 0">회원님이 찜한 장소가 없습니다.</h4>
      <!--찜한 장소 지도에 MARKER로 뜨겠지? 그거 받아오면 될 듯 아 그럼 또 DB 추가해야됨?-->
    </div>
    <!--게시글-->
    <div>
      <h4 v-if="myFavBoards.length == 0">회원님이 찜한 게시글이 없습니다.</h4>
      <table v-else>
        <thead>
          <tr>
            <th>번호</th>
            <th>내용</th>
            <th>작성자</th>
            <th>작성일</th>
          </tr>
        </thead>
        <!--key값.....:key뭘로잡아....-->
        <tbody v-for="favboard in myFavBoards">
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
import { useRoute } from "vue-router";

// 유저 닉네임 받아오기
const route = useRoute();
const nicknameParam = route.params.nickname;

const store = useMyPageStore();
//내가 찜한 장소 불러오기
const myFavLocations = computed(() => {
  return store.myFavLocations;
});

//내가 찜한 게시글 불러오기
const myFavBoards = computed(() => {
  return store.myFavBoards;
});

onMounted(() => {
  // store.getMyFavLocations(); 이것을 살려야 한다 지도 API 必
  store.getMyFavBoards(nicknameParam);
});
</script>

<style scoped></style>
