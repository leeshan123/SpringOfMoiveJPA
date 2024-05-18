package kr.co.moviespring.web.controller.admin;

import kr.co.moviespring.web.config.security.CustomUserDetails;
import kr.co.moviespring.web.entity.EventPage;
import kr.co.moviespring.web.entity.PlayGroundBoard;
import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.service.MovieService;
import kr.co.moviespring.web.service.PlayGroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller("AdminPlayGroundController") //이름의 중복을 피하기 위해 ioc컨테이너에 담기는 이름을 직접 작성해줄수있다
@RequestMapping("admin/playground")
public class PlayGroundController {

    @Autowired
    MovieService movieService;

    @Autowired
    PlayGroundService playGroundService;



    // 처음 메인 페이지
    @GetMapping("main")
    public String main(Model model) {
        List<PlayGroundBoard> pgbList = playGroundService.getBoardList();
        model.addAttribute("pgbList", pgbList);



        return "admin/playground/main";
    }



    //영화 가져오는 API
    @GetMapping("/search")
    @ResponseBody
    public List<Movie> search(@RequestParam(name="movie_query", required = false)String movieQuery, Model model){
        List<Movie> mList = movieService.getListByName(movieQuery);



        return mList;
    }
    //처음 등록 페이지
    @GetMapping("/reg")
    public String reg() {

        return "admin/playground/reg";
    }

    //등록
    @PostMapping("/reg")
    public String reg(
            @RequestParam("title") String title,
            @RequestParam("betting_title") String bettingTitle,
            @RequestParam("movie_query") String moiveQuery,
            @RequestParam("vote_end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date voteEndDate,
            @RequestParam("dead_line_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date deadLineDate,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){




        String MovieId = moiveQuery.substring(moiveQuery.lastIndexOf("(")+1,moiveQuery.lastIndexOf(")"));


        // BettingBoard 객체 생성
        PlayGroundBoard playGroundBoard = PlayGroundBoard.builder()
                .title(title)
                .movieId(MovieId)
                .bettingTitle(bettingTitle)
                .voteEndDate(voteEndDate)
                .deadLineDate(deadLineDate)
                .adminId(userDetails.getId())
                .build();



        playGroundService.RegBoard(playGroundBoard);






        return "redirect:/admin/playground/main";
    }

    //수정 페이지

    //삭제 페이지
    @GetMapping("delete")
    public String delete(
            @RequestParam("id") Long id
    ){

        playGroundService.deleteById(id);
        return "redirect:/admin/playground/main";
    }
}
