package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.Actor;

import java.util.List;

public interface ActorService {
    Long add(Actor actor);
    Actor getById(Long id);
    Actor getByTMDBId(String id);
    int remove(Long id);

    List<Actor> getListByName(String query);
}
