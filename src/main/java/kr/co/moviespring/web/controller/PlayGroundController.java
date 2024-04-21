package kr.co.moviespring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("playground")
public class PlayGroundController {

    @GetMapping("main")
    public String main(){

        return "playground/main";
    }
}