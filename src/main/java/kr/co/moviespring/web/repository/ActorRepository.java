package kr.co.moviespring.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.Actor;

@Mapper
public interface ActorRepository {
    
    Long save(Actor actor);
    int update(Actor actor);
    int delete(Actor actor);
}
