import { createRouter, createWebHistory } from "vue-router";
import MainView from "../views/MainView.vue";
<<<<<<< HEAD
import CommunityView from '@/views/CommunityView.vue';
import BoardView from '@/views/BoardView.vue';
import MyPageView from '@/views/MyPageView.vue';
import BoardDetail from '@/components/board/BoardDetail.vue';
import MyBoards from '@/components/myPage/MyBoards.vue';
import MyFavorite from '@/components/myPage/MyFavorite.vue';
import MyInfo from '@/components/myPage/MyInfo.vue';

=======
import CommunityView from "@/views/CommunityView.vue";
import BoardView from "@/views/BoardView.vue";
import MyPageView from "@/views/MyPageView.vue";
import BoardDetail from "@/components/board/BoardDetail.vue";
import MyFavor from "@/components/myPage/MyFavorLocation.vue";
import MyInfo from "@/components/myPage/MyInfo.vue";
import MyReplys from "@/components/myPage/MyReplys.vue";
import BoardWrite from "@/views/BoardWriteView.vue";
>>>>>>> 727b189833352bdaaf64ad5bf3e0aea0ffce5b0c

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
      path: "/board/:board_id",
      name: "board",
      component: BoardView,
      children: [
        {
          path: "/:id",
          name: "boardDetail",
          component: BoardDetail,
        },
      ],
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
      children: [
        {
          path: "/info",
          name: "myInfo",
          component: MyInfo,
        },
        {
          path: "/favorite",
          name: "myFavorite",
          component: MyFavorite,
        },
<<<<<<< HEAD
        
        {
          path: "/boards",
          name: "myBoards",
          component: MyBoards,
        },
        
      ]
    }   
=======
        // {
        //   path: "/boards",
        //   name: "myBoards",
        //   component: MyBoards,
        // },
        {
          path: "/boards",
          name: "myReplys",
          component: MyReplys,
        },
      ],
    },
>>>>>>> 727b189833352bdaaf64ad5bf3e0aea0ffce5b0c
  ],
});

export default router;
