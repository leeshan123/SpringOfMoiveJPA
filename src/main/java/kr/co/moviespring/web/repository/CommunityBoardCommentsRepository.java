package kr.co.moviespring.web.repository;

import kr.co.moviespring.web.entity.CommunityBoardComments;
import kr.co.moviespring.web.entity.CommunityBoardCommentsView;
import kr.co.moviespring.web.entity.CommunityBoardView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityBoardCommentsRepository {
    //댓글 저장
    void saveContent(Long boardId, Long memberId, String content);
    //댓글 목록 불러오기
    List<CommunityBoardCommentsView> findAllById(Long boardId, Long memberId);
    List<CommunityBoardCommentsView> findAllByMemberId(Long id);
    List<CommunityBoardCommentsView> findAll(int offset, int size);
    int getCount();
}
