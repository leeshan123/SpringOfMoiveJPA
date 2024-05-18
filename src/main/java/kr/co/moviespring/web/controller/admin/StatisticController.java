package kr.co.moviespring.web.controller.admin;

import kr.co.moviespring.web.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

        List<Double> percentList = new ArrayList();
        for(int ageRangInt : ageRangeList){
            double result = (double)ageRangInt/total;
            BigDecimal bd = new BigDecimal(Double.toString(result));
            bd = bd.setScale(4, RoundingMode.HALF_UP);

            BigDecimal percentage = bd.multiply(BigDecimal.valueOf(100));
            percentList.add(percentage.doubleValue());
        }

        for(double dlist : percentList){
            System.out.println(dlist);
        }



        model.addAttribute("percentList",percentList);



        return "admin/statistic/main";
    }
}
