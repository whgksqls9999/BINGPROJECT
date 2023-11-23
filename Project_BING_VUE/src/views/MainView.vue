<template>
  <div class="body">
    <h2>Section One Text Block Reveal</h2>
    <h3>Text</h3>
    <div class="p1">
      <div class="p1-e1">해빙</div>
      <div class="p1-e2"></div>
      <div class="p1-e3">解氷</div>
      <div class="p1-e4"></div>
      <!-- 얼음이 녹아 풀리는 것 -->
      <!--  -->
    </div>
    <div class="p2">
      <div class="p2-e2"></div>
      <div class="p2-e1">
        얼음이 녹아 풀리는 것
      </div>
    </div>
    <div class="p3"></div>
    <div class="p4">
      <div class="p4-e1">
        <span>asdf</span>
      </div>
    </div>
    <div class="p5"></div>
  </div>
</template>

<script setup>
import { useCommonStore } from "@/stores/commonStore.js";
import { ref, onMounted } from "vue";
import gsap from "gsap";
import ScrollTrigger from "gsap/ScrollTrigger";
gsap.registerPlugin(ScrollTrigger);



onMounted(() => {
  window.addEventListener('click', (e) => {
    console.log(e.offsetY);
  })
  
  gsap.to(".p1-e1", {
    scrollTrigger: {
      trigger: ".p1-e1",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 5,
      opacity: 0,
      markers: true,
    },
    opacity: 1,
    x: 20,
  });
  gsap.to(".p1-e2", {
    scrollTrigger: {
      trigger: ".p1-e1",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 3,
      opacity: 0,
      markers: true,
      width:400
    },
    opacity: 1,
    width: 900,
    x:20
  });
  gsap.to(".p1-e3", {
    scrollTrigger: {
      trigger: ".p1-e4",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 6,
      opacity: 0,
      markers: true,
      width:400
    },
    opacity: 1,
    x:-1
  });
  gsap.to(".p2-e1", {
    scrollTrigger: {
      trigger: ".p2",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 1,
      markers: true,
    },
    opacity: 1,
    x: -3
  });
  gsap.to(".p2-e1", {
    scrollTrigger: {
      trigger: ".p3",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 0,
      markers: true,
    },
    y: -50
  });
  gsap.to(".p4-e1", {
    scrollTrigger: {
      trigger: ".p5",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 0,
      markers: true,
    },
    y: -50
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
*{
  margin: 0;
  padding: 0;
}
.body {
  height: 10000px;
  background: linear-gradient(-7deg, rgb(180, 202, 221), rgb(0, 0, 0));
}

.p1{
  position: sticky;
  color: white;
  font-weight: bold;
  margin-top: 800px;
  top: 50px;
  margin-left: 100px;
}
.p1-e1{
  font-size: 200px;
  opacity: 0;
}
.p1-e2{
  background-color: white;
  height: 10px;
  width: 200px;
  margin-top: -31px;
  opacity: 0;
}
.p1-e3{
  font-size: 60px;
  margin-left: 760px;
  opacity: 0;
}
.p1-e4{

}
.p2{
  color: white;
  position: sticky;
  top: 450px;
  margin-left: 1200px;
  margin-top: 800px;
  overflow: hidden;
}

.p2-e1{
  font-size: 40px;
  opacity: 0;
}

.p3{
 margin-top: 900px;
}
.p4{
  position: fixed;
  position: relative;
  left: 1200;
  top: 400;
  opacity: 1;
  height: 50px;
  width: 100%;
  opacity: 1;
  z-index: 1;
}
.p4-e1{
  position: fixed;
  left: 1200;
  top: 450;
  opacity: 1;
  height: 100px;
  width: 100%;
  opacity: 1;
  font-size: 40px;
  color: white;
}
</style>
