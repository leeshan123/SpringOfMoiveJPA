package kr.co.moviespring.web.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moviespring.web.entity.Actor;
import kr.co.moviespring.web.entity.Director;
import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.MovieSearchView;
import kr.co.moviespring.web.entity.OnelineReview;
import kr.co.moviespring.web.service.MovieService;
import kr.co.moviespring.web.service.OnelineReviewService;

@Controller("AdminMovieManagementController") //이름의 중복을 피하기 위해 ioc컨테이너에 담기는 이름을 직접 작성해줄수있다
@RequestMapping("admin/movie-management")
public class MovieManagementController {

    @Autowired
    MovieService movieService;
    @Autowired
    OnelineReviewService onelineReviewService;
    
    @GetMapping("list")
    public String list(
        @RequestParam(name="q",required = false)String query
        , @RequestParam(name = "p", required = false, defaultValue = "1") Integer page
        , Model model
    ) {
        List<MovieSearchView> list = new ArrayList<>();
        int cnt = 0;
        if(query != null){
            list = movieService.getListView(page, query);
            cnt = movieService.getCount(query);
        } else {
            list = movieService.getListView(page);
            cnt = movieService.getCount();
        }

        model.addAttribute("searchQuery", query);
        model.addAttribute("list", list);
        model.addAttribute("count", cnt);
        return "admin/movie-management/list"; 
    }

    @GetMapping("search")
    public String search(
        @RequestParam(name="query",required = false)String query
        , @RequestParam(name = "page", required = false, defaultValue = "1") Integer page
        , Model model
    ) {
        List<MovieSearchView> list = new ArrayList<>();
        int cnt = 0;
        if(query != null){
            list = movieService.getListView(page, query);
            cnt = movieService.getCount(query);
        } else {
            list = movieService.getListView(page);
            cnt = movieService.getCount();
        }

        model.addAttribute("searchQuery", query);
        model.addAttribute("list", list);
        model.addAttribute("count", cnt);

        return "admin/movie-management/list";
    }

    
    @GetMapping("reg")
    public String reg() {
    
        return "admin/movie-management/reg"; 
    }
}
