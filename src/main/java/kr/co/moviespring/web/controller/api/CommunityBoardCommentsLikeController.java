//package kr.co.moviespring.web.controller.api;
//
//import kr.co.moviespring.web.service.CommunityBoardLikeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("api/community-board-comments-likes")
//public class CommunityBoardCommentsLikeController {
//
//        @Autowired
//        private CommunityBoardCommentsLikeService service;
//
//        @PostMapping
//        public MenuLike add(
//                @RequestBody MenuLike menuLike){
//            MenuLike newOne = service.add(menuLike);
//            return newOne;
//        }
//
//        @DeleteMapping
//        public String delete(Long id){
//            // service.cancel()
//            return null;
//        }
//
//    }
//
//}
