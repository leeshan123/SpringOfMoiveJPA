package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.PlayGroundBoard;

import java.util.List;

public interface PlayGroundService {
    void RegBoard(PlayGroundBoard playGroundBoard);

    List<PlayGroundBoard> getBoardList();

    void deleteById(Long id);
}
