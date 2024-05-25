package kr.co.moviespring.web.controller;

import java.util.List;

import kr.co.moviespring.web.entity.*;
import kr.co.moviespring.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moviespring.web.config.batch.BatchSchedulerConfig;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    MovieService movieService;

    @Autowired
    ActorService actorService;

    @Autowired
    DirectorService directorService;

    @Autowired
    TwoWeeksMovieService TWMovieService;

    @Autowired
    EventPageService eventService;

    @Autowired
    CommunityBoardService communityService;

    @Autowired
    PlayGroundService playGroundService;


    @GetMapping("")
    public String main(Model model) {
        List<Movie> dList = BatchSchedulerConfig.getList();
        // 올해의 영화
        List<Movie> list = movieService.getListByYear();

        //2주의영화
        List<totalVoteView> TWMovieList = TWMovieService.findByMovieCd();
        totalVoteView Weeksmovie = TWMovieService.findWinnerMovie();
        Long totalVote = TWMovieService.findTotalVote();

        //개봉예정영화
        List<Movie> listAfter = movieService.getListAfter();
        
        model.addAttribute("list", dList);
        model.addAttribute("listAfter", listAfter);

        //이벤트 게시판
        List<EventPage> eventList = eventService.getEventListTop5();
        model.addAttribute("eventList", eventList);

        //커뮤니티 게시판
        List<CommunityBoardView> communitylist = communityService.getList(1L,1, 5);
        model.addAttribute("communitylist", communitylist);

        //놀이터 게시판
         List<PlayGroundBoard> playGroundBoardList = playGroundService.getBoardTop5();
         model.addAttribute("pgbList",playGroundBoardList);
        
        //2주의영화
        model.addAttribute("WeeksMovie", Weeksmovie);
        model.addAttribute("WeeksMovieList", TWMovieList);
        model.addAttribute("v", totalVote);
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

    @GetMapping("deleted-member")
    public String deletedMember(){

        return "deleted-member";
    }

    @GetMapping("ban-member")
    public String banMember(){

        return "ban-member";
    }

}
