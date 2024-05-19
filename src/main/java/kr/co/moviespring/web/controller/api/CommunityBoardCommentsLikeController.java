//package kr.co.moviespring.web.controller.api;
//
//import kr.co.moviespring.web.config.security.CustomUserDetails;
//import kr.co.moviespring.web.service.CommunityBoardLikeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//@RestController("ApiCommunityBoardCommentsLikeController")
//@RequestMapping("api/community-board-comments-likes")
//public class CommunityBoardCommentsLikeController {
//
//    @Autowired
//    private CommunityBoardLikeService service;
//
//    @PostMapping("{commentId}/like")
//    public int like(@PathVariable Long commentId,
//                    @AuthenticationPrincipal CustomUserDetails userDetails) {
////        @RequestBody LikeRequest LikeRequest
//        if (userDetails == null)
//            return 100; //로그인하지 않은 유저가 좋아요 누를시 100 전송
//
//        Long userId = userDetails.getId();
//        int status = service.like(commentId, userId, 1); //좋아요는 status값으로 1을 전송하고 1을 리턴 받음
//
//        return status;
//    }
//
//    @PostMapping("{commentId}/dislike")
//    public int disLike(@PathVariable Long commentId,
//                       @AuthenticationPrincipal CustomUserDetails userDetails) {
////        @RequestBody LikeRequest LikeRequest
//        if (userDetails == null)
//            return 100; //로그인하지 않은 유저가 좋아요 누를시 100 전송
//
//        Long userId = userDetails.getId();
//        int status = service.disLike(commentId, userId, -1); //싫어요는 status값으로 -1을 전송하고 -1을 리턴 받음
//
//        return status;
//    }
//
//}
//
//
