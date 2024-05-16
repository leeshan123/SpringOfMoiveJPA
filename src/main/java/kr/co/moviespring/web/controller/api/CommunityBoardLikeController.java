//package kr.co.moviespring.web.controller.api;
//
//import kr.co.moviespring.web.service.CommunityBoardLikeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("api/community-board-likes")
//public class CommunityBoardLikeController {
//
//    @Autowired
//    private CommunityBoardLikeService service;
//
//    @PostMapping
//    public int like(
//            @RequestBody MenuLike menuLike) {
//        MenuLike newOne = service.add(menuLike);
//        return newOne;
//    }
//
//    @PostMapping
//    public int dislike(
//            @RequestBody MenuLike menuLike) {
//        MenuLike newOne = service.add(menuLike);
//        return newOne;
//    }
//
////        @DeleteMapping
////        public String delete(Long id){
////            // service.cancel()
////            return null;
////        }
//
//}
//
//}
