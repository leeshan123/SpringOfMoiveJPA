package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.People;

import java.util.List;

public interface PeopleInsertService {

    List<People> getActorList(String key);

    void insertPeopleData(List<People> actorList);

}
