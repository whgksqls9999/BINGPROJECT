import { createRouter, createWebHistory } from "vue-router";
import MainView from "../views/MainView.vue";
import CommunityView from '@/views/CommunityView.vue';
import BoardView from '@/views/BoardView.vue';
import MyPageView from '@/views/MyPageView.vue';
import BoardDetail from '@/components/board/BoardDetail.vue';
import MyBoards from '@/components/myPage/MyBoards.vue';
import MyFavor from '@/components/myPage/MyFavor.vue';
import MyInfo from '@/components/myPage/MyInfo.vue';
import MyReplys from '@/components/myPage/MyReplys.vue';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: MainView,
    },
    {
      path: "/community/:community_id",
      name: "community",
      component: CommunityView,
    },
    {
      path: "/board",
      name: "board",
      component: BoardView,
      children: [
        {
          path: "/:id",
          name: "boardDetail",
          component: BoardDetail,
        },
      ]
    },
    {
      path: "/board/write",
      name: "boardWrite",
      component: BoardWrite,
    },
    {
      path: "/:id",
      name: "myPage",
      component: MyPageView,
      children :[
        {
          path: "/info",
          name: "myInfo",
          component: MyInfo,
        },
        {
          path: "/favor",
          name: "myFavor",
          component: MyFavor,
        },
        {
          path: "/boards",
          name: "myBoards",
          component: MyBoards,
        },
        {
          path: "/boards",
          name: "myReplys",
          component: MyReplys,
        },
      ]
    }   
  ],
});

export default router;