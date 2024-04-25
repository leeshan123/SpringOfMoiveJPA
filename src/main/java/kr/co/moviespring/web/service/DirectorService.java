package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.Director;

public interface DirectorService {
    Director add(Director director);
    Director getById(Long id);
    int remove(Long id);
}
