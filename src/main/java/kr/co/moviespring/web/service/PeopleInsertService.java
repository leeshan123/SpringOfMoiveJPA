package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.MoviePeople;
import kr.co.moviespring.web.entity.People;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PeopleInsertService {

    List<People> getActorList(String key);

    void insertPeopleData(List<People> actorList);

    List<MoviePeople> getPeopleFilmoList(String key,String peopleCd) throws IOException;

    void insertMoviePeople(List<MoviePeople> filmoList);

    String getpeopleCd(int x);
    


}
