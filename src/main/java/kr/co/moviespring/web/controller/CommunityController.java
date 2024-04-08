package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.entity.Category;
import kr.co.moviespring.web.entity.GeneralBoard;
import kr.co.moviespring.web.service.communityService.CommunityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("community")
public class CommunityController {
    @Autowired
    CommunityService communityService;

    // 커뮤니티 메인페이지 요청//
    @GetMapping("main")
    public String main(@RequestParam(name="c",required = false)Long categoryId,Model model){
        List<Category> categories = communityService.getListByCategoryId(categoryId);
        model.addAttribute("ctgId", categories);
        return "community/main";
    }


    //게시글 목록 요청//
    @GetMapping("board/list")
    public String board(@RequestParam(name="c",required = false)Long categoryId,Model model){
        System.out.println("c = " + categoryId);
        List <GeneralBoard> list = communityService.getList(categoryId);
        model.addAttribute("list", list);
        return "community/board/list";
    }

    //게시글 상세 요청//
    @GetMapping("detail")
    public String detail(@RequestParam("b")Long id,Model model){
        GeneralBoard Genboard = communityService.getById(id);
        model.addAttribute("board", Genboard);
        return "community/board/list";
    }
    // 게시글 등록페이지 요청//
    @GetMapping("board/reg")
    public String reg(@RequestParam(name="c",required = false)Long categoryId,Model model) {
        model.addAttribute("cId", categoryId);
        return "community/board/reg";
    }

    // 게시글 등록//
    @PostMapping("board/reg")
    public String reg(String title , String contents,@RequestParam(name="c",required = false)Long categoryId){
        communityService.write(title,contents,categoryId);
        return "redirect:/community/board/list";
    }
}
