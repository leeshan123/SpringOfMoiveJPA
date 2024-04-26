package kr.co.moviespring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.MovieDirector;
import kr.co.moviespring.web.repository.MovieDirectorRepository;

@Service
public class MovieDirectorServiceImp implements MovieDirectorService{

    @Autowired
    MovieDirectorRepository repository;

    @Override
    public MovieDirector add(MovieDirector movieDirector) {
        repository.save(movieDirector);
        return movieDirector;
    }

    @Override
    public int remove(Long id) {
        // 일단 0값
        return 0;
    }
    
}
