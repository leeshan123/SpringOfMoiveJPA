package kr.co.moviespring.web.repository;

import kr.co.moviespring.web.entity.Movie2;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieInsertRepository {

    void DailyBoxOfficeSave(Movie2 movie2);

    //쓸일 없을거같음.
//    void saveAll(Movie2 movie2);

    void saveIfNotMovie(Movie2 movie2);



}
