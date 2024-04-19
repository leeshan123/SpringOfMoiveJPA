package kr.co.moviespring.web.service.communityService;

import java.util.List;

import kr.co.moviespring.web.entity.CommunityBoard;

public interface CommunityBoardService {

    // 게시글 등록//
    void write(String title, String contents, Long categoryId);

    //카테고리별 게시글 목록//
    List<CommunityBoard> getList(Long categoryId, int size);

    //게시글 상세//
    CommunityBoard getById(Long id);

    // 모든 게시물 목록 필요한가? 잘 모르겠음 일단 주석처리 2024-04-14, 일, 21:59  -JOON
//    List<GeneralBoard> getList();

}
