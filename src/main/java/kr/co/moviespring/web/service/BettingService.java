package kr.co.moviespring.web.service;

import java.util.List;

import kr.co.moviespring.web.entity.Betting;

public interface BettingService {

    List<Betting> getList(Long memberId);

    int getCount(Long betId);
    
}
