package kr.co.moviespring.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("StatisticController")
@RequestMapping("admin/statistic")
public class StatisticController {

    @GetMapping("main")
    public String main() {

        return "admin/statistic/main";
    }
}
