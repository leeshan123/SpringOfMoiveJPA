package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.Director;
import kr.co.moviespring.web.entity.MovieDirector;

import java.util.List;

public interface MovieDirectorService {
    /*영화코드별 감독리스트*/
    List<Director> getById(Long movieId);

    MovieDirector add(MovieDirector movieDirector);
    int remove(Long id);

    void deleteById(Long movieId);
    
}