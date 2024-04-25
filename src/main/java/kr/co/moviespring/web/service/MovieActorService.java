package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.MovieActor;

public interface MovieActorService {
    MovieActor add(MovieActor movieActor);
    int remove(Long id);
}
