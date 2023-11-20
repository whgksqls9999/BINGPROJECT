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
        <textarea type="text" v-model="content" />
      </div>
      <!-- 카테고리가 location일 때 -->
      <div class="content-if-location" v-else>
        <label for="">내용</label>
        <textarea type="text" v-model="content" />
        <div class="location-select" @click="doSelectLocation">
          <div v-if="!location" class="map-image">
          </div>
          <div v-else>
            <fieldset>
              <legend>장소명</legend>
              <div>{{ location.place_name }}</div>
            </fieldset>
            <fieldset>
              <legend>주소</legend>
              <div>{{ location.address_name }}</div>
            </fieldset>
            <div>
              <button
                @click.stop="
                  () => {
                    location = '';
                  }
                "
                class="location-delete"
              >
                장소 삭제
              </button>
            </div>
          </div>
        </div>
        <!-- <div class="map" > -->
        <!-- </div> -->
      </div>
      <div class="form-footer">
        <div class="category">
          <span v-if="category == ''">카테고리</span>
          <span v-else> {{ category }}</span>
          <div class="categories">
            <button @click="setCategory('소통')">소통</button>
            <button @click="setCategory('장소추천')">장소 추천</button>
            <button @click="setCategory('카풀')">카풀</button>
            <button @click="setCategory('질문')">질문</button>
          </div>
        </div>
        <button class="submit-btn" @click="doRegistBoard">등록</button>
      </div>
      <BoardWriteMap
        v-if="isSelectLocation"
        @close-window="doSelectLocation"
        @select-place="doSelectPlace"
      />
    </div>
  </div>
</template>

<script setup>
import { useBoardStore } from "@/stores/boardStore.js";
import { useUserStore } from "@/stores/userStore";
import { useLocationStore } from "@/stores/locationStore.js";
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
});

// 커뮤니티 정보
const commBoardList = computed(() => boardStore.commBoardList);
// const num = commBoardList.value == undefined ? 1 : commBoardList.value[commBoardList.value.length - 1].num + 1;

// 작성자 정보
const user = computed(() => userStore.user);
const writer = JSON.parse(
  atob(sessionStorage.getItem("access-token").split(".")[1])
);

// 게시글 등록하기
const doRegistBoard = () => {
  if (category.value === "") {
    alert("카테고리를 설정해주세요");
    return;
  }

  let board = {
    community_id: comm_id,
    num:
      commBoardList.value == undefined
        ? 1
        : commBoardList.value[commBoardList.value.length - 1].num + 1,
    header: category.value,
    title: title.value,
    content: content.value,
    writer: user.value.nickname,
    location_id: location.value.id,
  };

  // 장소추천 글이라면 DB에 장소정보 등록
  if (category.value === "장소추천") {
    locationStore.doGetLocation(location.value.id);
    if (locationStore.location == "") {
      locationStore.doPostLocation(location.value);
    }
  }

  boardStore.registBoard(board, comm_id);
};

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
const doSelectPlace = (place, address) => {
  location.value = place;
  location.value.address_name = address;
  isSelectLocation.value = !isSelectLocation.value;
};

// 카테고리 설정
const category = ref("");
const setCategory = (sel) => {
  category.value = sel;
  if (sel !== "장소추천") {
    location.value = "";
  }
};
</script>

<style scoped>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

@keyframes fadein {
  from {
    opacity: 0;
    transform: translateX(-2rem);
  }
  33% {
    opacity: 0;
  }
  to {
    opacity: 1;
    transform: translateX(0rem);
  }
}

@keyframes background-glow {
  0% {
    background-color: black;
  }
  50% {
    background-color: rgb(24, 24, 24);
  }
  100% {
    background-color: black;
  }
}

.container {
  width: 100vw;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.write-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 40rem;
  gap: 1rem;
}

input,
textarea {
  border-radius: 0.5rem;
  border: 1px solid rgba(0, 0, 0, 0.3);
  box-shadow: 0 1px 0.2rem grey;
  width: 100%;
  padding: 0.3rem;
  padding-top: 1.3rem;
}

.title,
.content,
.content-if-location {
  position: relative;
}

.title,
.content {
  width: 100%;
}

.title {
  animation: fadein 0.5s;
}

.content {
  animation: fadein 1s;
}
.title label,
.content label,
.content-if-location label {
  position: absolute;
  top: 0.2rem;
  left: 0.5rem;
}

.content-if-location {
  animation: fadein 1s;
}

.title input {
  height: 4rem;
}

.content textarea,
.content-if-location textarea {
  height: 10rem;
  resize: none;
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

.content-if-location textarea {
  width: 60%;
}

.location-select {
  border-radius: 0.5rem;
  border: 1px solid rgba(0, 0, 0, 0.3);
  box-shadow: 0 1px 0.2rem grey;
  width: 40%;
  padding: 0.3rem;
  margin-left: 1rem;
}

.form-footer {
  width: 100%;
  display: flex;
  justify-content: space-between;
  animation: fadein 1.5s;
}

.category {
  position: relative;
  border: 1px solid rgba(0, 0, 0, 0.3);
  border-radius: 0.5rem;
  box-shadow: 0 1px 0.2rem grey;
  height: 3rem;
  width: 6rem;
  transition: all 1s ease;
  transition-delay: 100ms;
  display: flex;
  overflow: hidden;
  align-items: center;
  background-color: black;
  color: white;
  /* cursor: pointer; */
}

.category:hover {
  width: 23rem;
  background-color: rgba(0, 0, 0, 0.9);
  box-shadow: 0 1px 0.5rem black;
  animation: background-glow 2s infinite ease;
}

.category span {
  margin-left: 1rem;
}

.categories {
  position: absolute;
  width: 100%;
  left: 7.4rem;
  display: flex;
  align-items: center;
}

.categories button {
  padding: 0.2rem;
  width: 3rem;
  border: none;
  border-radius: 0.3rem;
  margin: 0 0.3rem;
  transition: all 0.2s;
}

.categories button:active {
  transform: translateY(1px);
}
/* 지도 등록 정보 */
fieldset {
  border-radius: 0.3rem;
  padding: 0px 3px 5px 3px;
}

/* 지도 이미지*/
.map-image {
  background-image: url("@/assets/board/map.png");
  background-size: 65%;
  background-position: 50%;
  width: 100%;
  height: 100%;
  transition: opacity 0.1s;
  opacity: 0.6;
}

.map-image:hover {
  opacity: 1;
}

.map-image:active {
  opacity: 0.6;
}

.location-delete {
  margin-top: 10px;
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

.submit-btn:hover {
  background-color: rgba(135, 207, 235, 0.1);
  box-shadow: 0 2px 0.4rem skyblue;
  color: skyblue;
  font-weight: bold;
}
</style>
