package kr.co.moviespring.web.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import kr.co.moviespring.web.entity.MovieStillcut;
import kr.co.moviespring.web.movieapi.KobisMovieAPI;
import kr.co.moviespring.web.movieapi.TMDBMovieAPI;
import kr.co.moviespring.web.movieapi.dto.kobis.KobisMovieInfo;
import kr.co.moviespring.web.movieapi.dto.tmdb.TMDBMovieDetail;
import kr.co.moviespring.web.movieapi.dto.tmdb.TMDBPersonDetails;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Cast;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Crew;
import kr.co.moviespring.web.service.ActorService;
import kr.co.moviespring.web.service.DirectorService;
import kr.co.moviespring.web.service.MovieActorService;
import kr.co.moviespring.web.service.MovieDirectorService;
import kr.co.moviespring.web.service.MovieInsertService;
import kr.co.moviespring.web.service.MovieService;
import kr.co.moviespring.web.service.MovieStillcutService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    MovieService movieService;

    @Autowired
    ActorService actorService;

    @Autowired
    DirectorService directorService;

    @Autowired
    MovieActorService movieActorService;

    @Autowired
    MovieDirectorService movieDirectorService;

    @Autowired
    MovieInsertService serviceTest;

    @Autowired
    MovieStillcutService stillcutService;


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

        // Movie3 목록 불러오기(테스트용, 일단 10개만, 20240425 이전만)
        List<Movie3> list = serviceTest.getMovieList();

        // 제목이랑 개봉일자(년도만 추출) 저장해서 영화정보 불러오기
        // 일단 1개만 저장하고 나중에 반복문으로 가져온 개수만큼 저장하기
        movieName = list.get(0).getMovieNmEn();
        year = list.get(0).getOpenDt().substring(0, 4); 
        
        // TMDB에서 이름 년도로 영화 정보를 불러옴
        // 임시로 범죄도시1로 테스트
        movieName = "THE OUTLAWS";
        year = "2017";
        Long movieCode = api.serchMovie(movieName, year);

        // movieCode가 null이 아니면 중복체크 후 저장, 아니면 리턴
        TMDBMovieDetail md = new TMDBMovieDetail();
        if(movieCode != null){
            //중복체크
            String strCode = Long.toString(movieCode);
            Movie movie = movieService.getByTMDBId(strCode);

            if(movie == null)
                md = api.movieDetail(movieCode);
            else{
                System.out.println("이미 존재하는 영화");
                return "index";
            }
        }
        else{
            // movie3의 데이터로 api에서 찾지 못했다면 데이터를 csv에 저장 후 리턴
            // csv 테스트
            String movieString = list.get(0).toString();

            String directoryPath = "C:/Newlec/test_csv/"; // 폴더 경로
            String fileName = "not_save_movie.csv";

            // 폴더가 존재하지 않으면 폴더를 생성.
            Path path = Paths.get(directoryPath);
            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);
                    System.out.println("폴더가 생성되었습니다: " + path);
                } catch (IOException e) {
                    System.err.println("폴더 생성 중 오류가 발생하였습니다: " + e.getMessage());
                }
            }

            // 값만 꺼내기
            String[] keyValuePairs = movieString.split(", ");
            StringBuilder csvLine = new StringBuilder();
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split("=");
                String value = keyValue.length > 1 ? keyValue[1] : ""; // 값이 없는 경우 공백으로 처리
                csvLine.append(value).append(",");
            }
            // 마지막 쉼표와 ) 제거
            csvLine.delete(csvLine.length() - 2, csvLine.length());
            csvLine.append("\n");

            // 파일 경로
            String filePath = directoryPath + "/" + fileName;
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(csvLine.toString());
            writer.close();

            return "index";
        }

        //db에 넣을 entity
        Movie movie = new Movie();
        
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
        movie.setPosterUrl("https://image.tmdb.org/t/p/original" + md.getPosterPath());
        movie.setRunningTime(md.getRuntime());
        movie.setMainImgUrl("https://image.tmdb.org/t/p/original" + md.getBackdropPath());
        movie.setTrailerUrl("https://www.youtube.com/embed/" + md.getResults().get(0).getKey());


        // movie를 db에 저장하고 생성된 ID 가져옴
        Long movieID = movieService.saveMovie(movie);


        // 스틸컷 저장
        MovieStillcut stillcut = new MovieStillcut();
        stillcut.setMovieId(movieID);   
        for (String strUrl : md.getStillCuts()) {
            stillcut.setUrl(strUrl);
            stillcutService.add(stillcut);   
        }

        // actor 테이블 저장
        List<Cast> casts = md.getCasts();
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
                actor.setImgUrl("https://image.tmdb.org/t/p/original"+cast.getProfilePath());
                actor.setTmdbId(tmdbId);
                actor.setPopularity(Double.parseDouble(cast.getPopularity()));
                TMDBPersonDetails pd = api.personDetails(cast.getId());
                actor.setKorName(pd.getKorName());
                // db에 저장하고 생성된 ID 가져옴
                actorId = actorService.add(actor);
            }
            else
                actorId = actor.getId();

            // movieActor 테이블 저장
            {
                MovieActor movieActor = new MovieActor();
                movieActor.setActorId(actorId);
                movieActor.setMovieId(movieID);
                movieActor.setCastEngName(cast.getCharacter());
                // movieActor.setCastKorName(year); 한국배역명은 일단 보류
                movieActor.setCastOrder(Long.parseLong(cast.getCastOrder()));

                // 리스트에 저장할 필요가 있나?
                // movieActors.add(movieActor);

                //db에 저장
                movieActorService.add(movieActor);
            }
        }

        // director 테이블 저장
        List<Crew> crews = md.getCrews();
        for (Crew crew : crews) {
            String tmdbId = crew.getId();

            // db에 tmdbId가 있는지 체크 후 저장하거나 배우ID 가져오기
            Director director = directorService.getByTMDBId(tmdbId);
            Long directorId = 0L;

            if(director == null){
                director = new Director();
                director.setEngName(crew.getOriginalName());
                director.setImgUrl("https://image.tmdb.org/t/p/original"+crew.getProfilePath());
                director.setTmdbId(crew.getId());
                director.setPopularity(Double.parseDouble(crew.getPopularity()));
                TMDBPersonDetails pd = api.personDetails(crew.getId());
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
        }
        return "index";
    }

}
