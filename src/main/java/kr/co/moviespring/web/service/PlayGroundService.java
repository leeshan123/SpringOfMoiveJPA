package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.PlayGroundBoard;

import java.util.List;

public interface PlayGroundService {
    void RegBoard(PlayGroundBoard playGroundBoard);

    List<PlayGroundBoard> getBoardList();

    void deleteById(Long id);

    PlayGroundBoard getById(Long id);

    void EditBoard(PlayGroundBoard playGroundBoard);

    List<PlayGroundBoard> getBoardMovieList();

    int getBettingUserCount(Long id);

    int getCount(Long memberId);

    List<PlayGroundBoard> getBoardTop5();
}
