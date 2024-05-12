package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.CommunityBoardComments;
import kr.co.moviespring.web.entity.CommunityBoardCommentsView;
import kr.co.moviespring.web.entity.Director;

import java.util.List;

public interface CommunityBoardCommentsService {
    //커뮤니티 게시글 댓글달기
    void write(Long boardId, Long memberId, String content);

    List<CommunityBoardCommentsView> getListById(Long boardId);

    List<CommunityBoardCommentsView> getListByMemberId(Long id);

    List<CommunityBoardCommentsView> getList(Integer page, int size);

    int getCount();
}
