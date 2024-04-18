package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.config.security.CustomUserDetails;
import kr.co.moviespring.web.entity.Category;
import kr.co.moviespring.web.entity.GeneralBoard;
import kr.co.moviespring.web.service.categoryservice.CategoryService;
import kr.co.moviespring.web.service.communityService.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;



@Controller
@RequestMapping("community")
public class CommunityController {
    @Autowired
    CommunityService communityService;
    @Autowired
    CategoryService categoryService;

    // 커뮤니티 메인페이지 요청//
    @GetMapping("main")
    public String main(Model model){
        List<Category> categories = categoryService.getList();

        for (int i = 0; i < categories.size(); i++) {
        List <GeneralBoard> list = communityService.getList(categories.get(i).getId(),5);
        model.addAttribute("list"+(i+1), list);
        }
        model.addAttribute("ctgId", categories);

//        List <GeneralBoard> list2 = communityService.getList2();
//        List <GeneralBoard> list3 = communityService.getList3();
//        List <GeneralBoard> list4 = communityService.getList4();

//        model.addAttribute("list1", list1);
//        model.addAttribute("list2", list2);
//        model.addAttribute("list3", list3);
//        model.addAttribute("list4", list4);

        return "community/main";
    }


    //게시글 목록 요청//
    @GetMapping("board/list")
    public String board(@RequestParam(name="c",required = false)Long categoryId, Model model){
        List <GeneralBoard> list = communityService.getList(categoryId, 20);
        Category category = categoryService.getById(categoryId);
        model.addAttribute("list", list);
        model.addAttribute("c", category);

        return "community/board/list";
    }

    //게시글 상세페이지 요청//
    @GetMapping("board/detail")
    public String detail(@RequestParam("c")Long categoryId,
                         @RequestParam("id")Long boardId, Model model){
        //FIXME: 2024-04-14, 일, 22:58 주소창에서 카테고리 쿼리를 변경해도 게시글이 그대로 출력되는 버그있음,
        // 임의로 변경시 게시글의 카테고리명이 변경됨, 쿼리값에 카테고리가 필요한지? (디씨의 경우 있긴 함) -JOON
        GeneralBoard board = communityService.getById(boardId);
        Category category = categoryService.getById(categoryId);
        model.addAttribute("board", board);
        model.addAttribute("category", category);

        return "community/board/detail";
    }
    // 게시글 등록페이지 요청//
    @GetMapping("board/reg")
    public String reg(@RequestParam(name="c",required = false)Long categoryId, Model model) {
        model.addAttribute("cId", categoryId);
        return "community/board/reg";
    }

    // 게시글 등록//
    @PostMapping("board/reg")
    public String reg(String title , String contents,@RequestParam(name="c",required = false)Long categoryId){
        communityService.write(title,contents,categoryId);
        return "redirect:/community/board/list";
    }

    // 게시글 댓글 등록//
    @PostMapping("board/detail")
    public String regComment(String contents,@RequestParam(name="c",required = false)Long categoryId,
                                             @RequestParam(name="b",required = false)Long boardId){
//        communityService.write(contents,categoryId);
        return "redirect:/community/board/detail?b="+boardId;
    }
}
