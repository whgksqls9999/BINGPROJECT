import { createRouter, createWebHistory } from "vue-router";
import MainView from "@/views/MainView.vue";
import CommunityView from "@/views/CommunityView.vue";
import BoardView from "@/views/BoardView.vue";
import MyPageView from "@/views/MyPageView.vue";
import BoardWrite from "@/views/BoardWriteView.vue";
import BoardDetail from "@/components/board/BoardDetail.vue";
import MyBoards from "@/components/myPage/MyBoards.vue";
import MyFavorite from "@/components/myPage/MyFavorite.vue";
import MyInfo from "@/components/myPage/MyInfo.vue";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: MainView,
    },
    {
      path: "/community/",
      name: "community",
      component: CommunityView,
    },
    {
      path: "/board/:community_id",
      name: "board",
      component: BoardView,
      children: [
        {
          path: ":board_id",
          name: "boardDetail",
          component: BoardDetail,
        },
      ],
    },
    {
      path: "/board/:community_id/write",
      name: "boardWrite",
      component: BoardWrite,
      beforeEnter: (to, from) => {
        if (loginCheck()) return true;
        return false;
      },
    },
    {
      path: "/myPage/:nickname",
      name: "myPage",
      component: MyPageView,
      beforeEnter: (to, from) => {
        if (loginCheck()) return true;
        return false;
      },
      children: [
        {
          path: "info",
          name: "myInfo",
          component: MyInfo,
        },
        {
          path: "favorite",
          name: "myFavorite",
          component: MyFavorite,
        },
        {
          path: "boards",
          name: "myBoards",
          component: MyBoards,
        },
      ],
    },
  ],
});

function loginCheck() {
  let loginUser = sessionStorage.getItem("access-token");
  if (loginUser == null) {
    alert("로그인이 필요합니다.");
    return false;
  }
  return true;
}

export default router;
