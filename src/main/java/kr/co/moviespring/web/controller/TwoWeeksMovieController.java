package kr.co.moviespring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.moviespring.web.config.security.CustomUserDetails;
import kr.co.moviespring.web.entity.OnelineReview;
import kr.co.moviespring.web.entity.OnelineReviewView;
import kr.co.moviespring.web.entity.totalVoteView;
import kr.co.moviespring.web.service.MovieService;
import kr.co.moviespring.web.service.OnelineReviewService;
import kr.co.moviespring.web.service.TwoWeeksMovieService;
import kr.co.moviespring.web.service.UserPointService;

@Controller
@RequestMapping("2weeks")
public class TwoWeeksMovieController {

      @Autowired
    OnelineReviewService onelineReviewService;
    @Autowired
    MovieService movieService;
    @Autowired
    TwoWeeksMovieService TWMovieService;
    @Autowired
    UserPointService userPointService;

    @GetMapping("movie")
    public String movie(Model model,@AuthenticationPrincipal CustomUserDetails userDetails) {
        
        totalVoteView Weeksmovie = TWMovieService.findWinnerMovie();
        // Long movieId = Long.parseLong(Weeksmovie.getMovieCd());
        Long movieId = Weeksmovie.getMovieCd();


     List<OnelineReviewView> onelineReviews = onelineReviewService.getList(movieId);
        if (userDetails != null) {
        Long memberId = userDetails.getId();
            OnelineReview review = onelineReviewService.getById(movieId, memberId);
            if (review != null)
            model.addAttribute("myReview", review);
        }
        {
            model.addAttribute("avgRate", 15000); //리뷰가 없을경우 기본값 전송
            if (onelineReviews.size() != 0) {
                int total = 0;
                int avg = 0;
                for (int i = 0; i < onelineReviews.size(); i++) {
                    total += onelineReviews.get(i).getMemberRate();
                }
                avg = total / (onelineReviews.size());
                int intAvg = avg / 100 * 100; // 소수점 앞 2자리 잘라서 100단위까지만 나오게
                model.addAttribute("avgRate", intAvg); //유저 평점을 기준으로 평균가격 측정
            }
        }
        // {
        //     model.addAttribute("avgRate", 15000); //리뷰가 없을경우 기본값 전송
        //     if (onelineReviews.size() != 0) {
        //         int total = 15000;
        //         int avg = 0;
        //         for (int i = 0; i < onelineReviews.size(); i++) {
        //             total += onelineReviews.get(i).getMemberRate();
        //         }
        //         avg = total / (onelineReviews.size()+1);
        //         model.addAttribute("avgRate", avg); //유저 평점을 기준으로 평균가격 측정
        //     }
        // }
        model.addAttribute("reviews", onelineReviews);
        model.addAttribute("movie", Weeksmovie);
        model.addAttribute("user", userDetails);
        //서비스로직이 컨트롤러까지 올라온것같음 나중에 수정 ㄱㄱ
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


    @PostMapping("list")
    public String voteMovie(@RequestParam("memberId") Long memberId,
        @RequestParam("movieId") Integer movieId,RedirectAttributes redirectAttributes) {
            TWMovieService.vote(memberId, movieId);
            // redirectAttributes.addFlashAttribute("message", "포인트가 지급되었습니다.");
        return "redirect:/2weeks/list";
    }

    //2주의영화 한줄평 영화한줄평이랑 동일
    @PostMapping("comment")
    public String comment(String comments, @RequestParam(value = "rate", defaultValue = "15000") int rate, @RequestParam("movieCd") Long movieId,
                          @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long memberId = userDetails.getId();
        onelineReviewService.saveComment(memberId, comments, rate, movieId);
        // onelineReviewService.deleteComment(memberId, movieId);


        return "redirect:/2weeks/movie";
    }

    @PostMapping("comment/edit/{movieCd}")
    public String edit(String comments , int rate, @PathVariable("movieCd") Long movieId,
                       @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long memberId = userDetails.getId();
        onelineReviewService.editComment(memberId, comments, rate, movieId);

        return "redirect:/2weeks/movie";
    }

    @PostMapping("comment/delete/{movieCd}")
    public String delete(@PathVariable("movieCd") Long movieId,@AuthenticationPrincipal CustomUserDetails userDetails){
        Long memberId = userDetails.getId();
        onelineReviewService.deleteComment(memberId, movieId);
        return "redirect:/2weeks/movie";
    
    }
}