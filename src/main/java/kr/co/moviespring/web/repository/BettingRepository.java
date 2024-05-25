package kr.co.moviespring.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.Betting;

@Mapper
public interface BettingRepository {

    List<Betting> findAllById(Long memberId);

    int getCount(Long betId);

    List<Betting> finaAllSuccessUser(Long betId, int choose);

//    void deletegetPoint(Long betId, int choose);
    
}
