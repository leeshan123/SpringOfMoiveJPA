package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.MovieDirector;

public interface MovieDirectorService {
    MovieDirector add(MovieDirector movieDirector);
    int remove(Long id);
    
}