<template>
  <div class="container">
    <div class="write-form">
      <div class="title">
        <label for="">제목</label>
        <input type="text" v-model="title" />
      </div>
      <!-- 카테고리가 location이 아닐 때 -->
      <div class="content" v-if="category != '장소추천'">
        <label for="">내용</label>
        <input type="text" v-model="content" />
      </div>
      <!-- 카테고리가 location일 때 -->
      <div class="content-if-location" v-else>
        <label for="">내용</label>
        <input type="text" v-model="content" />
        <div class="location-select" @click="doSelectLocation">
          <div v-if="!location"> 여기를 누르면 장소를 첨부할 지도를 표시한다.</div>
          <div v-else>
            <div>{{ location.place_name }}</div>
            <div><button @click.stop="() => { location = '' }">장소 삭제</button></div>
          </div>
        </div>
        <!-- <div class="map" > -->
        <!-- </div> -->
      </div>
      <div class="form-footer">
        <div class="category">
          <span>카테고리</span>
          <div class="categories">
            <button @click="setCategory('소통')">소통</button> |
            <button @click="setCategory('장소추천')">장소 추천</button> |
            <button @click="setCategory('카풀')">카풀</button> |
            <button @click="setCategory('질문')">질문</button>
          </div>
        </div>
        <button class="submit-btn" @click="doRegistBoard">등록</button>
      </div>
      <BoardWriteMap class="map" v-if="isSelectLocation" @close-window="doSelectLocation" @select-place="doSelectPlace" />
    </div>
  </div>
</template>

<script setup>
import { useBoardStore } from '@/stores/boardStore.js';
import { useUserStore } from '@/stores/userStore';
import { useLocationStore } from '@/stores/locationStore.js';
import { useRoute } from "vue-router";
import { ref, computed, onMounted } from "vue";
import BoardWriteMap from "@/components/board/BoardWriteMap.vue";
// Store
const boardStore = useBoardStore();
const userStore = useUserStore();
const locationStore = useLocationStore();

onMounted(() => {
  boardStore.getCommBoardList(comm_id);
  userStore.getUserByEmail(writer.email);
})

// 커뮤니티 정보
const commBoardList = computed(() => boardStore.commBoardList);
const num = commBoardList.value == undefined ? 1 : commBoardList.value[commBoardList.value.length - 1].num + 1;

// 작성자 정보
const user = computed(() => userStore.user);
const writer = JSON.parse(atob(sessionStorage.getItem('access-token').split(".")[1]));

// 게시글 등록하기
const doRegistBoard = () => {
  let board = {
    'community_id': comm_id,
    'num': num,
    'header': category.value,
    'title': title.value,
    'content': content.value,
    'writer': user.value.nickname,
    'location_id': location.value.id,
  }

  if(category.value === '장소추천'){
    locationStore.doGetLocation(location.value.id);
    if (locationStore.location == ''){
      locationStore.doPostLocation(location.value);
    }
  }

  boardStore.registBoard(board, comm_id);
}


// 어떤 커뮤니티에 등록될 게시글인지 커뮤니티 번호 가져오기
const route = useRoute();
const comm_id = route.params.community_id;

// 게시글 제목, 내용 등등 채우기
const title = ref("");
const content = ref("");
const location = ref("");

// 장소 선택란 클릭시 지도 뜨도록 설정
const isSelectLocation = ref(false);
const doSelectLocation = () => {
  isSelectLocation.value = !isSelectLocation.value;
};

// 선택된 장소 정보 가져오기
const doSelectPlace = (place) => {
  console.log(place);
  location.value = place;
  isSelectLocation.value = !isSelectLocation.value;
}

// 카테고리 설정
const category = ref("소통");
const setCategory = (sel) => {
  category.value = sel;
  if(sel !== '장소추천'){
    location.value = '';
  }
};
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.container {
  margin-top: 70px;
  width: 100vw;
  display: flex;
  justify-content: center;
  align-items: center;
}

.write-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 30rem;
  gap: 1rem;
}

input {
  border-radius: 0.5rem;
  border: 1px solid rgba(0, 0, 0, 0.3);
  box-shadow: 0 1px 0.2rem grey;
  width: 30rem;
  padding: 0.3rem;
}

.title,
.content,
.content-if-location {
  position: relative;
}

.title label,
.content label,
.content-if-location label {
  position: absolute;
  top: 0.3rem;
  left: 0.5rem;
}

.title input {
  height: 3rem;
}

.content input,
.content-if-location input {
  height: 10rem;
}

/* 장소 추천 글일 때 컨텐트 div 설정 */
.content-if-location {
  display: flex;
  width: 100%;
}

.content-if-location label {
  position: absolute;
  top: 0.3rem;
  left: 0.5rem;
}

.content-if-location input {
  width: 60%;
}

.location-select {
  border-radius: 0.5rem;
  border: 1px solid rgba(0, 0, 0, 0.3);
  box-shadow: 0 1px 0.2rem grey;
  width: 40%;
  padding: 0.3rem;
}

.form-footer {
  width: 100%;
  display: flex;
  justify-content: space-between;
}

.category {
  position: relative;
  border: 1px solid rgba(0, 0, 0, 0.3);
  border-radius: 0.5rem;
  box-shadow: 0 1px 0.2rem grey;
  height: 3rem;
  width: 6rem;
  transition: width 1s ease;
  transition-delay: 100ms;
  display: flex;
  overflow: hidden;
  display: flex;
  align-items: center;
}

.category:hover {
  width: 23rem;
}

.categories {
  position: absolute;
  width: 100%;
  left: 8rem;
}

.categories button {
  padding: 0.2rem;
  width: 3rem;
  border: none;
  border-radius: 0.3rem;
}

.submit-btn {
  background-color: skyblue;
  border: none;
  border-radius: 0.5rem;
  box-shadow: 0 1px 0.2rem grey;
  width: 4rem;
  color: white;
  transition: all 0.5s;
}
</style>
