package kr.co.moviespring.web.movieapi;

import kr.co.moviespring.web.entity.Movie2;
import kr.co.moviespring.web.entity.People;
import kr.co.moviespring.web.service.PeopleInsertServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PeopleDataUpdate {

    @Autowired
    PeopleInsertServiceImp actorInsertService;

    public List<People> getActorList(String key){
        System.out.println("getActorList메서드 시작");
        return actorInsertService.getActorList(key);
    }

    public void peopleInsert(List<People> peopleList){
        System.out.println("movieListInsert 메서드 시작");
        actorInsertService.insertPeopleData(peopleList);
    }

}
