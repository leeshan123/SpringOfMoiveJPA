package kr.co.moviespring.web.controller.admin;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("AdminPlayGroundController") //이름의 중복을 피하기 위해 ioc컨테이너에 담기는 이름을 직접 작성해줄수있다
@RequestMapping("admin/playground")
public class PlayGroundController {

    @Autowired
    MovieService movieService;


    @GetMapping("main")
    public String main() {

        return "admin/playground/main";
    }

    @GetMapping("reg")
    public String reg() {

        return "admin/playground/reg";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name="movie_query", required = false)String movieQuery, Model model){
        List<Movie> mList = movieService.getListByName(movieQuery);

        model.addAttribute("mList", mList);

        return "admin/playground/search";
    }


}
