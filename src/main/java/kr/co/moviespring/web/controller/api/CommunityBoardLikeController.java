package kr.co.moviespring.web.controller.api;

import kr.co.moviespring.web.config.security.CustomUserDetails;
import kr.co.moviespring.web.service.CommunityBoardLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController("ApiCommunityBoardLikeController")
@RequestMapping("api/community-board-likes")
public class CommunityBoardLikeController {

    @Autowired
    private CommunityBoardLikeService service;

    // 게시글 좋아요/싫어요 요청
    @PostMapping("board/{boardId}/like")
    public int boardLike(@PathVariable Long boardId,
                    @AuthenticationPrincipal CustomUserDetails userDetails) {
//        @RequestBody LikeRequest LikeRequest
        if (userDetails == null)
            return 100; //로그인하지 않은 유저가 좋아요 누를시 100 전송

        Long memberId = userDetails.getId();
        int status = service.addLikeDisLike(boardId, memberId, 1, "board"); //좋아요는 status값으로 1을 전송하고 1을 리턴 받음

        return status;
    }

    @PostMapping("board/{boardId}/dislike")
    public int boardDisLike(@PathVariable Long boardId,
                       @AuthenticationPrincipal CustomUserDetails userDetails) {
//        @RequestBody LikeRequest LikeRequest
        if (userDetails == null)
            return 100; //로그인하지 않은 유저가 좋아요 누를시 100 전송

        Long memberId = userDetails.getId();
        int status = service.addLikeDisLike(boardId, memberId, -1, "board"); //싫어요는 status값으로 -1을 전송하고 -1을 리턴 받음

        return status;
    }

    // 댓글 좋아요/싫어요 요청
    @PostMapping("comment/{commentId}/like")
    public int commentLike(@PathVariable Long commentId,
                    @AuthenticationPrincipal CustomUserDetails userDetails) {
//        @RequestBody LikeRequest LikeRequest
        if (userDetails == null)
            return 100; //로그인하지 않은 유저가 좋아요 누를시 100 전송

        Long memberId = userDetails.getId();
        int status = service.addLikeDisLike(commentId, memberId, 1, "comment"); //좋아요는 status값으로 1을 전송하고 1을 리턴 받음

        return status;
    }

    @PostMapping("comment/{commentId}/dislike")
    public int commentDisLike(@PathVariable Long commentId,
                       @AuthenticationPrincipal CustomUserDetails userDetails) {
//        @RequestBody LikeRequest LikeRequest
        if (userDetails == null)
            return 100; //로그인하지 않은 유저가 좋아요 누를시 100 전송

        Long memberId = userDetails.getId();
        int status = service.addLikeDisLike(commentId, memberId, -1, "comment"); //싫어요는 status값으로 -1을 전송하고 -1을 리턴 받음

        return status;
    }

}