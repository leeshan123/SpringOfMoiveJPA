package kr.co.moviespring.web.movieapi;

import kr.co.moviespring.web.entity.Movie2;
import kr.co.moviespring.web.entity.MoviePeople;
import kr.co.moviespring.web.entity.People;
import kr.co.moviespring.web.service.PeopleInsertServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Service
public class PeopleDataUpdate {

    @Autowired
    PeopleInsertServiceImp peopleInsertService;

    public List<People> getActorList(String key){
        System.out.println("getActorList메서드 시작");
        return peopleInsertService.getActorList(key);
    }

    public void peopleInsert(List<People> peopleList){
        System.out.println("movieListInsert 메서드 시작");
        peopleInsertService.insertPeopleData(peopleList);
    }

    public List<MoviePeople> getFilmoList(String key,String peopleCd) throws IOException {
        System.out.println("filmoInsert 메서드 시작");
        return peopleInsertService.getPeopleFilmoList(key,peopleCd);
    }

    public  void filmoInsertServie(List<MoviePeople> filmoList){
        System.out.println("filmoInsertServie 메서드 시작");
        peopleInsertService.insertMoviePeople(filmoList);
    }

    public String getPeopleCd(int x){
        System.out.println("getPeopleCd메서드 시작");
        String peopleCd = peopleInsertService.getpeopleCd(x);

        return peopleCd;
    }
}
