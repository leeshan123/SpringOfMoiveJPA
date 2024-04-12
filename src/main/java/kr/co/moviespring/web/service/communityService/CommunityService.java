package kr.co.moviespring.web.service.communityService;

import java.util.List;

import kr.co.moviespring.web.entity.GeneralBoard;

public interface CommunityService {
    // 게시글 등록//
    void write(String title, String contents, Long categoryId);

    //게시글 목록//
    List<GeneralBoard> getList(Long categoryId);

    //게시글 상세//
    GeneralBoard getById(Long id);

    List<GeneralBoard> getList();

    List<GeneralBoard> getList1();

    List<GeneralBoard> getList2();

    List<GeneralBoard> getList3();

    List<GeneralBoard> getList4();

}
