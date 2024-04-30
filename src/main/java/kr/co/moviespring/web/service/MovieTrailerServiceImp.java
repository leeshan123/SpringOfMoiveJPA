package kr.co.moviespring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.MovieTrailer;
import kr.co.moviespring.web.repository.MovieTrailerRepository;

import java.util.List;

@Service
public class MovieTrailerServiceImp implements MovieTrailerService{

    @Autowired
    MovieTrailerRepository repository;

    @Override
    public Long add(MovieTrailer trailer) {
        Long result = repository.save(trailer);
        return result;
    }
    //영화 아이디별 예고편 가져오기
    @Override
    public List<MovieTrailer> getById(Long movieId) {
        List<MovieTrailer> list = repository.findAllById(movieId);
        return list;
    }

}
