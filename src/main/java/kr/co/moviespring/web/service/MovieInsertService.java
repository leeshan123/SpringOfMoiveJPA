package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.Movie2;

import java.util.List;

public interface MovieInsertService {

    void saveMovie(List<Movie2> movieList);

    List<Movie2> getList();
}
