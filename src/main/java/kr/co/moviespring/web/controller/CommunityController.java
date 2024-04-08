package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.entity.GeneralBoard;
import kr.co.moviespring.web.service.communityService.CommunityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("community")
public class CommunityController {
    @Autowired
    CommunityService communityService;

    // 커뮤니티 메인페이지 요청//
    @GetMapping("main")
    public String main(){
        return "community/main";
    }

    // 커뮤니티 카테고리별 페이지 요청//
    @GetMapping("list")
    public String board(Model model){
        // List <GeneralBoard> list = communityService.getList();

        return "community/list";
    }


    // 게시글 등록페이지 요청//
    @GetMapping("reg")
    public String reg() {
        return "community/reg";
    }

    // 게시글 등록//
    @PostMapping("reg")
    public String reg(String title , String contents){
        communityService.write(title,contents);
        return "redirect:/community/main";
    }
}
