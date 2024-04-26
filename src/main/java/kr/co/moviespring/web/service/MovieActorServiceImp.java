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
        repository.save(movieActor);
        return movieActor;
    }

    @Override
    public int remove(Long id) {
        // 일단 0값
        return 0;
    }
    
}
