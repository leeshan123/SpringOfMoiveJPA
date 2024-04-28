package kr.co.moviespring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.MovieStillcut;
import kr.co.moviespring.web.repository.MovieStillcutRepository;

@Service
public class MovieStillcutServiceImp implements MovieStillcutService{

    @Autowired
    MovieStillcutRepository repository;

    @Override
    public Long add(MovieStillcut stillcut) {
        Long result = repository.save(stillcut);
        return result;
    }
    
}
