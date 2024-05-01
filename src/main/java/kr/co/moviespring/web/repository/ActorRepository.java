package kr.co.moviespring.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.Actor;

import java.util.List;

@Mapper
public interface ActorRepository {
    
    Long save(Actor actor);
    int update(Actor actor);
    int delete(Actor actor);
    Actor findByTMDBId(String id);

    List<Actor> findAllByName(String query);
}
