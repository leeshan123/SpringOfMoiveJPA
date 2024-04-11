package kr.co.moviespring.web.service.categoryservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.Category;
import kr.co.moviespring.web.repository.CategoryRepository;
import kr.co.moviespring.web.repository.CommunityRepository;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;
    

    //카테고리별 게시판 목록//

    @Override
    public List<Category> getListAllCategoryId() {
        List<Category> categorys = categoryRepository.findByCategoryId();
        return categorys;
    }
    
    //게시판 카테고리 이름찾기//
    @Override
    public Category getNameByCategoryId(Long categoryId) {
        Category name = categoryRepository.findNameByCategoryId(categoryId);
        return name;
    }
    
}
