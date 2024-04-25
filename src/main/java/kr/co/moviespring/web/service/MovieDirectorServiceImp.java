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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public int remove(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
    
}
