package kr.co.moviespring.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.MovieDirector;

@Mapper
public interface MovieDirectorRepository {
    int save(MovieDirector movieDirector);
    int update(MovieDirector movieDirector);
    int delete(MovieDirector movieDirector);
}
