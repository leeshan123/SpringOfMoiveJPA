package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.entity.GeneralBoard;
import kr.co.moviespring.web.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


@Controller
@RequestMapping("community")
public class CommunityController {
    @Autowired
    CommunityService communityService;

    @GetMapping("main")
    public String main(){
        return "community/main";
    }

    @GetMapping("board")
    public String board(){
        return "list";
    }



    @GetMapping("reg")
    public String reg() {
        return "community/reg";
    }

    @PostMapping("reg")
    public String reg(String title , String contents){
        communityService.write(title,contents);
        return "redirect:/community/main";
    }
}
