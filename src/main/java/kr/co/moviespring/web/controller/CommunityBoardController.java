package kr.co.moviespring.web.controller;

import java.util.List;

import kr.co.moviespring.web.entity.CommunityBoardView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import kr.co.moviespring.web.config.security.CustomUserDetails;
import kr.co.moviespring.web.entity.Category;
import kr.co.moviespring.web.entity.CommunityBoard;
import kr.co.moviespring.web.service.CategoryService;
import kr.co.moviespring.web.service.CommunityBoardService;



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
            Long categoryId = categories.get(i).getId();
            List <CommunityBoardView> list = communityBoardService.getList(categoryId,1, 5);
            model.addAttribute("list"+(i+1), list); // list1,2,3,4,5로 보내려고 +1해줌 특별한 의미 없음
        }
        model.addAttribute("categories", categories);

        return "community/main";
    }


    //게시글 목록 요청//
    @GetMapping("board/list")
    public String board(@RequestParam(name = "c",required = false) String categoryName,
                        @RequestParam(name = "q",required = false) String query,
                        @RequestParam(name = "p",required = false, defaultValue = "1") Integer page,
                        Model model){
        Category category = categoryService.getByName(categoryName);
        Long categoryId = category.getId();
        List <CommunityBoardView> list = communityBoardService.getList(categoryId, page, 20);
        int count = 0;
        count = communityBoardService.getCount(categoryId);


        if (query != null) {
            list = communityBoardService.getList(categoryId, page, 20, query);
            count = communityBoardService.getCount(categoryId, query);
        }

        List<Category> categories = categoryService.getList();
        model.addAttribute("list", list);
        model.addAttribute("count", count);
        model.addAttribute("c", category);
        model.addAttribute("ctgList", categories);

        return "community/board/list";
    }

    //게시글 상세페이지 요청//
    @GetMapping("board/detail")
    
    public String detail(@RequestParam("c")String categoryName,
                         @RequestParam("id")Long boardId, Model model,@AuthenticationPrincipal CustomUserDetails userDetails){
        //FIXME: 2024-04-14, 일, 22:58 주소창에서 카테고리 쿼리를 변경해도 게시글이 그대로 출력되는 버그있음,
        // 임의로 변경시 게시글의 카테고리명이 변경됨, 쿼리값에 카테고리가 필요한지? (디씨의 경우 있긴 함) -JOON
        Long memberId = null;
        if(userDetails != null)
        memberId= userDetails.getId();

        CommunityBoardView board1 = communityBoardService.getById(boardId);
        // CommunityBoard board = communityBoardService.getById(boardId); 예전버전
        Category category = categoryService.getByName(categoryName);

        model.addAttribute("userId", memberId);
        model.addAttribute("board", board1);
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
    public String reg(String title , String contents, String categoryName,@AuthenticationPrincipal CustomUserDetails userDetails){
        Category category = categoryService.getByName(categoryName);

        Long memberId = null;
        if(userDetails != null)
        memberId= userDetails.getId();
        
        communityBoardService.write(memberId,title,contents,category.getId());
        System.out.println("등록");
        return "redirect:/community/board/list?c="+categoryName;
    }

    // 게시글 댓글 등록//
    @PostMapping("board/detail")
    public String regComment(String contents,@RequestParam(name="c",required = false)String categoryName,
                                             @RequestParam(name="id",required = false)Long boardId){
//        communityBoardService.write(contents,categoryId);
        return "redirect:/community/board/detail?c="+categoryName+"&id="+boardId;
    }

    // 게시글 등록 수정페이지 요청//
    @GetMapping("board/edit/{id}")
    public String edit(@PathVariable Long id,@RequestParam(name="c",required = false)String categoryName, Model model) {
        // CommunityBoard board = communityBoardService.getById(id);
        CommunityBoardView board = communityBoardService.getById(id);

        model.addAttribute("board", board);
        model.addAttribute("cName", categoryName);
        return "community/board/reg";
    }

    // 게시글 수정// //put으로 바꿔야함
    @PostMapping("board/edit/{id}")
    public String edit(String contents , String title, @PathVariable Long id, String categoryName) {
        communityBoardService.editById(id, title, contents);
//        model.addAttribute("board", board);
        return "redirect:/community/board/detail?c="+categoryName+"&id="+id;
    }

    // 게시글 삭제// //delete로 바꿔야함
    @GetMapping("board/delete/{id}")
    public String delete(@PathVariable Long id,@RequestParam(name="c",required = false)String categoryName ) {
        int result = communityBoardService.deleteById(id);

        return "redirect:/community/board/list?c="+categoryName;
    }
}
