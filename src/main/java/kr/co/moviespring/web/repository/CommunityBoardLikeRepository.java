package kr.co.moviespring.web.repository;

import kr.co.moviespring.web.entity.CommunityBoardView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityBoardLikeRepository {

    // 좋아요 , 싫어요 등록
    void save(Long id, Long memberId, int status, String type);
    //좋아요/싫어요/총 개수
    Integer getCount(Long boardId, int status);
    //멤버의 게시글 좋아요 여부
    Integer getStatusById(Long boardId, Long memberId);
}
