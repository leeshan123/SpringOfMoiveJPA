package kr.co.moviespring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.moviespring.web.entity.Member;
import kr.co.moviespring.web.service.userService.MemberService;

@Controller
@RequestMapping("user")
public class MemberController {
    // 로그인 공사중//
    @Autowired
    MemberService memberService;

    @GetMapping("signin")
    public String signin() {

        return "user/signin";
    }

    @GetMapping("findid")
    public String findId() {

        return "user/findid";
    }

    @GetMapping("findIdResult")
    public String findIdResult() {

        return "findidresult";
    }


    @GetMapping("findpwd")
    public String findpwd() {

        return "user/findpwd";
    }

    @GetMapping("changepwd")
    public String changePwd() {

        return "user/changepwd";
    }

    @GetMapping("changepwdresult")
    public String changePwdResult() {

        return "user/changepwdresult";
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
    @GetMapping("regist")
    public String regist() {

        return "user/regist";
    }

    @PostMapping("regist")
    public String regist(Member member) {
//        String userId, String pwd, String name, String nickname, int age, String email
        memberService.regist(member);
//        username, password, name, nickname, age, email
        return "redirect:/user/welcome";
    }

    @GetMapping("welcome")
    public String welcome() {

        return "user/welcome";
    }

}
