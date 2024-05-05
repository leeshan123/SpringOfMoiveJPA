package kr.co.moviespring.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.service.MovieService;


@Controller("AdminTwoWeeksMovieController")
@RequestMapping("admin/2weeks")
public class TwoWeeksMovieController {

    // TwoweeksMovieService twoweeksMovieService;

    @Autowired
    MovieService movieService;
    @GetMapping("vote-list")
    public String voteList () {
        return "admin/2weeks/vote-list";
    }
    
    @PostMapping("vote-list")
    public String voteListReg(@RequestParam("pS") String parentSelectValue,
                                @RequestParam("cS") String childSelectValue){

        int psv= Integer.parseInt(parentSelectValue);

        System.out.println(psv);
        System.out.println("자식 select 값: " + childSelectValue);
        
        if(psv==1){
            
            movieService.findByReleseDate(childSelectValue);
        //    Movie movieWeeks = movieService.findAllEditedList();
        }
        else if(psv==2){
            
            movieService.findByGenre(childSelectValue);
        }
        else if(psv==3){
            
            movieService.findByDistributor(childSelectValue);
        }





        return "admin/2weeks/vote-list";
    }
    
}
