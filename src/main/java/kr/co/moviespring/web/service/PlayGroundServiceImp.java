package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.PlayGroundBoard;
import kr.co.moviespring.web.repository.PlayGroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayGroundServiceImp implements PlayGroundService{

    @Autowired
    PlayGroundRepository repository;


    @Override
    public void RegBoard(PlayGroundBoard playGroundBoard) {
        repository.save(playGroundBoard);

    }

    @Override
    public List<PlayGroundBoard> getBoardList() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.delete(id);
    }
}
