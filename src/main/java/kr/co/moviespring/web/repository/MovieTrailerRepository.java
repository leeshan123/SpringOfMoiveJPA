package kr.co.moviespring.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.MovieTrailer;

import java.util.List;

@Mapper
public interface MovieTrailerRepository {
    Long save(MovieTrailer trailer);

    //영화 아이디별 예고편 가져오기
    List<MovieTrailer> findAllById(Long movieId);

    void delete(Long movieId);
}
