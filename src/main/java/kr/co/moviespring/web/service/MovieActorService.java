package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.MovieActor;
import kr.co.moviespring.web.entity.MovieActorView;

import java.util.List;

public interface MovieActorService {
    MovieActor add(MovieActor movieActor);
    int remove(Long id);

    /*영화아이디별 배우목록*/
    List<MovieActorView> getById(Long movieId);
    boolean checkMovieActor(MovieActor movieActor);
}
