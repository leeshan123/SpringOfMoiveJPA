package kr.co.moviespring.web.repository;

import kr.co.moviespring.web.entity.Movie2;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieInsertRepository {

    void DailyBoxOfficeSave(Movie2 movie2);

    //쓸일 없을거같음.
//    void saveAll(Movie2 movie2);

    void saveIfNotMovie(Movie2 movie2);

    //엑셀에서 가져온 누적관객수 데이터 넣기.
    void AduienceAccInsert(Movie2 movie2);



}
