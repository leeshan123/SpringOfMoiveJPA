package kr.co.moviespring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.moviespring.web.memberService.MemberService;

@Controller
@RequestMapping("user")
public class UserController {
//------------------------로그인---------------------//
    @Autowired
    MemberService memberService;

    @GetMapping("signin")
    public String signin(){

        return "user/signin";
    }
    // @PostMapping("signin")
    // public String signin(String userId , String pwd){
    //     boolean valid = memberService.validate();

    //     return "redirect:/movie/list";
    // }

//------------------------회원가입---------------------//

    @GetMapping("regist")
    public String regist(){
        
        return "user/regist";
    }



    // @PostMapping("membership")
    // public String membership(String userId , String pwd ,String name, String nickname ,int age , String email){
        
    //     Member Member = memberService.membership(userId,pwd,name,nickname,age,email);

    //     return "redirect:/user/signin";
    // }

}
