package kr.co.moviespring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("community")
public class CommunityController {

    @GetMapping("main")
    public String main(){
        return "community/main";
    }

    @GetMapping("board")
    public String board(){
        return "community/board";
    }

    @GetMapping("reg")
    public String reg() {
        return "community/reg";
    }
}
