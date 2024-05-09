package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.TwoWeeksMovie;
import kr.co.moviespring.web.service.MovieService;
import kr.co.moviespring.web.service.TwoWeeksMovieService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("2weeks")
public class TwoWeeksMovieController {

    @Autowired
    MovieService movieService;
    TwoWeeksMovieService TWMovieService;
    @GetMapping("movie")
    public String movie (Model model){
        // Movie movie = movieService.getById(513L);
        // model.addAttribute("movie",movie);


        return "2weeks/movie";
    }

    @GetMapping("list")
    public String list (Model model){
        
        List<TwoWeeksMovie> TWMovie = TWMovieService.findByMovieCd();
        // // System.out.println(TWMovie.toString());
        if(TWMovie == null)
        System.out.println("조회할 정보가 없습니다");
    
        model.addAttribute("Tm", TWMovie);
        return "2weeks/list";
    }
}
