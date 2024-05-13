package kr.co.moviespring.web.controller.admin;

import kr.co.moviespring.web.service.admin.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller("StatisticController")
@RequestMapping("admin/statistic")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping("main")
    public String main(Model model) {

        int total = statisticService.getTotalMemberCount();
        model.addAttribute("total",total);

        int today = statisticService.getTodayRegMemberCount();
        model.addAttribute("today",today);

        List<Integer> ageRangeList = statisticService.getMemberAgeRangCount();
        model.addAttribute("ageRangeList",ageRangeList);

        List<Double> persentList = new ArrayList();
        for(int ageRangInt : ageRangeList){
            double result = (double)ageRangInt/total;
            double temp = Math.round(result * 1000) / 1000.0;
            persentList.add(temp*100);
        }



        model.addAttribute("persentList",persentList);



        return "admin/statistic/main";
    }
}
