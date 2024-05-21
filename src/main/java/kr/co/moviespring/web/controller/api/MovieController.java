package kr.co.moviespring.web.controller.api;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import kr.co.moviespring.web.entity.Actor;
import kr.co.moviespring.web.entity.Director;
import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.Movie3;
import kr.co.moviespring.web.entity.MovieActor;
import kr.co.moviespring.web.entity.MovieDirector;
import kr.co.moviespring.web.entity.MovieStillcut;
import kr.co.moviespring.web.entity.MovieTrailer;
import kr.co.moviespring.web.movieapi.KobisMovieAPI;
import kr.co.moviespring.web.movieapi.TMDBMovieAPI;
import kr.co.moviespring.web.movieapi.dto.kobis.KobisMovieInfo;
import kr.co.moviespring.web.movieapi.dto.kobis.sub.Audits;
import kr.co.moviespring.web.movieapi.dto.tmdb.TMDBMovieDetail;
import kr.co.moviespring.web.movieapi.dto.tmdb.TMDBPersonDetails;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Cast;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Crew;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Result;
import kr.co.moviespring.web.service.ActorService;
import kr.co.moviespring.web.service.DirectorService;
import kr.co.moviespring.web.service.MovieActorService;
import kr.co.moviespring.web.service.MovieDirectorService;
import kr.co.moviespring.web.service.MovieService;
import kr.co.moviespring.web.service.MovieStillcutService;
import kr.co.moviespring.web.service.MovieTrailerService;


@RestController("ApiMovieController")
@RequestMapping("api/movie")
public class MovieController {
    
    @Autowired
    MovieService movieService;

    @Autowired
    MovieTrailerService trailerService;

    @Autowired
    MovieStillcutService stillcutService;

    @Autowired
    ActorService actorService;

    @Autowired
    MovieActorService movieActorService;

    @Autowired
    DirectorService directorService;
    
    @Autowired
    MovieDirectorService movieDirectorService;


    
    @PostMapping("save")
    public ResponseEntity<String> save(
        Movie3 movie3
        , Long tmdbCode
    ) throws IOException, InterruptedException, ParseException{

        TMDBMovieAPI tmdbApi = new TMDBMovieAPI();
        KobisMovieAPI kobisApi = new KobisMovieAPI();
 
        // 중복체크
        {
            String strCode = Long.toString(tmdbCode);
            Movie movie = movieService.getByTMDBId(strCode);
            
            // 이미 영화가 있다면
            if(movie != null){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 영화입니다.");
            }
        }
            
        // db에 넣을 entity
        Movie movie = new Movie();

        // tmdb 영화 상세정보 요청
        TMDBMovieDetail tmd = new TMDBMovieDetail();
        tmd = tmdbApi.movieDetail(tmdbCode);

        // Movie 저장. 심의등급, 장르, 한글명, 영어명, 개봉년도, kobis코드는 먼저 저장
        movie.setGenre(movie3.getRepGenreNm());
        movie.setEngName(movie3.getMovieNmEn());
        movie.setKorName(movie3.getMovieNm());

        // 날짜 파싱
        String dateString = movie3.getOpenDt();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date releaseDate = dateFormat.parse(dateString);
        movie.setReleaseDate(releaseDate);
        movie.setReleaseNation(movie3.getNationAlt());
        movie.setKobisId(movie3.getMovieCd());

        // kobis api 요
        KobisMovieInfo kmi = kobisApi.searchMovieInfo(movie.getKobisId());

        // 없을경우 객체 할당
        List<Audits> audits = kmi.getAudits();
        if (audits == null)
            kmi.setActors(new ArrayList<>());
        
        movie.setWatchGrade((kmi.getAudits().isEmpty() || kmi.getAudits() == null) ? null : kmi.getAudits().get(0).getWatchGradeNm());
        movie.setSponsor((kmi.getCompanys().isEmpty() || kmi.getCompanys() == null) ? null : kmi.getCompanys().get(0).getCompanyNm());

        // tmdb에서 채움
        movie.setTmdbId(tmd.getId());
        movie.setMovieIntro(tmd.getOverview());
        movie.setPosterUrl(tmd.getPosterPath());
        movie.setRunningTime(tmd.getRuntime());
        movie.setLogoUrl(tmd.getLogo());
        movie.setMainImgUrl(tmd.getBackdropPath());

        // movie를 db에 저장하고 생성된 ID 가져옴
        Long movieID = movieService.saveMovie(movie);

        // 트레일러 저장
        MovieTrailer trailer = new MovieTrailer();
        trailer.setMovieId(movieID);
        for (Result result : tmd.getResults()) {
            trailer.setTrailerIntro(result.getName());
            trailer.setUrl(result.getKey());
            trailerService.add(trailer);
        }

        // 스틸컷 저장
        MovieStillcut stillcut = new MovieStillcut();
        stillcut.setMovieId(movieID);   
        for (String strUrl : tmd.getStillCuts()) {
            stillcut.setUrl(strUrl);
            stillcutService.add(stillcut);   
            Thread.sleep(100);
        }

        // actor 테이블 저장
            List<Cast> casts = tmd.getCasts();
            for (Cast cast : casts) {
                String tmdbId = cast.getId();

                // 리스트에 저장할 필요가 있음?
                // actors.add(actor);

                // db에 tmdbId가 있는지 체크 후 저장하거나 배우ID 가져오기
                Actor actor = actorService.getByTMDBId(tmdbId);
                Long actorId = 0L;
                if(actor == null){
                    // db에 저장하고 생성된 ID 가져옴
                    actor = new Actor();
                    actor.setEngName(cast.getOriginalName());
                    actor.setImgUrl(cast.getProfilePath());
                    actor.setTmdbId(tmdbId);
                    actor.setPopularity(Double.parseDouble(cast.getPopularity()));
                    TMDBPersonDetails pd = tmdbApi.personDetails(cast.getId());
                    actor.setKorName(pd.getKorName());
                    // db에 저장하고 생성된 ID 가져옴
                    actorId = actorService.add(actor);
                }
                else
                    actorId = actor.getId();
                Thread.sleep(150);


                // movieActor 테이블 저장
                {
                    MovieActor movieActor = new MovieActor();
                    movieActor.setActorId(actorId);
                    movieActor.setMovieId(movieID);
                    movieActor.setCastEngName(cast.getCharacter());
                    movieActor.setCastOrder(Long.parseLong(cast.getCastOrder()));

                    // 저장 전에 중복 체크
                    boolean isExist = movieActorService.checkMovieActor(movieActor);

                    //db에 저장
                    if(!isExist)
                        movieActorService.add(movieActor);
                }
                Thread.sleep(120);
            }


            // director 테이블 저장
            List<Crew> crews = tmd.getCrews();
            for (Crew crew : crews) {
                Thread.sleep(150);
                String tmdbId = crew.getId();

                // db에 tmdbId가 있는지 체크 후 저장하거나 배우ID 가져오기
                Director director = directorService.getByTMDBId(tmdbId);
                Long directorId = 0L;

                if(director == null){
                    director = new Director();
                    director.setEngName(crew.getOriginalName());
                    director.setImgUrl(crew.getProfilePath());
                    director.setTmdbId(crew.getId());
                    director.setPopularity(Double.parseDouble(crew.getPopularity()));
                    TMDBPersonDetails pd = tmdbApi.personDetails(crew.getId());
                    director.setKorName(pd.getKorName());
                    // db에 저장하고 생성된 ID 가져옴
                    directorId = directorService.add(director);
                }
                else{
                    directorId = director.getId();
                }

                // movieDirector 테이블 저장
                {
                    MovieDirector movieDirector = new MovieDirector();
                    movieDirector.setDirectorId(directorId);
                    movieDirector.setMovieId(movieID);

                    // movieDirectors.add(movieDirector);
                    // db에 저장
                    movieDirectorService.add(movieDirector);
                }
                Thread.sleep(100);
            }
        // 응답 생성
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(movieID).toUri();

        return ResponseEntity.created(location).body("영화가 성공적으로 저장되었습니다.");
    }

    
}
