package kr.co.moviespring.web.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.moviespring.web.entity.CommunityBoardCommentsView;
import kr.co.moviespring.web.entity.CommunityBoardView;
import kr.co.moviespring.web.entity.OnelineReviewMovieView;
import kr.co.moviespring.web.service.CommunityBoardCommentsService;
import kr.co.moviespring.web.service.MemberService;
import kr.co.moviespring.web.service.OnelineReviewService;

@RestController("ApiMemberController")
@RequestMapping("api/member")
public class MemberController {
    
    @Autowired
    MemberService memberService;

    @Autowired
    CommunityBoardCommentsService cbcService;

    @Autowired
    OnelineReviewService orService;

    

    @GetMapping("board-list")
    public List<CommunityBoardView> boardList(
        @RequestParam Long id
    ) {
        List<CommunityBoardView> list = memberService.getMyBoard(id);
        return list;
    }


    @GetMapping("comment-list")
    public List<CommunityBoardCommentsView> commentList(
        @RequestParam Long id
    ) {
        List<CommunityBoardCommentsView> list = cbcService.getListByMemberId(id);
        return list;
    }

    
    @GetMapping("review-list")
    public List<OnelineReviewMovieView> reviewList(
        @RequestParam Long id
    ) {
        
        List<OnelineReviewMovieView> list = orService.getListByMemberId(id);
        return list;
    }


    @GetMapping("check-username")
    public Map<String, Boolean> checkUsername(
        @RequestParam String name
    ){
        boolean isDuplicate = memberService.getByUsername(name);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return response;
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> delete(
        @RequestBody Long id
        ,HttpServletRequest request
        ,HttpServletResponse response
    ){
        // 일단 업데이트 문으로, 나중에 구현
        // status 속성을 추가해서 active | inactive로 바꾸자
        // or active 속성을 추가해서 boolean으로 받아서 하던가
        memberService.removeById(id);

        // 로그아웃 처리
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        
        return ResponseEntity.ok("성공적으로 삭제되었습니다.");
    }

    // 회원 제제
    @PostMapping("ban")
    public ResponseEntity<String> ban(
        @RequestBody Map<String, Long> requestBody
    ) {

        Long id = requestBody.get("id");

        memberService.banById(id);

        return ResponseEntity.ok("회원을 제제하였습니다.");
    }

    
    // 회원 복구
    @PostMapping("restore")
    public ResponseEntity<String> restore(
        @RequestBody String userId
    ){
        Long memberId = Long.parseLong(userId);
        memberService.restoreById(memberId);
        return ResponseEntity.ok("성공적으로 복구되었습니다.");
    }
}
