package kr.co.moviespring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("2weeks")
public class TwoWeeksMovieController {
    
    @GetMapping("movie")
    public String movie (){
        return "2weeks/movie";
    }

    @GetMapping("list")
    public String list (){
        return "2weeks/list";
    }
}
