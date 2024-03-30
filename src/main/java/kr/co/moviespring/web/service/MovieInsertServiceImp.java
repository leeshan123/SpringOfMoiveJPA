package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.Movie2;
import kr.co.moviespring.web.repository.MovieInsertRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieInsertServiceImp implements MovieInsertService{

    @Autowired
    MovieInsertRepository repository;


    @Override
    public void saveMovie(List<Movie2> movieList) {
        for(int i=0;i<movieList.size();i++){
        repository.saveMovie(movieList.get(i));
        }
    }

    @Override
    public List<Movie2> getList() {
        List <Movie2> list = repository.findAll();
        return list;
    }
}
