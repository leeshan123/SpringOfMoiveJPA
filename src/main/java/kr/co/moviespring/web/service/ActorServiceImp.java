package kr.co.moviespring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.Actor;
import kr.co.moviespring.web.repository.ActorRepository;

@Service
public class ActorServiceImp implements ActorService{

    @Autowired
    ActorRepository repository;

    @Override
    public Long add(Actor actor) {
        repository.save(actor);
        Long actorId = actor.getId(); 
        return actorId;
    }

    @Override
    public Actor getById(Long id) {
        // 일단 null값
        return null;
    }

    @Override
    public int remove(Long id) {
        // 일단 0값
        return 0;
    }

    @Override
    public Actor getByTMDBId(String id) {
        Actor actor = repository.findByTMDBId(id);
        return actor;
    }
    
}
