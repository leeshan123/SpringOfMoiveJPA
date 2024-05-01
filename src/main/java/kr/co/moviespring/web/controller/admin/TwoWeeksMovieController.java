package kr.co.moviespring.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller("AdminTwoWeeksMovieController")
@RequestMapping("admin/2weeks")
public class TwoWeeksMovieController {

    @GetMapping("vote-list")
    public String voteList () {
        return "admin/2weeks/vote-list";
    }
    
    @PostMapping("vote-list")
    public String voteListReg(){

        return "admin/2weeks/vote-list";
    }
    
}
