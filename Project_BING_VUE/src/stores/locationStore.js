import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";
import router from "@/router/index.js";

export const useLocationStore = defineStore("location", () => {
  const REST_LOCATION_API = "http://localhost:1004/location";

  // 장소 정보 가져오기
  const location = ref("");
  const doGetLocation = (location_id) => {
    axios.get(`${REST_LOCATION_API}/${location_id}`).then((response) => {
      location.value = response.data;
    });
  };

  // 장소 정보 등록하기
  const doPostLocation = (location) => {
    axios
      .post(`${REST_LOCATION_API}/regist`, location, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then(() => {})
      .catch((err) => console.log(err));
  };

  return {
    location,
    doGetLocation,
    doPostLocation,
  };
});
