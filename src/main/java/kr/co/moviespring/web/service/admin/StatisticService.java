package kr.co.moviespring.web.service.admin;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface StatisticService {


    int getTotalMemberCount();

    int getTodayRegMemberCount();

    List<Integer> getMemberAgeRangCount();
}
