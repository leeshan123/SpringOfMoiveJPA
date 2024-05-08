package kr.co.moviespring.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.service.MovieService;

@Controller("AdminMovieManagementController") //이름의 중복을 피하기 위해 ioc컨테이너에 담기는 이름을 직접 작성해줄수있다
@RequestMapping("admin/movie-management")
public class MovieManagementController {

    @Autowired
    MovieService movieService;
    
    @GetMapping("list")
    public String list(
        Model model
    ) {
        int page = 1;
        List<Movie> list = movieService.getList(page);
        model.addAttribute("list", list);
        return "admin/movie-management/list"; 
    }

    
    @GetMapping("reg")
    public String reg() {
    
        return "admin/movie-management/reg"; 
    }
}
