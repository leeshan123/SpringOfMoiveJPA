package kr.co.moviespring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("weeks")
public class TwoWeeksMovieController {
    
    @GetMapping("list")
    public String list (){
        return "weeks/list";
    }
}
