<template>
  <div>
    <div id="map"></div>
    <!-- <button @click="initMap">내위치</button> -->
  </div>
  <div>
    <input type="text" v-model="keyword" />
    <button @click="search">검색</button>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";

let keyword = ref("");

const search = () => {
  // 장소 검색을 위한 객체 생성
  let ps = new kakao.maps.services.Places();
  // 키워드로 장소 검색
  ps.keywordSearch(keyword.value, placesSearchCB);
};

// 키워드 검색 함수
const placesSearchCB = (data, status, pagination) => {
  if (status === kakao.maps.services.Status.OK) {
    // LatLngBounds 객체에 좌표 추가
    // 검색 장소 위치 기준으로 지도 범위 재설정 위함
    let bounds = new kakao.maps.LatLngBounds();
    console.log(data);
    console.log(status);
    for (let i = 0; i < data.length; ++i) {
      displayMarker(data[i]);
      bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
    }

    //  검색 위치 기준으로 지도 범위 재설정
    map.setBounds(bounds);
  }
};

// 마커 표시 함수
const displayMarker = (place) => {
  let marker = new kakao.maps.Marker({
    map: map,
    position: new kakao.maps.LatLng(place.y, place.x),
  });

  // 마커에 클릭이벤트를 등록합니다
  kakao.maps.event.addListener(marker, "click", function () {
    // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
    infowindow.setContent(
      '<div style="padding:5px;font-size:12px;">' + place.place_name + "</div>"
    );
    infowindow.open(map, marker);
  });
};

let map = null;
const initMap = () => {
  let mapContainer = document.getElementById("map"); // 지도를 표시할 div
  let mapOption = {
    center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
  };
  map = new kakao.maps.Map(mapContainer, mapOption);

  let infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

  // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
  const mapTypeControl = new kakao.maps.MapTypeControl();

  // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
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
#map {
  width: 400px;
  height: 400px;
}
</style>
