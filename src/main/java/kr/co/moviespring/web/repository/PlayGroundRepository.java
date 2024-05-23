package kr.co.moviespring.web.repository;

import kr.co.moviespring.web.entity.Betting;
import kr.co.moviespring.web.entity.PlayGroundBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlayGroundRepository {

    void save(PlayGroundBoard playGroundBoard);

    List<PlayGroundBoard> findAll();

    void delete(Long id);

    PlayGroundBoard findById(Long id);

    void update(PlayGroundBoard playGroundBoard);

    List<PlayGroundBoard> findAllPlusMovie();

    void saveBetting(Betting betting);

    int countBettingUser(Long bettingBoardId);

    List<PlayGroundBoard> findBoardTop5();
}
