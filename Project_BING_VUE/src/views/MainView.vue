<template>
  <div class="body">
    <h2>Section One Text Block Reveal</h2>
    <h3>Text</h3>
    <div class="p1">
      <div class="p1-e1">해빙</div>
      <div class="p1-e2"></div>
      <div class="p1-e3">解氷</div>
    </div>
  </div>
</template>

<script setup>
import { useCommonStore } from "@/stores/commonStore.js";
import { ref, onMounted } from "vue";
import gsap from "gsap";
import ScrollTrigger from "gsap/ScrollTrigger";
gsap.registerPlugin(ScrollTrigger);



onMounted(() => {
  gsap.to(".a1,.a2", {
    scrollTrigger: {
      trigger: ".a1",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 2,
      opacity: 0,
      markers: true,
    },
    opacity: 1,
    x: 20,
  });
});
// let options = {
//   root: document.querySelector('#scrollArea'),
//   rootMargin: '0px',
//   threshold: 1.0
// }

// let observer = new IntersectionObserver(() => {
//   console.log('detected');
// }, options)

// let target = document.querySelector('.first');
// observer.observe(target);
// console.log(observer);
// observer.observe(target_1);
// const isVisible = ref(false);
// observer.stop

window.addEventListener('scroll', function () {
  console.log(window.scrollY)
});

// # init
// 헤더 fixed toggle
const commonStore = useCommonStore();

onMounted(() => {
  commonStore.toggleHeaderFixed(true);
  commonStore.toggleFooterFixed(false);
});

// onBeforeRouteLeave(() => {
//   commonStore.toggleHeaderFixed(false);
// });

// ## init

</script>

<style scoped>
.body {
  height: 5000px;
  background: linear-gradient(-7deg, rgb(180, 202, 221), rgb(0, 0, 0));
}

p1{
  position: absolute;
  color: white;
  font-size: 40px;
  top: 1000px;

}
</style>
