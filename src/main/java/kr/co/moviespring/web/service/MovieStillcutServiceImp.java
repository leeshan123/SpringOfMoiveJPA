package kr.co.moviespring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.MovieStillcut;
import kr.co.moviespring.web.repository.MovieStillcutRepository;

import java.util.List;

@Service
public class MovieStillcutServiceImp implements MovieStillcutService{

    @Autowired
    MovieStillcutRepository repository;

    @Override
    public Long add(MovieStillcut stillcut) {
        Long result = repository.save(stillcut);
        return result;
    }

    //영화 아이디별 스틸컷 리스트
    @Override
    public List<MovieStillcut> getById(Long movieId) {
        List<MovieStillcut> list = repository.findAllById(movieId);
        return list;
    }

    @Override
    public void deleteById(Long movieId) {
        repository.delete(movieId);
    }

}
