package kr.co.moviespring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moviespring.web.config.batch.BatchSchedulerConfig;
import kr.co.moviespring.web.entity.Actor;
import kr.co.moviespring.web.entity.Director;
import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.service.ActorService;
import kr.co.moviespring.web.service.DirectorService;
import kr.co.moviespring.web.service.MovieService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    MovieService movieService;

    @Autowired
    ActorService actorService;

    @Autowired
    DirectorService directorService;


    @GetMapping("")
    public String main(Model model) {
        List<Movie> dList = BatchSchedulerConfig.getList();
        // 올해의 영화
        List<Movie> list = movieService.getListByYear();
        model.addAttribute("list", dList);
        //개봉예정영화
        List<Movie> listAfter = movieService.getListAfter();
        model.addAttribute("listAfter", listAfter);

        return "main";
    }

    //게시글 검색페이지 요청
    @GetMapping("search")
    public String search(@RequestParam(name="query",required = false)String query, Model model) {
        List<Movie> mList = movieService.getListByName(query);
        List<Actor> aList = actorService.getListByName(query);
        List<Director> dList = directorService.getListByName(query);

//        model.addAttribute("query", query);
        model.addAttribute("mList", mList);
        model.addAttribute(("dList"), dList);
        model.addAttribute(("aList"), aList);

        return "search";
    }

    //영화 검색 더보기페이지 요청
    @GetMapping("search/movies")
    public String searchMovie(@RequestParam(name="query",required = false)String query, Model model) {
        List<Movie> mList = movieService.getListByName(query);
//        List<Actor> aList = actorService.getListByName(query);
//        List<Director> dList = directorService.getListByName(query);
//
////        model.addAttribute("query", query);
        model.addAttribute("mList", mList);
//        model.addAttribute(("dList"), dList);
//        model.addAttribute(("aList"), aList);

        return "search-movie";
    }

    //인물 검색 더보기페이지 요청
    @GetMapping("search/people")
    public String searchPeople(@RequestParam(name="query",required = false)String query, Model model) {
//        List<Movie> mList = movieService.getListByName(query);
        List<Actor> aList = actorService.getListByName(query);
        List<Director> dList = directorService.getListByName(query);
//
////        model.addAttribute("query", query);
//        model.addAttribute("mList", mList);
        model.addAttribute(("dList"), dList);
        model.addAttribute(("aList"), aList);

        return "search-people";
    }

}
