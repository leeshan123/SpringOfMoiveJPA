package kr.co.moviespring.web.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import kr.co.moviespring.web.entity.Movie2;
import kr.co.moviespring.web.repository.MovieInsertRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieInsertServiceImp implements MovieInsertService{

    @Autowired
    MovieInsertRepository repository;


    //일일 박스오피스 리스트를 가져온걸 Myriadb에 넣기.
    @Override
    public void DailyBoxOfficeSave(List<Movie2> movieList) {
        for(int i=0;i<movieList.size();i++){
        repository.DailyBoxOfficeSave(movieList.get(i));
        }
    }

    //다 저장하는 함수
    //지금 쓸일은 없는듯
    //영화 목록 리스트를 가져온걸 Myriadb에 넣기.
//    @Override
//    public void saveAllMovie(List<Movie2> movieList) {
//        for(int i=0;i<movieList.size();i++){
//            repository.saveAll(movieList.get(i));
//        }
//
//    }

    //같은게 없으면 저장함.
    //영화 목록 리스트에서 중복이 없는 것만 가져옴
    @Override
    public void saveIfNotMovie(List<Movie2> movieList) {
        for(int i=0;i<movieList.size();i++){
            repository.saveIfNotMovie(movieList.get(i));
        }
    }
    //엑셀에서 가져온 누적관객수 데이터 넣기.
    @Override
    public void AduienceAccInsert(List<Movie2> movie2List) {
        for(int i=0; i<movie2List.size();i++){
            repository.AduienceAccInsert(movie2List.get(i));
        }
    }


    //일별 박스오피스 외부 API요청하기
    @Override
    public void getDailyBoxOffice(String key, String targetDt) {
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
            BoxOfficeResult boxOfficeResult = gson.fromJson(sb.toString(),BoxOfficeResult.class);
            List<Movie2> movieList = new ArrayList<>();
            for (Movie2 dailyBoxOfficeList : boxOfficeResult.getBoxOfficeResult().getdailyBoxOfficeList()) {
                Movie2 movie2 = new Movie2();
                movie2.setMovieCd(dailyBoxOfficeList.getMovieCd());
                movie2.setMovieNm(dailyBoxOfficeList.getMovieNm());
                movie2.setOpenDt(dailyBoxOfficeList.getOpenDt());
                movie2.setSalesAmt(dailyBoxOfficeList.getSalesAmt());
                movie2.setAudiCnt(dailyBoxOfficeList.getAudiCnt());
                movie2.setAudiAcc(dailyBoxOfficeList.getAudiAcc());
                movieList.add(movie2);

            }

            // 저장된 Movie2 객체 출력
            for (Movie2 movie : movieList) {
                System.out.println(movie.toString());
            }

            System.out.println(movieList.size());
            System.out.println(movieList.get(0));

            DailyBoxOfficeSave(movieList);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Gson으로 파싱하기 위한 클래스 구조
    class BoxOfficeResult {
        //JSON 필드 이름과 Java 필드 이름을 매핑해주는데 혹시 몰라서..
        @SerializedName("boxOfficeResult")
        private dailyBoxOfficeList boxOfficeResult;

        public dailyBoxOfficeList getBoxOfficeResult() {
            return boxOfficeResult;
        }
    }

    class dailyBoxOfficeList {
        @SerializedName("dailyBoxOfficeList")
        private List<Movie2> dailyBoxOfficeList;

        public List<Movie2> getdailyBoxOfficeList() {
            return dailyBoxOfficeList;
        }

    }

    //이거 쓸일이 없을거 같음
//    @Override
//    public void getsaveAllMovie(String key) {
//
//        List<Movie2> movielist= new ArrayList<>();
//
//        // 1부터 10까지 실행(총 100개)
//        for (int i = 1; i < 10; i++) {
//            try {
//                StringBuilder sb = new StringBuilder();
//                URL url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=" + key + "&curPage=" + i);
//                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
//
//
//                String line;
//                while ((line = br.readLine()) != null) {
//                    sb.append(line);
//                }
//
//                Gson gson = new GsonBuilder().create();
//
//
//                // Gson을 사용하여 JSON 데이터를 파싱
//                MovieListResult movielistresult = gson.fromJson(sb.toString(), MovieListResult.class);
//
//                // 파싱된 데이터를 리스트에 추가 movielistresult가 null이면 값이 들어오지 않게 설정.
//                if (movielistresult != null && movielistresult.getMovielistresult() != null) {
//                    for (Movie2 movielistinfo : movielistresult.getMovielistresult().getMovieList()) {
//                        Movie2 movie2 = new Movie2();
//                        movie2.setMovieCd(movielistinfo.getMovieCd());
//                        movie2.setMovieNm(movielistinfo.getMovieNm());
//                        movie2.setOpenDt(movielistinfo.getOpenDt());
//                        movie2.setRepGenreNm(movielistinfo.getRepGenreNm());
//                        movielist.add(movie2);
//                    }
//                } else {
//                    System.out.println("영화 널나옴.");
//                }
//
//                br.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        saveAllMovie(movielist);
//
//    }

    //영화가 없으면 가져와라.
    @Override
    public void getsaveIfNotMovie(String key) {

        List<Movie2> movielist= new ArrayList<>();

        // 1부터 10까지 실행(총 100개)
        for (int i = 1; i < 200; i++) {
            try {
                StringBuilder sb = new StringBuilder();
                URL url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=" + key + "&curPage=" + i);
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));


                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                Gson gson = new GsonBuilder().create();


                // Gson을 사용하여 JSON 데이터를 파싱
                MovieListResult movielistresult = gson.fromJson(sb.toString(), MovieListResult.class);

                // 파싱된 데이터를 리스트에 추가 movielistresult가 null이면 값이 들어오지 않게 설정.
                if (movielistresult != null && movielistresult.getMovielistresult() != null) {
                    for (Movie2 movielistinfo : movielistresult.getMovielistresult().getMovieList()) {
                        Movie2 movie2 = new Movie2();
                        movie2.setMovieCd(movielistinfo.getMovieCd());
                        movie2.setMovieNm(movielistinfo.getMovieNm());
                        movie2.setOpenDt(movielistinfo.getOpenDt());
                        movie2.setRepGenreNm(movielistinfo.getRepGenreNm());
                        movielist.add(movie2);
                    }
                } else {
                    System.out.println("영화 널나옴.");
                }

                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(movielist.size());
        saveIfNotMovie(movielist);

    }



    //영화 목록의 영화리스트 결과 클래스 담기
class MovieListResult {

    @SerializedName("movieListResult")
    private MovieListInfo movieListInfo;

    public MovieListInfo getMovielistresult() {
        return movieListInfo;
    }
}
//영화 목록의 영화 리스트결과의 리스트정보 담기.
class MovieListInfo {
    @SerializedName("movieList")
    private List<Movie2> movieList;

    public List<Movie2> getMovieList() {
        return movieList;
    }
}





}
