package kr.co.moviespring.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moviespring.web.entity.Category;
import kr.co.moviespring.web.entity.CommunityBoardCommentsView;
import kr.co.moviespring.web.entity.CommunityBoardView;
import kr.co.moviespring.web.service.CategoryService;
import kr.co.moviespring.web.service.CommunityBoardCommentsService;
import kr.co.moviespring.web.service.CommunityBoardService;

@Controller("AdminCommunityBoardController") //이름의 중복을 피하기 위해 ioc컨테이너에 담기는 이름을 직접 작성해줄수있다
@RequestMapping("admin/community-board")
public class CommunityBoardController {

    @Autowired
    CommunityBoardService communityBoardService;

    @Autowired
    CommunityBoardCommentsService commentsService;

    @Autowired
    CategoryService cService;

    @GetMapping("boardlist")
    public String boardList(
        @RequestParam(name = "p", required = false, defaultValue = "1") Integer page
        , Model model
    ){
        int cnt = 0;

        List<CommunityBoardView> list = communityBoardService.getList(null, page, 10);
        cnt = communityBoardService.getCount(null);
        List<Category> clist = cService.getList();
        
        model.addAttribute("list", list);
        model.addAttribute("clist", clist);
        model.addAttribute("count", cnt);
        
        return "admin/community-board/boardlist";
    }

    @GetMapping("commentlist")
    public String commentList(
        @RequestParam(name = "p", required = false, defaultValue = "1") Integer page
        , Model model
    ){
        int cnt = 0;

        List<CommunityBoardCommentsView> list = commentsService.getList(page, 10);
        cnt = commentsService.getCount();
        
        List<Category> clist = cService.getList();
        
        model.addAttribute("list", list);
        model.addAttribute("clist", clist);
        model.addAttribute("count", cnt);
        
        return "admin/community-board/commentlist";
    }
}
