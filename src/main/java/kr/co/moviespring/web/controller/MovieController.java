package kr.co.moviespring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moviespring.web.entity.OnelineReview;
import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.service.CommentService;
import kr.co.moviespring.web.service.MovieService;

@Controller
@RequestMapping("movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    CommentService commentService;

    @GetMapping("list")
    public String list(Model model){
        List <Movie> list = movieService.getList();
        model.addAttribute("list", list);
        return "movie/list";
    }

    @GetMapping("detail")
    public String detail(@RequestParam("movieid")Long movieId,Model model){
        Movie movie = movieService.getById(movieId);
        model.addAttribute("movie", movie);

        return "movie/detail";
    }
    
    // @PostMapping("Comment")
    // public String comment(){

    //         List<Comment> comments = commentService.SaveComment();

    //     return "redirect:/movie/list";
    // }
    @PostMapping("comment")
    public String comment(String comments,int rate,@RequestParam("movie-id") Long movieId){
        System.out.println("post 요청");

        OnelineReview review = commentService.SaveComment(comments,rate,movieId);
        
        System.out.println("댓글작성");
        return "redirect:/movie/detail?movieid="+movieId;
    }
}
