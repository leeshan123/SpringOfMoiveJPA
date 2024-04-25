package kr.co.moviespring.web.repository;

import java.util.List;

import kr.co.moviespring.web.entity.CommunityBoardView;
import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.CommunityBoard;

@Mapper
public interface CommunityBoardRepository {
    // 게시글 등록//
    void saveContents(String title, String contents, Long categoryId);

    // 게시글 삭제//
    int delete(Long id);

    // 게시글 수정
    void edit(Long id, String title, String contents);

    // 카테고리별 게시글 목록
    List<CommunityBoardView> findAll(Long categoryId, int size);

    //게시글 디테일페이지
    CommunityBoard findById(Long id);


//    List<GeneralBoard> findAll();

}
