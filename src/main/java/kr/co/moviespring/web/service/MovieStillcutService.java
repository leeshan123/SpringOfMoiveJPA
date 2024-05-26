package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.MovieStillcut;

import java.util.List;

public interface MovieStillcutService {
    Long add(MovieStillcut stillcut);

    //영화 아이디별 스틸컷 리스트
    List<MovieStillcut> getById(Long movieId);

    void deleteById(Long movieId);
}
