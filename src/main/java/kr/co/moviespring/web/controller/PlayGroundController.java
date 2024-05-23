package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.entity.PlayGroundBoard;
import kr.co.moviespring.web.service.PlayGroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("playground")
public class PlayGroundController {

    @Autowired
    PlayGroundService playGroundService;


    @GetMapping("main")
    public String main(Model model){

        List<PlayGroundBoard> pgbList = playGroundService.getBoardMovieList();
        List<Integer> countList = new ArrayList<>();
        List<Double> percentList = new ArrayList<>();

        if (pgbList == null) {
            System.out.println("pgbList is null");
        } else if (pgbList.isEmpty()) {
            System.out.println("pgbList is empty");
        }

        //배팅한 유저들의 숫자 구하기
        for(int i=0; i<pgbList.size(); i++){

            countList.add(playGroundService.getBettingUserCount(pgbList.get(i).getId()));

        }

        //퍼센트를 구해오기
        for(PlayGroundBoard pgb : pgbList){
            double result = (double)pgb.getLeftBettingPoint()/ (pgb.getLeftBettingPoint()+ pgb.getRightBettingPoint());
            BigDecimal bd = new BigDecimal(Double.toString(result));
            bd = bd.setScale(4, RoundingMode.HALF_UP);

            BigDecimal percentage = bd.multiply(BigDecimal.valueOf(100));
            percentList.add(percentage.doubleValue());
            System.out.println("percentage: "+percentage);
        }








        model.addAttribute("pgbList", pgbList);
        model.addAttribute("countList", countList);
        model.addAttribute("percentList",percentList);



        return "playground/main";
    }

}