package kr.co.moviespring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.service.movieService.MovieService;

@Controller("2weeks")
public class TwoWeeksMovieController {
    @Autowired
    MovieService movieService;
    
    @GetMapping("list")
    public String list (Model model){
        List<Movie> list = movieService.getList();
        model.addAttribute("list", list);
        return "2weeks/list";
    }
}
