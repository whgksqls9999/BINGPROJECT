<template>
  <!--장소 게시글 한번에-->
  <div class="myFavorite-global">
    <div class="myFavLocation-container">
      <h2>{{ user.nickname }}님의 찜한 장소 목록</h2>
      <!-- <h3 v-if="favLocationList.length == 0">
        {{ user.nickname }}님이 찜한 장소가 없습니다.
      </h3> -->
      <div class="myFavLocation-map">
        <div id="map"></div>
      </div>
      <div class="myFavLocation-list-box"></div>
      <!--찜한 장소 지도에 MARKER로 뜨겠지? 그거 받아오면 될 듯 아 그럼 또 DB 추가해야됨?-->
    </div>
    <!--게시글-->
    <div class="myFavoriteBoard-container">
      <h2>{{ user.nickname }}님의 찜한 게시글 목록</h2>
      <h3 v-if="favBoardList.length == 0">
        {{ user.nickname }}님이 찜한 게시글이 없습니다.
      </h3>
      <table v-else class="myFavoriteBoard-table">
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
          </tr>
        </thead>
        <!--key값.....:key뭘로잡아....-->
        <tbody
          v-for="favboard in favBoardList"
          :key="favboard.favorite_boardId"
        >
          <tr>
            <td>{{ favboard.favorite_boardId }}</td>
            <!-- <td>{{ favboard.title }}</td> -->
            <td>
              <RouterLink
                :to="{
                  name: 'boardDetail',
                  params: {
                    community_id: favboard.community_id,
                    board_id: favboard.board_id,
                  },
                }"
                >{{ favboard.title }}</RouterLink
              >
            </td>
            <td>{{ favboard.writername }}</td>
            <td>{{ favboard.reg_date }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { onMounted, computed, ref, toRaw } from "vue";
// import { useMyPageStore } from "@/stores/myPageStore.js";
import { useUserStore } from "@/stores/userStore.js";
import { useBoardStore } from "@/stores/boardStore.js";
import { useFavStore } from "@/stores/favStore.js";
import { useRoute } from "vue-router";

// route, store
const route = useRoute();
const userStore = useUserStore();
// const store = useMyPageStore();
const favStore = useFavStore();
const boardStore = useBoardStore();

// 유저 정보 받아오기
const user = computed(() => userStore.user);

// 유저 이메일 받아오기
const emailParam = route.params.email;

//내가 찜한 장소 불러오기
const favLocationList = computed(() => {
  return favStore.favLocationList;
});

//내가 찜한 게시글 불러오기
const favBoardList = computed(() => {
  return favStore.favBoardList;
});

// 카카오 지도에 출력하기 위한 좌표값들 저장
const markerPosition = ref([]);

// 카카오 지도에 출력하기 위한 마커들
const markerList = ref([]);

onMounted(async () => {
  await userStore.getUserByEmail(emailParam);
  await favStore.getFavLocationList(user.value.nickname);
  await favStore.getFavBoardList(user.value.nickname);

  markerPosition.value = favLocationList.value.map((location) => [
    location.latitude,
    location.longitude,
  ]);

  console.log(markerPosition.value);

  console.log(favLocationList.value);
  console.log(favBoardList.value);

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
});

///////////////////////////// 밑으로 지도
let map = null;
let infowindow = null;
const initMap = () => {
  let mapContainer = document.getElementById("map"); // 지도를 표시할 div
  let mapOption = {
    // center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
    center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
  };
  map = new kakao.maps.Map(mapContainer, mapOption);

  infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
  // displayMarker(location.value);

  // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
  const mapTypeControl = new kakao.maps.MapTypeControl();

  // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

  displayMarker(markerPosition.value);
};

// 마커 표시 함수
const displayMarker = (markers) => {
  // 마커들의 포지션을 가져간다
  const positions = markers.map(
    (position) => new kakao.maps.LatLng(...position)
  );

  if (positions.length > 0) {
    markerList.value = positions.map(
      (position) =>
        new kakao.maps.Marker({
          map: toRaw(map),
          position,
        })
    );
  }

  const bounds = positions.reduce(
    (bounds, latlng) => bounds.extend(latlng),
    new kakao.maps.LatLngBounds()
  );

  toRaw(map).setBounds(bounds);
  // let marker = new kakao.maps.Marker({
  //   map: map,
  //   position: new kakao.maps.LatLng(place.latitude, place.longitude),
  // });
  // infowindow.setContent(
  //   `<div class="map-info" style="padding:3px;font-size:12px;width:100%;display:flex">
  //         <div>
  //           <div style="font-weight: bold; font-size: 14px">${place.place_name}</div>
  //           <div style="width:160px">${place.address_name}</div>
  //         </div>
  //       </div>`
  // );
  // infowindow.open(map, marker);
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
  // // 장소 정보 가져오기
  // await locationStore.doGetLocation(props.location);
  // // 유저 로그인 체크
  // userStore.doLoginCheck();
  // //로그인 상태일 때
  // if (isLogin.value) {
  //   await userStore.getUserEmail(); // 토큰 가져오기
  //   await userStore.getUserByEmail(loginUser.value.email); // 정보 가져오기
  //   await favStore.doFavLocationCheck(
  //     user.value.nickname,
  //     location.value.location_id
  //   ); // 이미 찜한 장소인지 체크하기
  //   console.log(isFavoredLocation.value);
  // }
});
</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
}
.myFavorite-global {
  /* display: grid; */
  /* grid-template-rows: 1fr 1fr; */
  /* row-gap: 100px; */
  display: flex;
  margin-top: 150px;
  gap: 20px;
}

.myFavLocation-container,
.myFavoriteBoard-container {
  display: flex;
  flex-direction: column;
  /* justify-content: center; */
  align-items: center;
  border: 2px solid black;
  border-radius: 20px;
  height: 40rem;
  width: 100%;
}

.myFavoriteBoard-table {
  color: black;
  font-size: 1.3rem;
  font-weight: 500;
  text-align: center;
  border-spacing: 1.3rem;
}

#map {
  width: 100%;
  height: 100%;
}

.myFavLocation-map {
  width: 35rem;
  height: 20rem;
  border: 2px solid #ccc;
  border-radius: 20px;
  overflow: hidden;
  margin: 1rem 2rem;
}

.myFavLocation-list-box {
  width: 35rem;
  height: 13.5rem;
  border: 2px solid #ccc;
  border-radius: 20px;
}
</style>
