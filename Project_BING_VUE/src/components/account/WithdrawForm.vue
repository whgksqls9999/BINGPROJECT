<template>
  <div>
    <div class="myInfo-withdraw-container">
      <button @click="handleButtonClick(1)" v-if="currentStep === 1">
        정말 탈퇴하시나요😥
      </button>
      <button @click="handleButtonClick(2)" v-if="currentStep === 2">
        다시 한번만 더 생각해보세요😨
      </button>
      <button @click="handleButtonClick(3)" v-if="currentStep === 3">
        마지막으로 생각해보세요😭
      </button>
      <div class="last-container">
        <button
          class="last"
          @click="handleButtonClick(4)"
          v-if="currentStep === 4"
        >
          다음에 다시 <span class="glow">"HABING"</span>을 찾아주세요!
        </button>
        <div class="withdraw-submit">
          <div class="withdraw-block-text" v-if="currentStep === 4">
            <label for="withdraw-text"></label>
            <input
              type="text"
              name="withdraw-text"
              id="withdraw-text"
              placeholder="가입시 작성했던 탈퇴 문구를 입력해주세요"
            />
          </div>
          <div>
            <button v-if="currentStep === 4" class="withdraw-button">
              탈퇴
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const currentStep = ref(1);

const handleButtonClick = (step) => {
  console.log(`Button clicked for step ${step}`);
  if (step < 4) {
    currentStep.value = step + 1;
  }
};
</script>

<style scoped>
button {
  text-align: center;
  font-size: 30px;
  font-weight: bold;
  transition: background-color 0.3s ease, color 0.3s ease;
}
.last {
  font-size: 30px;
}
.myInfo-withdraw-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 150px;
  height: 50vh;
}
.glow {
  font-size: 33px;
  color: rgb(0, 0, 0);
  text-align: center;
  background: linear-gradient(
    0deg,
    rgba(149, 207, 247, 1) 43%,
    rgba(255, 255, 255, 1) 43%
  );
  /* background: radial-gradient(
    circle,
    rgba(0, 38, 83, 0.3) 0%,
    rgba(182, 238, 240, 0.3) 100%
  ); */
  /* padding: 0 5px; */
  /* border-radius: 60%; 옵셔널: 텍스트 주변에 둥근 모서리를 생성합니다. */
}

input {
  /* border: 1px solid black; */
  background-color: rgba(157, 150, 150, 0.3);
  text-align: center;
  height: 40px;
  width: 400px;
  border-radius: 10px;
  color: white;
  font-weight: bold;
  font-size: 20px;
}

input:focus {
  border: 2px solid black;
  outline: none;
}

input::placeholder {
  color: rgb(146, 151, 156);
}
.last-container {
  display: flex;
  flex-direction: column;
  gap: 30px;
  justify-content: center;
  align-items: center;
}
.withdraw-submit {
  display: flex;
  gap: 30px;
}
.withdraw-button {
  background-color: rgba(157, 150, 150, 0.3);
  width: 100px;
  height: 40px;
  border-radius: 25px;
  font-size: 23px;
  font-weight: 300;
  transition: background-color 0.3s ease, color 0.3s ease;
}

.withdraw-button:hover {
  background-color: rgb(216, 67, 67);
  color: aliceblue;
}
</style>
