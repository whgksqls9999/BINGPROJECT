<template>
  <div class="map-container">
    <div id="map"></div>
    <div class="board-detail-location-info"></div>
    <span v-if="isLogin">
      <button
        class="fav-location-btn"
        v-if="isFavoredLocation == ''"
        @click="doFavLocation"
      >
        <font-awesome-icon :icon="['fas', 'star']" /> 찜하기
      </button>
      <button
        class="fav-cancel-location-btn"
        v-else
        @click="doFavLocationCancel"
      >
        <font-awesome-icon :icon="['fas', 'star']" /> 찜취소
      </button>
    </span>
  </div>
</template>

<script setup>
import { onMounted, ref, computed, onBeforeMount } from "vue";
import { useLocationStore } from "@/stores/locationStore.js";
import { useBoardStore } from "@/stores/boardStore.js";
import { useUserStore } from "@/stores/userStore.js";
import { useFavStore } from "@/stores/favStore.js";
import { useRoute } from "vue-router";

// router, store
const locationStore = useLocationStore();
const boardStore = useBoardStore();
const userStore = useUserStore();
const favStore = useFavStore();
const route = useRoute();

// 유저 로그인 체크
const isLogin = computed(() => userStore.isLogin);

// 유저 토큰 정보
const loginUser = computed(() => userStore.loginUser);

//유저 정보
const user = computed(() => userStore.user);

// 게시글 정보
const boardOne = computed(() => boardStore.boardOne);

// 게시글 id
const idParam = computed(() => route.params.board_id);

// 이미 찜한 장소인지 체크
const isFavoredLocation = computed(() => favStore.isFavoredLocation);

const doFavLocation = async () => {
  let favLocation = {
    favorite_locationId: 0,
    writername: user.value.nickname,
    location_id: location.value.location_id,
  };

  favStore.doFavLocation(favLocation, user.value.nickname);
  location.value.fav_cnt++;
  await locationStore.updateLocation(location.value);
  locationStore.doGetLocation(location.value.location_id);
};

const doFavLocationCancel = async () => {
  favStore.doFavLocationCancel(
    isFavoredLocation.value,
    user.value.nickname,
    location.location_id
  );
  location.value.fav_cnt--;
  await locationStore.updateLocation(location.value);
  locationStore.doGetLocation(location.value.location_id);
};
// const doFavBoard = async () => {
//   let favBoard = {
//     favorite_boardId: 0,
//     board_id: idParam.value,
//     writername: user.value.nickname,
//   };

//   favStore.doFavBoard(favBoard, user.value.nickname);
//   boardOne.value.fav_cnt++;
//   await boardStore.updateBoard(boardOne.value);
//   boardStore.getBoard(idParam.value);
// };
// location_id를 props로 가져오기
const props = defineProps({
  location: Number,
});

// 장소 정보
const location = computed(() => locationStore.location);

let keyword = ref("");
let infowindow = "";

const search = () => {
  // 장소 검색을 위한 객체 생성
  let ps = new kakao.maps.services.Places();
  // 키워드로 장소 검색
  ps.keywordSearch(keyword.value, placesSearchCB);
};

let map = null;
const initMap = () => {
  let mapContainer = document.getElementById("map"); // 지도를 표시할 div
  let mapOption = {
    // center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
    center: new kakao.maps.LatLng(
      Number(location.value.latitude),
      Number(location.value.longitude)
    ), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
  };
  map = new kakao.maps.Map(mapContainer, mapOption);

  infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
  displayMarker(location.value);

  // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
  const mapTypeControl = new kakao.maps.MapTypeControl();

  // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
};

// 마커 표시 함수
const displayMarker = (place) => {
  let marker = new kakao.maps.Marker({
    map: map,
    position: new kakao.maps.LatLng(place.latitude, place.longitude),
  });
  infowindow.setContent(
    `<div class="map-info" style="padding:3px;font-size:12px;width:100%;display:flex">
          <div>
            <div style="font-weight: bold; font-size: 14px">${place.place_name}</div>
            <div style="width:160px">${place.address_name}</div>
          </div>
        </div>`
  );
  infowindow.open(map, marker);
};

// 키워드 검색 함수
const placesSearchCB = (data, status, pagination) => {
  if (status === kakao.maps.services.Status.OK) {
    // LatLngBounds 객체에 좌표 추가
    // 검색 장소 위치 기준으로 지도 범위 재설정 위함
    let bounds = new kakao.maps.LatLngBounds();
    for (let i = 0; i < data.length; ++i) {
      displayMarker(data[i]);
      bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
    }

    //  검색 위치 기준으로 지도 범위 재설정
    map.setBounds(bounds);
  }
};

onMounted(async () => {
  // 게시글 정보 갱신하기
  await boardStore.getBoard(idParam.value);

  console.log("게시글에서 받아온 보드아이디", boardOne.value.board_id);
  // 장소 정보 가져오기
  await locationStore.doGetLocation(boardOne.value.location_id);

  // 유저 로그인 체크
  userStore.doLoginCheck();

  //로그인 상태일 때
  if (isLogin.value) {
    await userStore.getUserEmail(); // 토큰 가져오기
    await userStore.getUserByEmail(loginUser.value.email); // 정보 가져오기
    await favStore.doFavLocationCheck(
      user.value.nickname,
      location.value.location_id
    ); // 이미 찜한 장소인지 체크하기
  }

  // 지도 생성하기
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script"); // autoload=false 스크립트를 동적으로 로드하기 위해서 사용
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_API_KEY
    }&libraries=services`;
    script.addEventListener("load", () => {
      kakao.maps.load(initMap);
    }); //헤드태그에 추가
    document.head.appendChild(script);
  }

  console.log("불러와진 장소의 아이디", location.value.location_id);
});
</script>

<style scoped>
#map {
  width: 100%;
  height: 100%;
  z-index: 0;
  border-right: 1px solid #ccc;
}

.map-container {
  display: flex;
  overflow: hidden;
  border: 1px solid #ccc;
  border-radius: 20px;
  width: 100%;
  height: 15rem;
  position: relative;
}

.fav-cancel-location-btn,
.fav-location-btn {
  background-color: white;
  border-radius: 0.3rem;
  position: absolute;
  right: 2.6rem;
  bottom: 4.83%;
  border: none;
  box-shadow: 0 2px 0.1rem #ccc;
  padding: 5px;
}

.fav-location-btn {
  color: rgb(51, 150, 255);
}

.fav-cancel-location-btn {
  background-color: rgb(255, 51, 51);
  color: white;
}

.fav-location-btn:hover {
  background-color: rgb(51, 150, 255);
  color: white;
}
.fav-cancel-location-btn:hover {
  background-color: white;
  color: rgb(255, 51, 51);
}
</style>
