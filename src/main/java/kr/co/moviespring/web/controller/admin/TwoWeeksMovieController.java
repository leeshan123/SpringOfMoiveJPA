package kr.co.moviespring.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    
    // service에서 구현할것 delete ,insert 쿼리함께실행시 트렌젝션 내부에서 작업하도록 어노테이션 적용해주면됨 5/22
    @PostMapping("vote-list")
    @Transactional
    public String voteListReg(@RequestParam("pS") String parentSelectValue,
                                @RequestParam("cS") String childSelectValue){
        //컨트롤러에 있을 로직이아닌거같음 수정 ㄱㄱ
        //부모셀렉터도 매퍼에 동적 템플릿레터럴로 넣으면 해결
        TWMovieService.initList(parentSelectValue,childSelectValue);

        
        return "admin/2weeks/vote-list";
    }
    
}
