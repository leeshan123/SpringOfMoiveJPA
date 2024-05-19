package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.entity.PlayGroundBoard;
import kr.co.moviespring.web.service.PlayGroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("playground")
public class PlayGroundController {

    @Autowired
    PlayGroundService playGroundService;

    @GetMapping("main")
    public String main(Model model){

        List<PlayGroundBoard> pgbList = playGroundService.getBoardMovieList();

        if (pgbList == null) {
            System.out.println("pgbList is null");
        } else if (pgbList.isEmpty()) {
            System.out.println("pgbList is empty");
        } else {
            for (PlayGroundBoard list : pgbList) {
                System.out.println(list.getTitle());
                System.out.println(list.getPosterUrl());
            }

        }


        model.addAttribute("pgbList", pgbList);



        return "playground/main";
    }

}