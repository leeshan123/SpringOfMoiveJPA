package kr.co.moviespring.web.controller;

import java.util.List;

import kr.co.moviespring.web.entity.*;
import kr.co.moviespring.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moviespring.web.config.security.CustomUserDetails;

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
    @GetMapping("list")
    public String list(Model model) {

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

        List<Movie> list = movieService.getList();
        model.addAttribute("list", list);
        return "movie/list";
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
        List<OnelineReview> onelineReviews = onelineReviewService.getOnelineReviews(movieId);

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
    public String comment(String comments, int rate, @RequestParam("movie-id") Long movieId) {

        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(id);

        onelineReviewService.saveComment(id, comments, rate, movieId);

        System.out.println("댓글작성");
        return "redirect:/movie/detail?movieid=" + movieId;
    }

    @GetMapping("actor")
    public String actor() {

        return "movie/actor";
    }

}
