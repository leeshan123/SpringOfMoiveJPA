package kr.co.moviespring.web.service.admin;

import kr.co.moviespring.web.repository.admin.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImp implements StatisticService{

    @Autowired
    StatisticRepository repository;
    @Override
    public int getTotalMemberCount() {

        return repository.CountAllMember();
    }

    @Override
    public int getTodayRegMemberCount() {
        return repository.CountTodayRegMember();
    }

    @Override
    public List<Integer> getMemberAgeRangCount() {
        List<Integer> memberAgeRangeList = new ArrayList<Integer>();

        for(int i = 0; i<4;i++){

            memberAgeRangeList.add(repository.CountMemberAgeRange(i));
        }


        return memberAgeRangeList;
    }
}
