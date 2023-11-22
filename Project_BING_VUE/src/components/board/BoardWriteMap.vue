<template>
  <div class="container" @click="doDetectClick">
    <div class="map">
      <div id="map"></div>
      <div class="map-form">
        <input
          type="text"
          placeholder="찾고자 하는 장소를 입력해주세요."
          v-model="keyword"
          @keyup.enter="search"
        />
        <button @click="search">검색</button>
        <button @click="doCloseWindow">닫기</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, computed } from "vue";

// 지도 창 닫기
// 1. 닫기 버튼 눌러서 닫기
const emit = defineEmits(["closeWindow", "selectPlace"]);
const doCloseWindow = () => {
  emit("closeWindow");
};
// 2. 바깥 공간 눌러서 닫기
const doDetectClick = (e) => {
  if (e.target.className === "container") {
    emit("closeWindow");
  }
};

// 선택된 장소 정보 저장하기
const location = ref("");
const shortAddress = ref("");
window.addEventListener("click", function (e) {
  if (e.target.className === "select-point") {
    // let place = JSON.parse(this.localStorage.getItem('place'));
    // location.value = place;
    doSelectPlace(location.value, shortAddress);
  }
});

const doSelectPlace = (location, shortAddress) => {
  emit("selectPlace", location, shortAddress);
};

// 지도 관련

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
    center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
  };
  map = new kakao.maps.Map(mapContainer, mapOption);

  infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

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
    position: new kakao.maps.LatLng(place.y, place.x),
  });
  // marker.id = 15217157;

  // 마커에 클릭이벤트를 등록합니다
  kakao.maps.event.addListener(marker, "click", function () {
    location.value = place;
    // localStorage.setItem('place',JSON.stringify(place));
    let address = place.address_name.split(" ").splice(0, 3).join(" ");
    shortAddress.value = address;
    // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
    infowindow.setContent(
      `<div class="map-info" style="padding:3px;font-size:12px;width:200px;display:flex">
        <div>
          <div style="font-weight: bold; font-size: 14px">${place.place_name}</div>
          <div style="width:160px">${address}</div>
        </div>
        <button class="select-point" style="width:50px; background-color:skyblue; color:white;">
          <div class="select-point">등록</div>
        </button>
      </div>`
    );
    infowindow.open(map, marker);
  });
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

onMounted(() => {
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
</script>

<style scoped>
.container {
  position: fixed;
  flex-direction: column;
  display: flex;
  top: 0;
  height: 100%;
  z-index: 1;
  background-color: rgba(128, 128, 128, 0.6);
}
.map {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: white;
  border-radius: 1rem;
  width: 30rem;
  height: 30rem;
  /* margin-top: -20rem; */
  box-shadow: 0 0 0.3rem grey;
}

#map {
  width: 400px;
  height: 400px;
  border-radius: 1rem;
  margin-top: 2rem;
  box-shadow: 0 0 0.2rem grey;
}

.map-form {
  width: 70%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem;
}

.map-form input {
  background-color: white;
  margin-bottom: 0.3rem;
  border: 2px solid black;
  border-radius: 0.4rem;
  padding: 3px;
  width: 15rem;
}

.map-form input:focus {
  box-shadow: 0 0 0.3rem black;
}

.map-form button {
  margin-top: 0.5rem;
  width: 3rem;
}
</style>
