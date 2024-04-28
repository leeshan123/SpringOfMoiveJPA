package kr.co.moviespring.web.service;

import java.util.List;

import kr.co.moviespring.web.entity.CommunityBoard;
import kr.co.moviespring.web.entity.CommunityBoardView;

public interface CommunityBoardService {

    // 게시글 등록//
    void write(String title, String contents, Long categoryId);
    // 게시글 삭제
    int deleteById(Long id);
    // 게시글 수정
    void editById(Long id, String title, String contents);
    //카테고리별 게시글 목록//
    List<CommunityBoardView> getList(Long categoryId, Integer page, Integer size);
    List<CommunityBoardView> getList(Long categoryId, Integer page, Integer size, String query);
    //게시글 상세//
    CommunityBoard getById(Long id);

    int getCount(Long categoryId);
    int getCount(Long categoryId, String query);

    // 모든 게시물 목록 필요한가? 잘 모르겠음 일단 주석처리 2024-04-14, 일, 21:59  -JOON
//    List<GeneralBoard> getList();

}
