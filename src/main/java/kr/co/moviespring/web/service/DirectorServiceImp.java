package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.Director;
import kr.co.moviespring.web.repository.DirectorRepository;

import java.util.List;

@Service
public class DirectorServiceImp implements DirectorService{

    @Autowired
    DirectorRepository repository;

    @Override
    public Long add(Director director) {
        repository.save(director);
        Long directorId = director.getId(); 
        return directorId;
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

    @Override
    public List<Director> getListByName(String query) {

        List<Director> list;
        if (query == null || query.trim().isEmpty()) {
            list = null;
        } else {
            list = repository.findAllByName(query);
        }

        return list;
    }

    @Override
    public Director getByTMDBId(String id) {
        Director director = repository.findByTMDBId(id);
        return director;
    }
    
}
