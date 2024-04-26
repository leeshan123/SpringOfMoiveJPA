package kr.co.moviespring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.Director;
import kr.co.moviespring.web.repository.DirectorRepository;

@Service
public class DirectorServiceImp implements DirectorService{

    @Autowired
    DirectorRepository repository;

    @Override
    public Director add(Director director) {
        repository.save(director);
        return director;
    }

    @Override
    public Director getById(Long id) {
        // 일단 null값
        return null;
    }

    @Override
    public int remove(Long id) {
        // 일단 0값
        return 0;
    }
    
}
