package kr.co.moviespring.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.Movie;

@Mapper
public interface MovieRepository {

    List<Movie> findAll();

    List<Movie> findAllAfter();

    Long save(Movie movie);

    Movie findById(Long id);
    Movie findByTMDBId(String id);
    Movie findByKobisId(String id);


    // 검색값으로 영화목록 가져오기//
    List<Movie> findAllByName(String query);

}
