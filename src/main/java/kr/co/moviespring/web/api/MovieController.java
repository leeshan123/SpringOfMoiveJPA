package kr.co.moviespring.web.api;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.OnelineReview;
import kr.co.moviespring.web.service.MovieService;
import kr.co.moviespring.web.service.TwoWeeksMovieService;
    
@RestController("apiTwoWeeksMovieController")
@RequestMapping("api/movie")
public class MovieController {
    
    @Autowired
    TwoWeeksMovieService TwoWeeksMovie;

    // @GetMapping("vote-list")
    // public List<Movie> voteListReg(@RequestParam("pS") String parentSelectValue,
    //                             @RequestParam("cS") String childSelectValue){

    // TWMovieService.findByCriteria(parentSelectValue, childSelectValue);


    //     return movieWeeks;
    // }
    //  @Autowired
    // CommentService commentService;
    // @GetMapping("detail")
    // public List<OnelineReview> detail(@RequestParam("movieid") Long movieId, Model model) {

    //     // 리뷰목록//
    //     List<OnelineReview> onelineReviews = commentService.getOnelineReviews(movieId);


	// // try {
    //     //     Thread.sleep(5000);
    //     // } catch (InterruptedException e) {
    //     //     // TODO Auto-generated catch block
    //     //     e.printStackTrace();
    //     // }

    //     return onelineReviews;
    // }


}
