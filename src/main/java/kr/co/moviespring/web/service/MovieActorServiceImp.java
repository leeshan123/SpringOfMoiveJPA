package kr.co.moviespring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.MovieActor;
import kr.co.moviespring.web.repository.MovieActorRepository;

@Service
public class MovieActorServiceImp implements MovieActorService{

    @Autowired
    MovieActorRepository repository;

    @Override
    public MovieActor add(MovieActor movieActor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public int remove(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }
    
}
