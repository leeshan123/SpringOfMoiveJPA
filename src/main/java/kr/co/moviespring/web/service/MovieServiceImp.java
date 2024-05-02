package kr.co.moviespring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.repository.MovieRepository;

@Service
public class MovieServiceImp implements MovieService {

    // 모든 목록 가져오기//
    @Autowired
    MovieRepository repository;

    @Override
    public List<Movie> getList() {
        List<Movie> list = repository.findAll();
        return list;
    }
    // 검색값으로 영화목록 가져오기//
    @Override
    public List<Movie> getListByName(String query) {

        List<Movie> list;
        if (query == null || query.trim().isEmpty()) {
            list = null;
        } else {
            list = repository.findAllByName(query);
        }



        return list;
    }

    // 요청 id값으로 상세정보가져오기//
    @Override
    public Movie getById(Long id) {

        Movie movie = repository.findById(id);
        return movie;
    }

    // 저장하고 생성된 영화 ID를 받아와서 리턴 //
    @Override
    public Long saveMovie(Movie movie) {
        repository.save(movie);
        Long movieId = movie.getId();
        return movieId;
    }

    @Override
    public Movie getByTMDBId(String id) {
        Movie movie = repository.findByTMDBId(id);
        return movie;
    }

}