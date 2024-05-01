package kr.co.moviespring.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.moviespring.web.service.admin.TwoweeksMovieService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller("AdminTwoWeeksMovieController")
@RequestMapping("admin/2weeks")
public class TwoWeeksMovieController {

    @Autowired
    TwoweeksMovieService twoweeksMovieService;

    @GetMapping("vote-list")
    public String voteList () {
        return "admin/2weeks/vote-list";
    }
    
    @PostMapping("vote-list")
    public String voteListReg(){

        
        
        return "admin/2weeks/vote-list";
    }
    
}
