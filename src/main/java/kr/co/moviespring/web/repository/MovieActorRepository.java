package kr.co.moviespring.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.MovieActor;

@Mapper
public interface MovieActorRepository {
    int save(MovieActor movieActor);
    int update(MovieActor movieActor);
    int delete(MovieActor movieActor);
}
