package kr.co.moviespring.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.CommunityBoard;

@Mapper
public interface CommunityBoardRepository {
    // 게시글 등록//
    void saveContents(String title, String contents, Long categoryId);

    // 카테고리별 게시글 목록
    List<CommunityBoard> findAll(Long categoryId, int size);

    CommunityBoard findById(Long id);

//    List<GeneralBoard> findAll();

}
