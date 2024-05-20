package kr.co.moviespring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moviespring.web.entity.totalVoteView;
import kr.co.moviespring.web.service.MovieService;
import kr.co.moviespring.web.service.TwoWeeksMovieService;

@Controller
@RequestMapping("2weeks")
public class TwoWeeksMovieController {

    @Autowired
    MovieService movieService;
    @Autowired
    TwoWeeksMovieService TWMovieService;

    @GetMapping("movie")
    public String movie(Model model) {
        // Movie movie = movieService.getById(513L);
        // model.addAttribute("movie",movie);

        return "2weeks/movie";
    }

    @GetMapping("list")
    public String list(Model model) {
        // 2주영화 목록
        List<totalVoteView> TWMovie = TWMovieService.findByMovieCd();
        // 테마이름
        String genre = TWMovieService.findGenreName();
        // 투표합계
        Long totalVote = TWMovieService.findTotalVote();

        model.addAttribute("Tm", TWMovie);
        model.addAttribute("g", genre);
        model.addAttribute("v", totalVote);
        return "2weeks/list";
    }

    // public void endVotingAndSelectWeeklyMovie() {

    //     // TWMovieService.findRegDate();

    //     LocalDateTime now = LocalDateTime.now();
    //     LocalDateTime endOfVotingTime = now.plusDays(7);
    // }

    @PostMapping("list")
    public String voteMovie(@RequestParam("memberId") Long memberId,
        @RequestParam("movieId") Integer movieId) {
            TWMovieService.vote(memberId, movieId);
        return "redirect:/2weeks/list";
    }
}