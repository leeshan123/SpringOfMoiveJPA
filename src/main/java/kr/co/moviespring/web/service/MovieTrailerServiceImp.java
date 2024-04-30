package kr.co.moviespring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.MovieTrailer;
import kr.co.moviespring.web.repository.MovieTrailerRepository;

@Service
public class MovieTrailerServiceImp implements MovieTrailerService{

    @Autowired
    MovieTrailerRepository repository;

    @Override
    public Long add(MovieTrailer trailer) {
        Long result = repository.save(trailer);
        return result;
    }

}
