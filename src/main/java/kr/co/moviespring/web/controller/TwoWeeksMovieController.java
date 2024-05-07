package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("2weeks")
public class TwoWeeksMovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("movie")
    public String movie (Model model){
        Movie movie = movieService.getById(513L);
        model.addAttribute("movie",movie);


        return "2weeks/movie";
    }

    @GetMapping("list")
    public String list (){
        return "2weeks/list";
    }
}
