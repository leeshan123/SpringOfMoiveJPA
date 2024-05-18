package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.CommunityBoardLike;

public interface CommunityBoardLikeService {

    //좋아요 개수
//    int getLikeCount(Long boardId);
    //싫어요 개수
//    int getDisLikeCount(Long boardId);
    //좋아요/싫어요 총 개수
    Integer getCount(Long boardId, int status); // 2번째 인자값으로 1을 넣으면 좋아요 개수 , -1은 싫어요 개수
    //게시글에 대한 현재 로그인한 멤버의 좋아요 여부
    Integer getStatusById(Long boardId, Long memberId); // 리턴값 1 = 좋아요 , 2 = 싫어요 , 0 = 투표하지 않음

    int like(Long boardId, Long userId, int status);
    int disLike(Long boardId, Long userId, int status);

}
