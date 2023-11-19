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
          가입시 작성했던 탈퇴 문구를 입력해주세요
        </button>
        <div class="withdraw-submit">
          <div class="withdraw-block-text" v-if="currentStep === 4">
            <label for="withdraw-text"></label>
            <input type="text" name="withdraw-text" id="withdraw-text" />
          </div>
          <div>
            <button v-if="currentStep === 4" class="withdraw-button">
              확인
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
  font-size: 40px;
  font-weight: bold;
}
.last {
  font-size: 30px;
}
.myInfo-withdraw-container {
  margin-top: 150px;
}

input {
  /* border: 1px solid black; */
  background-color: rgba(200, 209, 209, 0.9);
  text-align: center;
  height: 50px;
  width: 400px;
  border-radius: 30px;
  color: white;
  font-weight: bold;
  font-size: 20px;
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
  background-color: rgba(200, 209, 209, 0.7);
  width: 100px;
  border-radius: 30px;
  font-size: 30px;
  font-weight: 300;
}

.withdraw-button:hover {
  background-color: rgb(90, 140, 140);
  color: aliceblue;
}
</style>
