package kr.co.moviespring.web.api;

import kr.co.moviespring.web.entity.Category;
import kr.co.moviespring.web.entity.CommunityBoardView;
import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.service.CategoryService;
import kr.co.moviespring.web.service.CommunityBoardService;
import kr.co.moviespring.web.service.TwoWeeksMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("ApiCommunityBoardController")
@RequestMapping("api/community-boards")
public class CommunityBoardController {

    @Autowired
    CommunityBoardService communityBoardService;
    @Autowired
    CategoryService categoryService;

    // API 응답에 필요한 데이터를 담은 DTO 클래스 정의
    public static class BoardResponse {
        private List<CommunityBoardView> list;
        private int count;
        private Category category;
        private List<Category> categoryList;

        public BoardResponse(List<CommunityBoardView> list, int count, Category category, List<Category> categoryList) {
            this.list = list;
            this.count = count;
            this.category = category;
            this.categoryList = categoryList;
        }

        // 게터 메서드들

        public List<CommunityBoardView> getList() {
            return list;
        }

        public int getCount() {
            return count;
        }

        public Category getCategory() {
            return category;
        }

        public List<Category> getCategoryList() {
            return categoryList;
        }
    }

    //게시글 목록 요청//
    @GetMapping("{category}")
    @CrossOrigin(origins = "http://localhost")
    public BoardResponse list(@RequestParam(name = "c",required = false) String categoryName,
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
        return new BoardResponse(list, count, category, categories);
    }


}
