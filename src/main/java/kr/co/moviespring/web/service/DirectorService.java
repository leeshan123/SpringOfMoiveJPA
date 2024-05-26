package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.Director;

import java.util.List;

public interface DirectorService {
    Long add(Director director);
    Director getById(Long id);
    Director getByTMDBId(String id);
    int remove(Long id);

    List<Director> getListByName(String query);
}
