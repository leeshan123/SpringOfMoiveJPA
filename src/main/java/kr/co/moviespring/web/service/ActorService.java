package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.Actor;

public interface ActorService {
    Long add(Actor actor);
    Actor getById(Long id);
    Actor getByTMDBId(String id);
    int remove(Long id);
}
