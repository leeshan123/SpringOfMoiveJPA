package kr.co.moviespring.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.Category;
@Mapper
public interface CategoryRepository {

    List<Category> findAll();

    Category findById(Long categoryId);

    
}
