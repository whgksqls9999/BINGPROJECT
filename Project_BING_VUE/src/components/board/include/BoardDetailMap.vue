<template>
  <div class="map-container">
    <div id="map"></div>
    <div class="board-detail-location-info"></div>
  </div>
</template>

<script setup>
import { onMounted, ref, computed, onBeforeMount } from "vue";
import { useLocationStore } from "@/stores/locationStore.js";
// router, store
const locationStore = useLocationStore();

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
  console.log(infowindow);
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
  await locationStore.doGetLocation(props.location);
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
}
</style>
