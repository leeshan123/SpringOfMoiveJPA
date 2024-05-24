package kr.co.moviespring.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.moviespring.web.config.security.CustomUserDetailService;
import kr.co.moviespring.web.config.security.CustomUserDetails;
import kr.co.moviespring.web.entity.Category;
import kr.co.moviespring.web.entity.CommunityBoardCommentsView;
import kr.co.moviespring.web.entity.CommunityBoardView;
import kr.co.moviespring.web.entity.Member;
import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.OnelineReview;
import kr.co.moviespring.web.entity.OnelineReviewMovieView;
import kr.co.moviespring.web.service.CategoryService;
import kr.co.moviespring.web.service.CommunityBoardCommentsService;
import kr.co.moviespring.web.service.CommunityBoardService;
import kr.co.moviespring.web.service.MemberService;
import kr.co.moviespring.web.service.MovieService;
import kr.co.moviespring.web.service.OnelineReviewService;
import kr.co.moviespring.web.service.PlayGroundService;

@Controller
@RequestMapping("user")
public class MemberController {
    // 로그인 공사중//
    @Autowired
    MemberService memberService;

    @Autowired
    MovieService movieService;

    @Autowired
    CategoryService cService;

    @Autowired
    OnelineReviewService orService;

    @Autowired
    CommunityBoardCommentsService cbcService;

    @Autowired
    CommunityBoardService cbService;

    @Autowired
    PlayGroundService pgService;

    @Autowired
    private CustomUserDetailService customUserDetailsService;

    @GetMapping("mypage")
    public String main(
        Model model
        , @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        Long memberId = userDetails.getId();
        
        int orCnt = orService.getCount(memberId); // 평가한 영화
        int pgCnt = pgService.getCount(memberId); // 배팅한 영화
        int cbCnt = cbService.getCount(memberId); // 게시물
        int cbcCnt = cbcService.getCount(memberId); // 댓글

        model.addAttribute("orCnt", orCnt);
        model.addAttribute("pgCnt", pgCnt);
        model.addAttribute("cbCnt", cbCnt);
        model.addAttribute("cbcCnt", cbcCnt);
        model.addAttribute("point", userDetails.getPoint());
        return "user/mypage";
    }

    @GetMapping("signin")
    public String signin() {

        return "user/signin";
    }

    @GetMapping("findid")
    public String findId() {

        return "user/findid";
    }

    @GetMapping("findid-Result")
    public String findIdResult() {

        return "user/findid-result";
    }


    @GetMapping("findpwd")
    public String findpwd() {

        return "user/findpwd";
    }

    @GetMapping("change-pwd")
    public String changePwd() {

        return "user/change-pwd";
    }

    @GetMapping("change-pwd-result")
    public String changePwdResult() {

        return "user/change-pwd-result";
    }


    //스프링 사큐리티 설정파일의 .loginProcessingUrl("/user/signin")으로 로그인 폼데이터를 POST요청받음
    /*@PostMapping("signin")
    public String signin(String username, String password, HttpSession session, HttpServletResponse response) {
        System.out.println("post요청");
//        boolean valid = memberService.validate(username, password);
        System.out.println("포스트 요청");
//        if (!valid) {
//            return "redirect:signin?error";
//        }

        // Cookie uidCookie = new Cookie("uid","1");
        // uidCookie.setPath("/");
        // // uidCookie.setMaxAge(0);
        // // uidCookie.setSecure(false);
        // uidCookie.setHttpOnly(true);
        //
        Cookie usernameCookie = new Cookie("username",username);
        usernameCookie.setPath("/");
        
        // response.addCookie(uidCookie);
        response.addCookie(usernameCookie);

        return "redirect:/index";
    }*/

    // 회원가입 공사중//
    @GetMapping("signup")
    public String signup() {

        return "user/signup";
    }

    @PostMapping("signup")
    public String signup(Member member) {
//        String userId, String pwd, String name, String nickname, int age, String email
        memberService.regist(member);
//        username, password, name, nickname, age, email
        return "redirect:/user/welcome";
    }

    @GetMapping("welcome")
    public String welcome() {

        return "user/welcome";
    }
    
    @GetMapping("mymovie")
    public String mymovie(
        Model model
        ,@AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        
        List<OnelineReviewMovieView> reviewList = orService.getListByMemberId(userDetails.getId());
        List<Movie> movieList = new ArrayList<>();
        for (OnelineReviewMovieView onelineReviewMovieView : reviewList) {
            Movie movie = movieService.getById(onelineReviewMovieView.getMovieId());
            movieList.add(movie);
        }

        model.addAttribute("rlist", reviewList);
        model.addAttribute("mlist", movieList);
        return "user/mymovie";
    }
    
    @GetMapping("mybet")
    public String mybet() {

        return "user/mybet";
    }
    @GetMapping("myboard")
    public String myboard(
        @AuthenticationPrincipal CustomUserDetails userDetails
        ,Model model
    ) {
        
        List<CommunityBoardView> list = memberService.getMyBoard(userDetails.getId());
        List<Category> clist = cService.getList();
        model.addAttribute("list", list);
        model.addAttribute("clist", clist);
        return "user/myboard";
    }

    @GetMapping("mycomment")
    public String mycomment(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        Model model
    ) {
        List<CommunityBoardCommentsView> cbcList = cbcService.getListByMemberId(userDetails.getId());
        List<Category> clist = cService.getList();
        model.addAttribute("cbclist", cbcList);
        model.addAttribute("clist", clist);
        return "user/mycomment";
    }
    @GetMapping("myinfo")
    public String myinfo(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        Model model
    ) {
        CustomUserDetails refreshedUserDetails = (CustomUserDetails)customUserDetailsService.loadUserByUsername(userDetails.getUsername());
        model.addAttribute("user", refreshedUserDetails);
        return "user/myinfo";
    }

    @PostMapping("myinfo")
    public String myinfo(
        String nickname,
        String password,
        String email,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        if(nickname != null){
            System.out.println("닉네임");
            userDetails.setNickname(nickname);
            memberService.changeUserInfo(userDetails.getId(), nickname, null, null);
        }
        else if(password != null){
            System.out.println("패스워드");
            memberService.changeUserInfo(userDetails.getId(), null, password, null);
        }
        else if(email != null){
            System.out.println("이메일");
            memberService.changeUserInfo(userDetails.getId(), null, null, email);
        }
        

        return "redirect:/user/myinfo";
    }



}
