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

    @Override
    public PlayGroundBoard getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void EditBoard(PlayGroundBoard playGroundBoard) {
        repository.update(playGroundBoard);
    }

    @Override
    public List<PlayGroundBoard> getBoardMovieList() {
        return repository.findAllPlusMovie();
    }

    @Override
    public int getBettingUserCount(Long id) {
        return repository.countBettingUser(id);
    }

    @Override
    public List<PlayGroundBoard> getBoardTop5() {
        return repository.findBoardTop5();
    }


}
