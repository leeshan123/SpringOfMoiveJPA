package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.entity.Category;
import kr.co.moviespring.web.entity.GeneralBoard;
import kr.co.moviespring.web.service.categoryservice.CategoryService;
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
    @Autowired
    CategoryService categoryService;

    // 커뮤니티 메인페이지 요청//
    @GetMapping("main")
    public String main(Model model){
        List<Category> categories = categoryService.getListAllCategoryId();
        List <GeneralBoard> list1 = communityService.getList1();
        List <GeneralBoard> list2 = communityService.getList2();
        List <GeneralBoard> list3 = communityService.getList3();
        List <GeneralBoard> list4 = communityService.getList4();

        model.addAttribute("ctgId", categories);
        model.addAttribute("lits", list1);
        model.addAttribute("list1", list1);
        model.addAttribute("list2", list2);
        model.addAttribute("list3", list3);
        model.addAttribute("list4", list4);


        return "community/main";
    }


    //게시글 목록 요청//
    @GetMapping("board/list")
    public String board(@RequestParam(name="c",required = false)Long categoryId,Model model){
        List <GeneralBoard> list = communityService.getList(categoryId);
        Category categories = categoryService.getNameByCategoryId(categoryId);
        model.addAttribute("list", list);
        model.addAttribute("c", categories);

        return "community/board/list";
    }

    //게시글 상세페이지 요청//
    @GetMapping("board/detail")
    public String detail(@RequestParam("b")Long id,Model model){
        GeneralBoard Genboard = communityService.getById(id);
        // Long categoryId = Genboard.getCategoryId();
        // String categoryName = communityService;
        model.addAttribute("board", Genboard);

        return "community/board/detail";
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

    // 게시글 댓글 등록//
    @PostMapping("board/detail")
    public String regComment(String contents,@RequestParam(name="c",required = false)Long categoryId,@RequestParam(name="b",required = false)Long boardId){
        // communityService.write(contents,categoryId);
        return "redirect:/community/board/detail?b="+boardId;
    }
}
