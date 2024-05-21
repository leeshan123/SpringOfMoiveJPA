package kr.co.moviespring.web.controller.api;

import kr.co.moviespring.web.config.security.CustomUserDetails;
import kr.co.moviespring.web.service.CommunityBoardCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController("ApiCommunityBoardController")
@RequestMapping("api/community-board")
public class CommunityBoardController {

    @Autowired
    private CommunityBoardCommentsService commentsService;

    // 댓글 수정 요청
    @PutMapping("comments/{commentId}")
    public int boardLike(@PathVariable Long commentId,@RequestBody String comment,
                         @AuthenticationPrincipal CustomUserDetails userDetails) {
//        @RequestBody LikeRequest LikeRequest
        if (userDetails == null)
            return 100; //로그인하지 않은 유저가 좋아요 누를시 100 전송

        Long memberId = userDetails.getId();
        int result = commentsService.editById(commentId, memberId, comment);

        return result;
    }

    // 댓글 삭제 요청
    @DeleteMapping("comments/{commentId}")
    public int boardDisLike(@PathVariable Long commentId,
                            @AuthenticationPrincipal CustomUserDetails userDetails) {
//        @RequestBody LikeRequest LikeRequest
        if (userDetails == null)
            return 100; //로그인하지 않은 유저가 좋아요 누를시 100 전송

//        Long memberId = userDetails.getId();
        int result = commentsService.deleteById(commentId);

        return result;
    }

}