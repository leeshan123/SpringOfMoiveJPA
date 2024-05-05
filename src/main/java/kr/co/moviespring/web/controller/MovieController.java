package kr.co.moviespring.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moviespring.web.config.batch.BatchSchedulerConfig;
import kr.co.moviespring.web.config.security.CustomUserDetails;
import kr.co.moviespring.web.entity.Director;
import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.MovieActorView;
import kr.co.moviespring.web.entity.MovieStillcut;
import kr.co.moviespring.web.entity.MovieTrailer;
import kr.co.moviespring.web.entity.OnelineReviewView;
import kr.co.moviespring.web.movieapi.dto.kobis.KobisDailyBox;
import kr.co.moviespring.web.service.MovieActorService;
import kr.co.moviespring.web.service.MovieDirectorService;
import kr.co.moviespring.web.service.MovieService;
import kr.co.moviespring.web.service.MovieStillcutService;
import kr.co.moviespring.web.service.MovieTrailerService;
import kr.co.moviespring.web.service.OnelineReviewService;

@Controller
@RequestMapping("movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    OnelineReviewService onelineReviewService;

    @Autowired
    MovieActorService movieActorService;

    @Autowired
    MovieDirectorService movieDirectorService;

    @Autowired
    MovieStillcutService movieStillcutService;

    @Autowired
    MovieTrailerService movieTrailerService;


    // 영화 목록//
    @GetMapping("main")
    public String main(Model model) {

        //영화 받아오기 테스트
        // List <Movie> mlist = new ArrayList<>();
        // MovieAPI api = new MovieAPI();
        // List<MovieInfoEntity> miList = new ArrayList<>();
        // List<DailyBoxEntity> dbeList = new ArrayList<>();
        // dbeList = api.requestBoxDailly();
        // for (DailyBoxEntity dailyBoxEntity : dbeList) {
        //     MovieInfoEntity movieInfo;
        //     movieInfo = api.requestMovieInfo(dailyBoxEntity.getMovieCd());
        //     miList.add(movieInfo);
        //     MovieUrlEntity movieUrl = api.requestUrl(movieInfo.getMovieNm(), movieInfo.getOpenDt());

        //     Movie m = new Movie();
        //     m.setId(Long.parseLong(dailyBoxEntity.getMovieCd()));
        //     m.setTotalBoxoffice(dailyBoxEntity.getRank());
        //     m.setDailyAudience(dailyBoxEntity.getAudiCnt());
        //     m.setTotalAudience(dailyBoxEntity.getAudiAcc());
        //     m.setTotalSales(Long.parseLong(dailyBoxEntity.getSalesAcc()));
        //     m.setKorName(movieInfo.getMovieNm());
        //     m.setEngName(movieInfo.getMovieNmEn());
        //     m.setReleaseDate(movieInfo.getOpenDt());
        //     m.setReleaseNationId(movieInfo.getNationNm());
        //     m.setGenreId(movieInfo.getGenreNm());
        //     m.setTrailerUrl(movieUrl.getTrailerUrl());
        //     m.setPosterUrl(movieUrl.getPosterUrl());
        //     m.setStillcutUrl(movieUrl.getStillcutUrl());
        //     m.setMovieIntro(movieUrl.getOverView());
        //     mlist.add(m);
        // }
        // List<String> sList = BatchSchedulerConfig.getList();
        // List<Movie> dailyList = new ArrayList<>();
        // for (String mCode : sList) {
        //     Movie movie = movieService.getByKobisId(mCode);
        //     dailyList.add(movie);
        // }
        List<Movie> dailyList = BatchSchedulerConfig.getList();
        List<Movie> list = movieService.getList();

        // 서버로 돌면 풀어주기, 일단 그냥 리스트로
        if(dailyList == null)
            dailyList = list;

        model.addAttribute("dlist", dailyList);
        model.addAttribute("list", list);
        return "movie/main";
    }

    // 영화 상세//
    @GetMapping("detail")
    public String detail(@AuthenticationPrincipal CustomUserDetails userDetails,
                         @RequestParam("movieid") Long movieId, Model model) {
        // 상세정보//
        Movie movie = movieService.getById(movieId);
        List<MovieActorView> actors = movieActorService.getById(movieId);
        List<Director> directors = movieDirectorService.getById(movieId); //디렉터 타입인데 무비디렉터 서비스 쓰는게 맞나?
        List<MovieStillcut> stillcuts = movieStillcutService.getById(movieId);
        List<MovieTrailer> trailers = movieTrailerService.getById(movieId);
        // 리뷰목록//
        List<OnelineReviewView> onelineReviews = onelineReviewService.getOnelineReviews(movieId);

        model.addAttribute("movie", movie);
        model.addAttribute("actors", actors);
        model.addAttribute("directors", directors);
        model.addAttribute("reviews", onelineReviews);
        model.addAttribute("stillcuts", stillcuts);
        model.addAttribute("trailers", trailers);
        model.addAttribute("user", userDetails); //유저 정보 객체 넣어줌 테스트중

        return "movie/detail";
    }

    // @PostMapping("Comment")
    // public String comment(){

    // List<Comment> comments = commentService.SaveComment();

    // return "redirect:/movie/list";
    // }
    // 한줄평 등록//
    @PostMapping("comment")
    public String comment(String comments, int rate, @RequestParam("movieid") Long movieId) {

        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(id);

        onelineReviewService.saveComment(id, comments, rate, movieId);

        System.out.println("댓글작성");
        return "redirect:/movie/detail?movieid=" + movieId;
    }

//    @GetMapping("actor")
//    public String actor() {
//
//        return "movie/people";
//    }

}
