package kr.co.moviespring.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.totalVoteView;
import kr.co.moviespring.web.service.MovieService;
import kr.co.moviespring.web.service.TwoWeeksMovieService;


@Controller("AdminTwoWeeksMovieController")
@RequestMapping("admin/2weeks")
public class TwoWeeksMovieController {

    // TwoweeksMovieService twoweeksMovieService;

    @Autowired
        TwoWeeksMovieService TWMovieService;

    @GetMapping("vote-list")
    public String voteList (Model model) {
        List<totalVoteView> movieList = TWMovieService.findByMovieCd();
        Long totalVote = TWMovieService.findTotalVote();
        
        System.out.println("Movies: " + movieList);
        model.addAttribute("movies", movieList);
        model.addAttribute("v", totalVote);
        return "admin/2weeks/vote-list";
    }
    
    @PostMapping("vote-list")
    public String voteListReg(@RequestParam("pS") String parentSelectValue,
                                @RequestParam("cS") String childSelectValue){
        //컨트롤러에 있을 로직이아닌거같음 수정 ㄱㄱ
        int psv= Integer.parseInt(parentSelectValue);

        System.out.println(psv);
        System.out.println("자식 select 값: " + childSelectValue);
        
        if(psv==1){
            
            TWMovieService.findByReleseDate(childSelectValue);
        }
        else if(psv==2){
            
            TWMovieService.findByGenre(childSelectValue);
        }
        else if(psv==3){
            
            TWMovieService.findByDistributor(childSelectValue);
        }





        return "admin/2weeks/vote-list";
    }
    
}
