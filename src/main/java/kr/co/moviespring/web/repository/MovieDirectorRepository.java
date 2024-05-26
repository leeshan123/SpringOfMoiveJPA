package kr.co.moviespring.web.repository;

import kr.co.moviespring.web.entity.Director;
import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.MovieDirector;

import java.util.List;

@Mapper
public interface MovieDirectorRepository {
    int save(MovieDirector movieDirector);
    int update(MovieDirector movieDirector);
    void delete(Long movieId);

    /*영화코드별 감독리스트*/
    List<Director> findAllById(Long movieId);
}
