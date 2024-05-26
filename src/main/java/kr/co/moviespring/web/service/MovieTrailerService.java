package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.MovieTrailer;

import java.util.List;

public interface MovieTrailerService {
    Long add(MovieTrailer stillcut);

    //영화 아이디별 예고편 가져오기
    List<MovieTrailer> getById(Long movieId);

    void deleteById(Long movieId);
}
