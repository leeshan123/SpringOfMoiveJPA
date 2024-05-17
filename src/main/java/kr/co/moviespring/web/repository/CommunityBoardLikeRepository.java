package kr.co.moviespring.web.repository;

import kr.co.moviespring.web.entity.CommunityBoardView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityBoardLikeRepository {

    // 좋아요 , 싫어요 등록
    void save(Long boardId, Long memberId, int status);
    //좋아요/싫어요/총 개수
    Integer getCount(Long boardId, int status);
}
