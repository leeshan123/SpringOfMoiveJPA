package kr.co.moviespring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.repository.MovieRepository;

@Service
public class MovieServiceImp implements MovieService {
    @Autowired
    MovieRepository repository;
    @Override
    public List<Movie> getList() {
        
        List <Movie> list = repository.findAll();
        return list;
    }

    @Override
    public Movie getById(Long id) {

        Movie movie = repository.findById(id);
        return movie;
    }

    @Override
    public void saveMovie(Movie movie) {
        repository.save(movie);
    }

    
}