package kr.co.moviespring.web.service.movieService;

import java.util.List;

import kr.co.moviespring.web.entity.Movie;

public interface MovieService {
    List <Movie> getList();
    Movie getById(Long id);
    void saveMovie(Movie movie);
}
