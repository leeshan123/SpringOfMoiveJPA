package kr.co.moviespring.web.service.categoryservice;

import java.util.List;

import kr.co.moviespring.web.entity.Category;

public interface CategoryService {
    
    //게시글 카테고리 목록//
    List<Category> getList();

    Category getById(Long categoryId);
}
