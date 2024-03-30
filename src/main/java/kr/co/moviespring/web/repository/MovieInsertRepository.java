package kr.co.moviespring.web.repository;

import kr.co.moviespring.web.entity.Movie2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieInsertRepository {
    List<Movie2> findAll();

    void saveMovie(Movie2 movie2);

}
