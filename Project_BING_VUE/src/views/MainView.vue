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
      <div class="p2-e1">얼음이 녹아 풀리는 것</div>
    </div>
    <div class="p3"></div>
    <div class="p4">
      <div class="p4-e1">
        <span>얼어있는 바닷물의 한 형태</span>
      </div>
    </div>
    <div class="p5"></div>
    <div class="p6"></div>
    <div class="p7">
      <div class="p71"></div>
      <div class="p72"></div>
      <div class="p73"></div>
      <div class="p74"></div>
    </div>
    <div class="p75">CONNECT WITH US</div>
    <div class="p76"></div>
    <div class="p8">
      <div class="p8e1">
        <font-awesome-icon :icon="['fas', 'fish']" style="color: #ffffff" />
      </div>
      <div class="p8e2">
        <font-awesome-icon
          :icon="['fas', 'fish-fins']"
          style="color: #ffffff"
        />
      </div>
      <div class="p8e3">
        <font-awesome-icon
          :icon="['fas', 'fish-fins']"
          style="color: #ffffff"
        />
      </div>
      <div class="p8e4">
        <font-awesome-icon :icon="['fas', 'fish']" style="color: #ffffff" />
      </div>
    </div>
    <div class="p77">HABING</div>
    <div class="end"></div>
  </div>
</template>

<script setup>
import { useCommonStore } from "@/stores/commonStore.js";
import { ref, onMounted } from "vue";
import gsap from "gsap";
import ScrollTrigger from "gsap/ScrollTrigger";
gsap.registerPlugin(ScrollTrigger);
onMounted(() => {
  window.addEventListener("click", (e) => {
    console.log(e.offsetY);
  });

  gsap.to(".p1-e1", {
    scrollTrigger: {
      trigger: ".p1-e1",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 5,
      opacity: 0,
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
      width: 400,
    },
    opacity: 1,
    width: 900,
    x: 20,
  });
  gsap.to(".p1-e3", {
    scrollTrigger: {
      trigger: ".p1-e4",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 6,
      opacity: 0,
      width: 400,
    },
    opacity: 1,
    x: -2,
  });
  gsap.to(".p2-e1", {
    scrollTrigger: {
      trigger: ".p2",
      toggleActions: "restart none reverse none",
      start: "100% 60%",
      end: "0% 20%",
      scrub: 1,
    },
    opacity: 1,
  });
  gsap.to(".p2-e1", {
    scrollTrigger: {
      trigger: ".p3",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 0,
    },
    y: -50,
  });
  gsap.to(".p4-e1", {
    scrollTrigger: {
      trigger: ".p5",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 0,
    },
    y: -50,
    opacity: 1,
  });
  gsap.to(".p2, .p3, .p4", {
    scrollTrigger: {
      trigger: ".p6",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 3,
    },
    opacity: 0,
  });
  gsap.to(".p8e1", {
    scrollTrigger: {
      trigger: ".p72",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 3,
    },
    opacity: 1,
  });
  gsap.to(".p8e2", {
    scrollTrigger: {
      trigger: ".p73",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 5,
    },
    opacity: 1,
  });
  gsap.to(".p8e3", {
    scrollTrigger: {
      trigger: ".p72",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 1,
    },
    opacity: 1,
  });
  gsap.to(".p8e4", {
    scrollTrigger: {
      trigger: ".p71",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 8,
    },
    opacity: 1,
  });
  gsap.to(".p75", {
    scrollTrigger: {
      trigger: ".p74",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 1,
    },
    y: 10,
    opacity: 1,
  });
  gsap.to(".p1, .p2, .p3", {
    scrollTrigger: {
      trigger: ".p74",
      toggleActions: "restart none reverse none",
      start: "100% 80%",
      end: "0% 20%",
      scrub: 1,
    },
    opacity: 0,
  });
  gsap.to(".p75", {
    scrollTrigger: {
      trigger: ".p76",
      toggleActions: "restart none reverse none",
      start: "100% 60%",
      end: "0% 20%",
      scrub: 3,
    },
    opacity: 0,
  });
  gsap.to(".p77", {
    scrollTrigger: {
      trigger: ".p76",
      toggleActions: "restart none reverse none",
      start: "100% 30%",
      end: "0% 20%",
      scrub: 3,
    },
    opacity: 1,
  });
  gsap.to(".p8", {
    scrollTrigger: {
      trigger: ".p76",
      toggleActions: "restart none reverse none",
      start: "100% 30%",
      end: "0% 20%",
      scrub: 3,
    },
    opacity: 1,
  });
  gsap.to(".p77, .p8", {
    scrollTrigger: {
      trigger: ".end",
      toggleActions: "restart none reverse none",
      start: "100% 30%",
      end: "0% 20%",
      scrub: 6,
    },
    opacity: 0,
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
* {
  margin: 0;
  padding: 0;
}
.body {
  height: 11300px;
  background: linear-gradient(-7deg, rgb(180, 202, 221), rgb(0, 0, 0));
}
.p1 {
  position: sticky;
  color: white;
  font-weight: bold;
  margin-top: 800px;
  top: 50px;
  margin-left: 100px;
}
.p1-e1 {
  font-size: 200px;
  opacity: 0;
}
.p1-e2 {
  background-color: white;
  height: 10px;
  width: 200px;
  margin-top: -31px;
  opacity: 0;
}
.p1-e3 {
  font-size: 60px;
  margin-left: 760px;
  opacity: 0;
}
.p1-e4 {
}
.p2 {
  color: white;
  position: sticky;
  top: 450px;
  margin-left: 1200px;
  margin-top: 800px;
  overflow: hidden;
}
.p2-e1 {
  font-size: 40px;
  opacity: 0;
}
.p3 {
  margin-top: 900px;
}
.p4 {
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
.p4-e1 {
  position: fixed;
  left: 1200;
  top: 450;
  opacity: 1;
  height: 100px;
  width: 100%;
  opacity: 0;
  font-size: 40px;
  color: white;
}
.p5 {
  /* position: absolute;
  background-color: skyblue;
  width: 100%;
  top: 5000px; */
}
.p6 {
  position: absolute;
  top: 4700px;
  left: 0;
  /* background-color: skyblue; */
  height: 10rem;
  width: 100%;
}

.p7 {
  position: absolute;
  top: 4900px;
  left: 0;
  background-color: orange;
  height: 10rem;
  widows: 100%;
}
.p8 {
  position: sticky;
  top: 200px;
  opacity: 0;
  /* margin-top: 5000px; */
}
.p8e1 {
  margin-left: 500px;
  font-size: 50px;
  opacity: 0;
}
.p8e2 {
  opacity: 0;
  margin-left: 510px;
}
.p8e3 {
  margin-left: 550px;
  font-size: 30px;
  opacity: 0;
}
.p8e4 {
  margin-left: 490px;
  font-size: 25px;
  opacity: 0;
}
.p71 {
  height: 500px;
  /* background-color: blue; */
}
.p72 {
  height: 500px;
  /* background-color: skyblue; */
}
.p73 {
  height: 500px;
  /* background-color: orange; */
}
.p74 {
  /* background-color: red; */
  height: 1000px;
}
.p75 {
  position: sticky;
  font-size: 100px;
  font-weight: bold;
  color: white;
  top: 300;
  margin-left: 30%;
  opacity: 0;
}
.p76 {
  height: 4900px;
  /* background-color: skyblue; */
  width: 100%;
}
.p77 {
  height: 1000px;
  position: sticky;
  font-size: 100px;
  font-weight: bold;
  color: white;
  top: 300;
  margin-left: 30%;
  opacity: 0;
}
.end {
  position: absolute;
  top: 10000px;
  width: 100%;
  height: 30px;
  /* background-color: black; */
}
</style>
