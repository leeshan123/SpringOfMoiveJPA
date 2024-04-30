package kr.co.moviespring.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.MovieTrailer;

@Mapper
public interface MovieTrailerRepository {
    Long save(MovieTrailer trailer);
}
