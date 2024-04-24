package kr.co.moviespring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import kr.co.moviespring.web.entity.Category;
import kr.co.moviespring.web.entity.CommunityBoard;
import kr.co.moviespring.web.service.categoryService.CategoryService;
import kr.co.moviespring.web.service.communityBoardService.CommunityBoardService;



@Controller
@RequestMapping("community")
public class CommunityBoardController {
    @Autowired
    CommunityBoardService communityBoardService;
    @Autowired
    CategoryService categoryService;

    // 커뮤니티 메인페이지 요청//
    @GetMapping("main")
    public String main(Model model){
        List<Category> categories = categoryService.getList();

        for (int i = 0; i < categories.size(); i++) {
        List <CommunityBoard> list = communityBoardService.getList(categories.get(i).getId(),5);
        model.addAttribute("list"+(i+1), list);
        }
        model.addAttribute("categories", categories);

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
    public String board(@RequestParam(name="c",required = false)String categoryName, Model model){
        Category category = categoryService.getByName(categoryName);
        List <CommunityBoard> list = communityBoardService.getList(category.getId(), 20);
        List<Category> categories = categoryService.getList();
        model.addAttribute("list", list);
        model.addAttribute("c", category);
        model.addAttribute("ctgList", categories);

        return "community/board/list";
    }

    //게시글 상세페이지 요청//
    @GetMapping("board/detail")
    public String detail(@RequestParam("c")String categoryName,
                         @RequestParam("id")Long boardId, Model model){
        //FIXME: 2024-04-14, 일, 22:58 주소창에서 카테고리 쿼리를 변경해도 게시글이 그대로 출력되는 버그있음,
        // 임의로 변경시 게시글의 카테고리명이 변경됨, 쿼리값에 카테고리가 필요한지? (디씨의 경우 있긴 함) -JOON
        CommunityBoard board = communityBoardService.getById(boardId);
        Category category = categoryService.getByName(categoryName);
        model.addAttribute("board", board);
        model.addAttribute("category", category);

        return "community/board/detail";
    }
    // 게시글 등록페이지 요청//
    @GetMapping("board/reg")
    public String reg(@RequestParam(name="c",required = false)String categoryName, Model model) {
        model.addAttribute("cName", categoryName);
        return "community/board/reg";
    }

    // 게시글 등록//
    @PostMapping("board/reg")
    public String reg(String title , String contents, String categoryName){
        Category category = categoryService.getByName(categoryName);
        communityBoardService.write(title,contents,category.getId());
        return "redirect:/community/board/list?c="+categoryName;
    }

    // 게시글 댓글 등록//
    @PostMapping("board/detail")
    public String regComment(String contents,@RequestParam(name="c",required = false)Long categoryId,
                                             @RequestParam(name="b",required = false)Long boardId){
//        communityService.write(contents,categoryId);
        return "redirect:/community/board/detail?b="+boardId;
    }

    // 게시글 등록 수정페이지 요청//
    @GetMapping("board/edit/{id}")
    public String edit(@PathVariable Long id,@RequestParam(name="c",required = false)String categoryName, Model model) {
        CommunityBoard board = communityBoardService.editById(id);
        model.addAttribute("board", board);
        return "community/board/reg";
    }

    // 게시글 수정//
    @PostMapping("board/edit/{id}")
    public String edit(@PathVariable Long id,@RequestParam(name="c",required = false)String categoryName) {
//        CommunityBoard board = communityBoardService.editById(id);
//        model.addAttribute("board", board);
        return "redirect:/community/board/detail?c="+categoryName+"&id="+id;
    }

    // 게시글 삭제//
    @DeleteMapping("board/delete/{id}")
    public String delete(@PathVariable Long id,@RequestParam(name="c",required = false)String categoryName ) {
        int result = communityBoardService.deleteById(id);

        return "redirect:/community/board/list?c="+categoryName;
    }
}
