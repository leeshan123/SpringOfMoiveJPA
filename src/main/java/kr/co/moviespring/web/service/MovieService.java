package kr.co.moviespring.web.service;

import java.util.List;

import kr.co.moviespring.web.entity.Movie;

public interface MovieService {
    List <Movie> getList();
    List <Movie> getListByName(String query);
    Movie getById(Long id);
    Movie getByTMDBId(String id);
    Movie getByKobisId(String id);
    Long saveMovie(Movie movie);
}
