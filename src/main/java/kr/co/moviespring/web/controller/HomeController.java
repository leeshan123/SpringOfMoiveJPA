package kr.co.moviespring.web.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
import kr.co.moviespring.web.service.MovieInsertService;
import kr.co.moviespring.web.service.MovieService;
import kr.co.moviespring.web.service.MovieStillcutService;
import kr.co.moviespring.web.service.MovieTrailerService;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    MovieTrailerService trailerService;


    @GetMapping("")
    public String main(Model model) {
        // 올해의 영화
        List<Movie> list = movieService.getListByYear();
        model.addAttribute("list", list);
        //개봉예정영화
        List<Movie> listAfter = movieService.getListAfter();
        model.addAttribute("listAfter", listAfter);

        return "main";
    }

    //게시글 검색페이지 요청
    @GetMapping("search")
    public String search(@RequestParam(name="query",required = false)String query, Model model) {
        List<Movie> mList = movieService.getListByName(query);
        List<Actor> aList = actorService.getListByName(query);
        List<Director> dList = directorService.getListByName(query);

//        model.addAttribute("query", query);
        model.addAttribute("mList", mList);
        model.addAttribute(("dList"), dList);
        model.addAttribute(("aList"), aList);

        return "search";
    }

    //영화 검색 더보기페이지 요청
    @GetMapping("search/movies")
    public String searchMovie(@RequestParam(name="query",required = false)String query, Model model) {
        List<Movie> mList = movieService.getListByName(query);
//        List<Actor> aList = actorService.getListByName(query);
//        List<Director> dList = directorService.getListByName(query);
//
////        model.addAttribute("query", query);
        model.addAttribute("mList", mList);
//        model.addAttribute(("dList"), dList);
//        model.addAttribute(("aList"), aList);

        return "search-movie";
    }

    //인물 검색 더보기페이지 요청
    @GetMapping("search/people")
    public String searchPeople(@RequestParam(name="query",required = false)String query, Model model) {
//        List<Movie> mList = movieService.getListByName(query);
        List<Actor> aList = actorService.getListByName(query);
        List<Director> dList = directorService.getListByName(query);
//
////        model.addAttribute("query", query);
//        model.addAttribute("mList", mList);
        model.addAttribute(("dList"), dList);
        model.addAttribute(("aList"), aList);

        return "search-people";
    }

    @PostMapping("test")
    public String test(String nyear) throws IOException, ParseException, InterruptedException{

        // 일단 위에거 안쓰고 데이터 저장용 테스트
        TMDBMovieAPI api = new TMDBMovieAPI();
        KobisMovieAPI kobisApi = new KobisMovieAPI();
        String movieName = "THE ROUNDUP : PUNISHMENT";
        String year = "2024";
        String nation = "";

        // Movie3 목록 불러오기(년도수만큼 불러오기)
        // List<Movie3> list = serviceTest.getMovie3List(nyear);

        //list 개수 저장=====================================================================

        // String dirPath = "C:/Newlec/test_csv/"; // 폴더 경로
        // String fileNm = "list_cnt.csv";
        int movieCnt = 0;
        int kobisNullMovieDetail = 0;
        StringBuilder sb = new StringBuilder();
        // // 폴더가 존재하지 않으면 폴더를 생성.
        // Path path2 = Paths.get(dirPath);
        // if (!Files.exists(path2)) {
        //     try {
        //         Files.createDirectories(path2);
        //         System.out.println("폴더가 생성되었습니다: " + path2);
        //     } catch (IOException e) {
        //         System.err.println("폴더 생성 중 오류가 발생하였습니다: " + e.getMessage());
        //     }
        // }
        // // 파일 경로
        // String filePath2 = dirPath + "/" + fileNm;
        // BufferedWriter writer2 = new BufferedWriter(new FileWriter(filePath2, true));
        // writer2.write(list.size());
        // writer2.close();

        // ===================================================================================
        
        //10년치
        for(int i = 0; i < 30; i++){

            // Movie3 목록 불러오기(년도수만큼 불러오기)
            List<Movie3> list = serviceTest.getMovie3List(nyear);

           for (Movie3 movie3 : list) {
            movieCnt++;
            // 제목이랑 개봉일자(년도만 추출) 저장해서 영화정보 불러오기
            // movieName = movie3.getMovieNm();
            year = movie3.getOpenDt().substring(0, 4);
            nation = movie3.getNationAlt(); 

            Long movieCode;
            // TMDB에서 이름 년도로 영화 정보를 불러옴
            // 한국영화인지 확인 후 순서 다르게
            if(nation.contains("한국")){
                movieName = movie3.getMovieNm();
                movieCode = api.serchMovie(movieName, year);
                if(movieCode == null){
                    movieName = movie3.getMovieNmEn();
                    movieCode = api.serchMovie(movieName, year);
                }
            }
            else{
                movieName = movie3.getMovieNmEn();
                movieCode = api.serchMovie(movieName, year);
                if(movieCode == null){
                    movieName = movie3.getMovieNm();
                    movieCode = api.serchMovie(movieName, year);
                }
            }


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
                    System.out.println("현재 저장 개수: " + movieCnt);
                    Thread.sleep(100);
                    continue;
                    // return "index";
                }
            }
            else{
                // movie3의 데이터로 api에서 찾지 못했다면 데이터를 csv에 저장 후 리턴
                // csv 테스트
                String movieString = movie3.toString();

                String directoryPath = "C:/Newlec/test_csv/"; // 폴더 경로
                // String fileName = "not_save_movie.csv";
                String fileName = String.format("not_save_movie_%s.csv", year);

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

                // 없으면 저장하고 다음 영화로
                continue;
                // return "index";
            }


            //db에 넣을 entity
            Movie movie = new Movie();
            
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

            // kobis 영화디테일에서 채움
            KobisMovieInfo mi = kobisApi.searchMovieInfo(movie.getKobisId());

            if(mi == null){
                kobisNullMovieDetail++;
                sb.append(movie.getKorName()).append("/n");
                continue;
            }

            List<Audits> audits = mi.getAudits();
            if (audits == null || audits.isEmpty()) {
                // audits가 null이 아니고 비어있으면 처리할 작업을 수행한다
                kobisNullMovieDetail++;
                sb.append(movie.getKorName()).append("/n");
                continue;
            } 


            movie.setWatchGrade((mi.getAudits().isEmpty() || mi.getAudits() == null) ? null : mi.getAudits().get(0).getWatchGradeNm());
            movie.setSponsor((mi.getCompanys().isEmpty() || mi.getCompanys() == null) ? null : mi.getCompanys().get(0).getCompanyNm());
            
            // tmdb에서 채움
            movie.setTmdbId(md.getId());
            movie.setMovieIntro(md.getOverview());
            movie.setPosterUrl(md.getPosterPath());
            movie.setRunningTime(md.getRuntime());
            movie.setLogoUrl(md.getLogo());
            movie.setMainImgUrl(md.getBackdropPath());
            
            
            // movie.setTrailerUrl(md.getResults().isEmpty() ? null : md.getResults().get(0).getKey());

            // movie를 db에 저장하고 생성된 ID 가져옴
            Long movieID = movieService.saveMovie(movie);

            // 트레일러 저장
            MovieTrailer trailer = new MovieTrailer();
            trailer.setMovieId(movieID);
            for (Result result : md.getResults()) {
                trailer.setTrailerIntro(result.getName());
                trailer.setUrl(result.getKey());
                trailerService.add(trailer);
            }

            // 스틸컷 저장
            MovieStillcut stillcut = new MovieStillcut();
            stillcut.setMovieId(movieID);   
            for (String strUrl : md.getStillCuts()) {
                stillcut.setUrl(strUrl);
                stillcutService.add(stillcut);   
                Thread.sleep(100);
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
                    actor.setImgUrl(cast.getProfilePath());
                    actor.setTmdbId(tmdbId);
                    actor.setPopularity(Double.parseDouble(cast.getPopularity()));
                    TMDBPersonDetails pd = api.personDetails(cast.getId());
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
                    // movieActor.setCastKorName(year); 한국배역명은 일단 보류
                    movieActor.setCastOrder(Long.parseLong(cast.getCastOrder()));

                    // 리스트에 저장할 필요가 있나?
                    // movieActors.add(movieActor);
                    // 저장 전에 중복 체크
                    boolean isExist = movieActorService.checkMovieActor(movieActor);

                    //db에 저장
                    if(!isExist)
                        movieActorService.add(movieActor);
                }
                Thread.sleep(120);
            }

            // director 테이블 저장
            List<Crew> crews = md.getCrews();
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
                Thread.sleep(100);
            }
            System.out.println(sb);
            System.out.println("영진위 영화 디테일 값 없음: " + kobisNullMovieDetail);
            System.out.println("현재 저장 개수: " + movieCnt);
            System.out.println("총 개수: " + list.size());
            Thread.sleep(1100);
            }

            int num = Integer.parseInt(nyear);
            num--;
            nyear = Integer.toString(num);
            movieCnt = 0;
        }
        return "index";
    }

}
