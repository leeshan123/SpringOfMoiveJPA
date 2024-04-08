package kr.co.moviespring.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.Category;
import kr.co.moviespring.web.entity.GeneralBoard;

@Mapper
public interface CommunityRepository {
    // 게시글 등록//
    void saveContents(String title, String contents, Long categoryId);

    List<GeneralBoard> findAll(Long CategoryId);

    GeneralBoard findById(Long id);

    List<Category> findByCategoryId(Long categoryId);

}
