package kr.co.moviespring.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.moviespring.web.entity.Actor;
import kr.co.moviespring.web.entity.Director;
import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.Movie3;
import kr.co.moviespring.web.entity.MovieActor;
import kr.co.moviespring.web.entity.MovieDirector;
import kr.co.moviespring.web.movieapi.KobisMovieAPI;
import kr.co.moviespring.web.movieapi.TMDBMovieAPI;
import kr.co.moviespring.web.movieapi.dto.kobis.KobisMovieInfo;
import kr.co.moviespring.web.movieapi.dto.tmdb.TMDBMovieDetail;
import kr.co.moviespring.web.movieapi.dto.tmdb.TMDBPersonDetails;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Cast;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Crew;
import kr.co.moviespring.web.service.MovieInsertService;
import kr.co.moviespring.web.service.MovieService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieInsertService serviceTest;

    @GetMapping("index")
    public String index(Model model) {
        List<Movie> list = movieService.getList();
        model.addAttribute("list", list);

        return "index";
    }

    @GetMapping("test")
    public String test() throws IOException, ParseException{

        // 일단 위에거 안쓰고 데이터 저장용 테스트
        TMDBMovieAPI api = new TMDBMovieAPI();
        KobisMovieAPI kobisApi = new KobisMovieAPI();
        String movieName = "THE ROUNDUP : PUNISHMENT";
        String year = "2024";
        TMDBMovieDetail md = api.movieDetail(movieName, year); //영화 정보를 불러옴

        // Movie3 목록 불러오기
        List<Movie3> list = serviceTest.getMovieList();


        //db에 넣을 entity
        Movie movie = new Movie();
        List<Actor> actors = new ArrayList<>();
        List<Director> directors = new ArrayList<>();
        List<MovieActor> movieActors = new ArrayList<>();
        List<MovieDirector> movieDirectors = new ArrayList<>();
        

        // Movie 저장. 심의등급, 장르, 한글명, 영어명, 개봉년도, kobis코드는 먼저 저장
        movie.setGenre(list.get(0).getRepGenreNm());
        movie.setEngName(list.get(0).getMovieNmEn());
        movie.setKorName(list.get(0).getMovieNm());
        
        // 날짜 파싱
        String dateString = list.get(0).getOpenDt();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date releaseDate = dateFormat.parse(dateString);
        movie.setReleaseDate(releaseDate);
        
        movie.setReleaseNation(list.get(0).getNationAlt());
        movie.setKobisId(list.get(0).getMovieCd());

        // kobis 영화디테일에서 채움
        KobisMovieInfo mi = kobisApi.searchMovieInfo(movie.getKobisId());
        movie.setWatchGrade(mi.getAudits().get(0).getWatchGradeNm());
        movie.setSponsor(mi.getCompanys().get(0).getCompanyNm());

        // tmdb에서 채움
        movie.setTmdbId(md.getId());
        movie.setMovieIntro(md.getOverview());
        movie.setPosterUrl("https://image.tmdb.org/t/p/original/" + md.getPosterPath());
        movie.setRunningTime(md.getRuntime());
        movie.setStillcutUrl("https://image.tmdb.org/t/p/original/" + md.getBackdropPath());
        movie.setTrailerUrl("https://www.youtube.com/watch?v=" + md.getResults().get(0).getKey());
        
        // actor 테이블 저장
        List<Cast> casts = md.getCasts();
        for (Cast cast : casts) {
            Actor actor = new Actor();
            actor.setEngName(cast.getOriginalName());
            actor.setImgUrl("https://image.tmdb.org/t/p/original/"+cast.getProfilePath());
            actor.setTmdbId(cast.getId());
            actor.setPopularity(Double.parseDouble(cast.getPopularity()));
            TMDBPersonDetails pd = api.personDetails(cast.getId());
            actor.setKorName(pd.getKorName());

            actors.add(actor);
            
            // movieActor 테이블 저장
            {
                MovieActor movieActor = new MovieActor();
                movieActor.setActorId(actor.getId());
                movieActor.setMovieId(movie.getId());
                movieActor.setCastEngName(cast.getCharacter());
                // movieActor.setCastKorName(year); 한국배역명은 일단 보류
                movieActor.setOrder(Long.parseLong(cast.getOrder()));

                movieActors.add(movieActor);
            }
        }

        // director 테이블 저장
        List<Crew> crews = md.getCrews();
        for (Crew crew : crews) {
            Director director = new Director();
            director.setEngName(crew.getOriginalName());
            director.setImgUrl("https://image.tmdb.org/t/p/original/"+crew.getProfilePath());
            director.setTmdbId(crew.getId());
            director.setPopularity(Double.parseDouble(crew.getPopularity()));
            TMDBPersonDetails pd = api.personDetails(crew.getId());
            director.setKorName(pd.getKorName());

            directors.add(director);

            // movieDirector 테이블 저장
            {
                MovieDirector movieDirector = new MovieDirector();
                movieDirector.setDirectorId(director.getId());
                movieDirector.setMovieId(movie.getId());

                movieDirectors.add(movieDirector);
            }
        }
        return "index";
    }

}
