package kr.co.moviespring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("community")
public class CommunityController {

    @GetMapping("main")
    public String main(){
        return "community/main";
    }
    
    @GetMapping("reg")
    public String reg() {
        return "community/reg";
    }
    
}
