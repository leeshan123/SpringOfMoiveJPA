package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.CommunityBoardLike;

public interface CommunityBoardLikeService {

    int like(Long boardId, Long userId, int status);
    int disLike(Long boardId, Long userId, int status);

}
