import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";
import router from "@/router/index.js";

export const useLocationStore = defineStore("location", () => {
  const REST_LOCATION_API = "http://localhost:1004/location";

  // 장소 정보 가져오기
  const location = ref("");
  const doGetLocation = async (location_id) => {
    await axios.get(`${REST_LOCATION_API}/${location_id}`).then((response) => {
      location.value = response.data;
    });
  };

  // 장소 정보 등록하기
  const doPostLocation = (location) => {
    let newLocation = {
      location_id: location.id,
      place_name: location.place_name,
      address_name: location.address_name,
      longitude: location.x,
      latitude: location.y,
    };
    axios
      .post(`${REST_LOCATION_API}/regist`, newLocation, {
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
