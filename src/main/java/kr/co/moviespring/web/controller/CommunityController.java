package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.entity.Category;
import kr.co.moviespring.web.entity.GeneralBoard;
import kr.co.moviespring.web.service.categoryservice.CategoryService;
import kr.co.moviespring.web.service.communityService.CommunityService;

import java.util.List;
import java.util.Map;

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
        // Map<String,List<Category>> categories1 = categoryService.getListAllCategoryId();
        List<Category> categories = categoryService.getListAllCategoryId();
        // List <GeneralBoard> list = communityService.getList(categoryId);
        // System.out.println(list);
        /*debug 돌리면 getList인자값이 null임,
         categoryId 파라미터가 없는 메인페이지에서 카테고리별로 등록된 게시글 5개씩 보여줄때 사용할 parameter를 어떻게 받을까?*/
         /*대충 방법2가지
          * 1. service에 mapper에서 카테고리마다 리스트5개씩 불러오는거 추가,
            2. 프론트에서 임의로 카운트 변수 설정하기. - 이때 불러오는 리스트는 카테고리 id와 무관하고 숫자만 같음*/
        model.addAttribute("ctgId", categories);
        // model.addAttribute("list", list);

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
