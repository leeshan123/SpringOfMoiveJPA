package kr.co.moviespring.web.repository;

import kr.co.moviespring.web.entity.MoviePeople;
import kr.co.moviespring.web.entity.People;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PeopleInsertRepository {

    void savePeople(People people);

   String getPeopleCd(int x);

    void saveMoviePeople(MoviePeople moviePeople);
}
