package kr.co.moviespring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.service.MovieService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("movie")
public class MovieController {

    @Autowired
    MovieService movieService;


    @GetMapping("list")
    public String list(Model model){
        List <Movie> list = movieService.getList();
        model.addAttribute("list", list);
        return "movie/list";
    }

    @GetMapping("detail")
    public String detail(Model model){
        List <Movie> list = movieService.getList();
        model.addAttribute("list", list);
        return "movie/detail";
    }
    
}
