package kr.co.moviespring.web.repository;

import kr.co.moviespring.web.entity.MovieActorView;
import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.MovieActor;

import java.util.List;

@Mapper
public interface MovieActorRepository {
    int save(MovieActor movieActor);
    int update(MovieActor movieActor);
    void delete(Long movieId);
    /*영화아이디별 배우목록*/
    List<MovieActorView> findAllById(Long movieId);
    boolean checkId(MovieActor movieActor);
}
