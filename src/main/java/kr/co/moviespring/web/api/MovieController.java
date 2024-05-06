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
    
@RestController("apiTwoWeeksMovieController")
@RequestMapping("api/movie")
public class MovieController {
    
    @Autowired
    MovieService movieService;

    @GetMapping("vote-list")
    public List<Movie> voteListReg(@RequestParam("pS") String parentSelectValue,
                                @RequestParam("cS") String childSelectValue){

        int psv= Integer.parseInt(parentSelectValue);

        System.out.println(psv);
        System.out.println("자식 select 값: " + childSelectValue);
                List<Movie> movieWeeks = new ArrayList<>();

        if(psv==1){
            
            movieService.findByReleseDate(childSelectValue);
            movieWeeks = movieService.findAllEditedList();
        }
        else if(psv==2){
            
            movieService.findByGenre(childSelectValue);
        }
        else if(psv==3){
            
            movieService.findByDistributor(childSelectValue);
        }

        return movieWeeks;
    }
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
