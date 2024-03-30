package kr.co.moviespring.web.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.Movie2;
import kr.co.moviespring.web.service.MovieInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("InsertMovie")
public class MovieInsertController {

    @Autowired
    MovieInsertService movieInsertService;



    @GetMapping("list")
    public String list(Model model){
        List<Movie2> list = movieInsertService.getList();
        model.addAttribute("list", list);
        return "InsertMovie/list";
    }

    //제발 되라
    @GetMapping("save")
    public String save() throws IOException {

            String key = "fa4a546d896ea6a36a7db5d09bcb80f3";
            String targetDt = "20240327";

            try {
                URL url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="+ key+ "&targetDt="+targetDt);

                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine())!= null){
                    sb.append(line);
                }
                br.close();

                // Gson 객체 생성
                Gson gson = new GsonBuilder().create();

                // JSON 데이터를 Movie2 객체로 파싱
                BoxOfficeResult boxOfficeResult = gson.fromJson(sb.toString(), BoxOfficeResult.class);
                List<Movie2> movieList = new ArrayList<>();
                for (MovieInfo boxOfficeInfo : boxOfficeResult.getBoxOfficeResult().getDailyBoxOfficeList()) {
                    Movie2 movie2 = new Movie2();
                    movie2.setMovieCd(Integer.parseInt(boxOfficeInfo.getMovieCd()));
                    movie2.setMovieNm(boxOfficeInfo.getMovieNm());
                    movie2.setOpenDt(boxOfficeInfo.getOpenDt());
                    movie2.setSalesAmt(Integer.parseInt(boxOfficeInfo.getSalesAmt()));
                    movie2.setAudiCnt(Integer.parseInt(boxOfficeInfo.getAudiCnt()));
                    movie2.setAudiAcc(Integer.parseInt(boxOfficeInfo.getAudiAcc()));
                    movieList.add(movie2);

                }

                // 저장된 Movie2 객체 출력
                for (Movie2 movie : movieList) {
                    System.out.println(movie.toString());
                }

                System.out.println(movieList.size());
                System.out.println(movieList.get(0));

                movieInsertService.saveMovie(movieList);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return "InsertMovie/save";
        }

    // Gson으로 파싱하기 위한 클래스 구조
    static class BoxOfficeResult {
        @SerializedName("boxOfficeResult")
        private BoxOfficeInfo boxOfficeResult;

        public BoxOfficeInfo getBoxOfficeResult() {
            return boxOfficeResult;
        }
    }

    static class BoxOfficeInfo {
        @SerializedName("dailyBoxOfficeList")
        private List<MovieInfo> dailyBoxOfficeList;

        public List<MovieInfo> getDailyBoxOfficeList() {
            return dailyBoxOfficeList;
        }

    }


    static class MovieInfo {
        @SerializedName("movieCd")
        private String movieCd;
        @SerializedName("movieNm")
        private String movieNm;
        @SerializedName("openDt")
        private String openDt;
        @SerializedName("salesAmt")
        private String salesAmt;
        @SerializedName("audiCnt")
        private String audiCnt;
        @SerializedName("audiAcc")
        private String audiAcc;

        public String getMovieCd() {
            return movieCd;
        }

        public void setMovieCd(String movieCd) {
            this.movieCd = movieCd;
        }

        public String getMovieNm() {
            return movieNm;
        }

        public void setMovieNm(String movieNm) {
            this.movieNm = movieNm;
        }

        public String getOpenDt() {
            return openDt;
        }

        public void setOpenDt(String openDt) {
            this.openDt = openDt;
        }

        public String getSalesAmt() {
            return salesAmt;
        }

        public void setSalesAmt(String salesAmt) {
            this.salesAmt = salesAmt;
        }

        public String getAudiCnt() {
            return audiCnt;
        }

        public void setAudiCnt(String audiCnt) {
            this.audiCnt = audiCnt;
        }

        public String getAudiAcc() {
            return audiAcc;
        }

        public void setAudiAcc(String audiAcc) {
            this.audiAcc = audiAcc;
        }
// Getter와 Setter는 필요에 따라 추가
    }
    }




