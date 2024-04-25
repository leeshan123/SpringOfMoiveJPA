package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.Actor;

public interface ActorService {
    Actor add(Actor actor);
    Actor getById(Long id);
    int remove(Long id);
}