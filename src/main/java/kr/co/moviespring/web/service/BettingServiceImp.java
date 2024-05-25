package kr.co.moviespring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.Betting;
import kr.co.moviespring.web.repository.BettingRepository;

@Service
public class BettingServiceImp implements BettingService{

    @Autowired
    BettingRepository repository;

    @Override
    public List<Betting> getList(Long memberId) {
        List<Betting> list = repository.findAllById(memberId);
        return list;
    }

    @Override
    public int getCount(Long betId) {
        int num = repository.getCount(betId);
        return num;
    }
    
}
