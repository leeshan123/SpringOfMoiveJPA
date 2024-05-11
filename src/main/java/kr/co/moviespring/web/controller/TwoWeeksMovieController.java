package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.TwoWeeksMovie;
import kr.co.moviespring.web.entity.VoteMemberList;
import kr.co.moviespring.web.service.MovieService;
import kr.co.moviespring.web.service.TwoWeeksMovieService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        List<TwoWeeksMovie> TWMovie = TWMovieService.findByMovieCd();
        String genre = TWMovieService.findGenreName();
        // // System.out.println(TWMovie.toString());
        if (TWMovie == null)
            System.out.println("조회할 정보가 없습니다");

        model.addAttribute("Tm", TWMovie);
        model.addAttribute("g", genre);
        return "2weeks/list";
    }

    @PostMapping("list")
    public String voteMovie(@RequestParam("memberId") Long memberId,
            @RequestParam("movieId") Integer movieId) {
        try {
            VoteMemberList voteMember = TWMovieService.vote(memberId, movieId);
            return "redirect:/2weeks/list";
        } catch (Exception e) {
            return "redirect:/2weeks/list?error=unknown";
        }

    }

    // sql pk중복오류 반환 exception 이름이 뭐임;
    // public class DuplicateDataException extends
    //         SQLIntegrityConstraintViolationException {
    //     public DuplicateDataException(String message) {
    //         super(message);
    //     }
    // }
}
