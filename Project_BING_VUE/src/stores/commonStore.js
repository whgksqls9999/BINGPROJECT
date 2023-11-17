import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useCommonStore = defineStore('common', () => {

  // 헤더의 상태를 fixed - unfixed
  const isHeaderFixed = ref(true);
  const toggleHeaderFixed = (state) => {
    isHeaderFixed.value = state
  }

  return { isHeaderFixed,toggleHeaderFixed }
})
