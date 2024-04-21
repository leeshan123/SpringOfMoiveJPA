package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.service.movieService.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    MovieService movieService;

    @GetMapping("index")
    public String index(Model model) {
        List<Movie> list = movieService.getList();
        model.addAttribute("list", list);

    return "index";
    }

}
