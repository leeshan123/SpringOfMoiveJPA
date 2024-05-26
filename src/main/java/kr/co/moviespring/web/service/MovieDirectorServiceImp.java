package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.MovieDirector;
import kr.co.moviespring.web.repository.MovieDirectorRepository;

import java.util.List;

@Service
public class MovieDirectorServiceImp implements MovieDirectorService{

    @Autowired
    MovieDirectorRepository repository;

    /*영화코드별 감독리스트*/
    @Override
    public List<Director> getById(Long movieId) {
        List<Director> list = repository.findAllById(movieId);
        return list;
    }

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

    @Override
    public void deleteById(Long movieId) {
        repository.delete(movieId);
    }
    
}
