import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

const REST_MYPAGE_API = 'http://localhost:1004/mypage';

export const useMyPageStore = defineStore('myPage', () => {
    const myPageUser = ref('')

 //내가 쓴 글 목록
const boards = ref([])
 //내가 쓴 댓글 목록

 //내가 찜한 장소

 //내가 팔로우 한 사람

 //개인정보 변경 -> userStore
  return { }
})
