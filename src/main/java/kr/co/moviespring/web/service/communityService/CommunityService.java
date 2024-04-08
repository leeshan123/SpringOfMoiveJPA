package kr.co.moviespring.web.service.communityService;

import java.util.List;

import kr.co.moviespring.web.entity.Category;
import kr.co.moviespring.web.entity.GeneralBoard;

public interface CommunityService {
    // 게시글 등록//
    void write(String title, String contents, Long categoryId);

    List<GeneralBoard> getList(Long categoryId);

    GeneralBoard getById(Long id);

    List<Category> getListByCategoryId(Long categoryId);

}
