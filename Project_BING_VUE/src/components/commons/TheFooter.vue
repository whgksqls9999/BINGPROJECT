<template>
  <div class="footer-container">
    <div class="footer-copyright">
      <p>&copy; 2023, JO HANBIN, KIM EUNJI. All right reserved</p>
    </div>
    <div class="footer-link">
      <a href="https://blog.naver.com/skkkkkr_"
        ><img src="@/assets/SNS/네이버블로그.png"
      /></a>
      <a href="https://instagram.com/ha.bin_g"
        ><img src="@/assets/SNS/인스타그램1.png"
      /></a>
      <a href="https://jhb0412.tistory.com/"
        ><img src="@/assets/SNS/t스토리.png"
      /></a>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.footer-container {
  height: 70px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-end; /* 추가: 세로 가운데 정렬 */
  background-color: transparent;
  /* padding: 10px; */
}
.footer-copyright {
  color: white;
}
.footer-link {
  display: flex;
  gap: 2rem;
  justify-content: flex-end;
  margin-left: 10px; /* 수정: 여유 마진을 왼쪽에 추가 */
}
a {
  text-decoration: none;
}
img {
  width: 1.5rem;
  height: 1.5rem;
}
</style>
