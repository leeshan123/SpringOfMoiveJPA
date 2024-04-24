package kr.co.moviespring.web.movieapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import kr.co.moviespring.web.entity.Actor;
import kr.co.moviespring.web.entity.Director;
import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.movieapi.dto.kobis.KobisDailyBox;
import kr.co.moviespring.web.movieapi.dto.tmdb.TMDBMovieDetail;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieAPI {

    // 상수 설정
    //   - 요청(Request) 요청 변수
    private String REQUEST_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
    private final String AUTH_KEY = "8eebf0d30cb02fd27d0ccede30262ce2";
    private final String AUTH_KEY2 = "860589c36cddbbbbd930b4a6aaa53da7";
 
    //   - 일자 포맷
    private final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyyMMdd");

    // Map -> QueryString
    public String makeQueryString(Map<String, String> paramMap) {
        final StringBuilder sb = new StringBuilder();
        paramMap.entrySet().forEach(( entry )->{
            if( sb.length() > 0 ) {
                sb.append('&');
            }
            sb.append(entry.getKey()).append('=').append(entry.getValue());
        });
        return sb.toString();
    }
 
    // API요청
    public void requestAPI() {
        // 변수설정
        //   - 하루전 날짜
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
 
        // 변수 설정
        //   - 요청(Request) 인터페이스 Map
        //   - 어제자 다양성 한국영화 10개 조회
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key"          , AUTH_KEY);                        // 발급받은 인증키
        paramMap.put("targetDt"     , DATE_FMT.format(cal.getTime()));  // 조회하고자 하는 날짜
        paramMap.put("itemPerPage"  , "10");                            // 결과 ROW 의 개수( 최대 10개 )
        paramMap.put("multiMovieYn" , "N");                             // Y:다양성 영화, N:상업영화, Default:전체
        //paramMap.put("repNationCd"  , "K");                             // K:한국영화, F:외국영화, Default:전체

        try {
            // Request URL 연결 객체 생성
            URL requestURL = new URL(REQUEST_URL+"?"+makeQueryString(paramMap));
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
 
            // GET 방식으로 요청
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
 
            // 응답(Response) 구조 작성
            //   - Stream -> JSONObject
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String readline = null;
            StringBuffer response = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                response.append(readline);
            }
 
            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(response.toString());
 
            // 데이터 추출
            JSONObject boxOfficeResult = responseBody.getJSONObject("boxOfficeResult");
 
            // 박스오피스 주제 출력
            String boxofficeType = boxOfficeResult.getString("boxofficeType");
            System.out.println(boxofficeType);
 
            // 박스오피스 목록 출력
            JSONArray dailyBoxOfficeList = boxOfficeResult.getJSONArray("dailyBoxOfficeList");
            Iterator<Object> iter = dailyBoxOfficeList.iterator();


            while(iter.hasNext()) {
                JSONObject boxOffice = (JSONObject) iter.next();
                System.out.printf("  %s - %s\n", boxOffice.get("rnum"), boxOffice.get("movieNm"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    //순위, 대표코드, 해당일의 관객수, 누적관객수, 누적매출액
    public List<KobisDailyBox> requestBoxDailly(){
        
        // 일단 어제 날짜
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key"          , AUTH_KEY);                        // 발급받은 인증키
        paramMap.put("targetDt"     , DATE_FMT.format(cal.getTime()));  // 조회하고자 하는 날짜
        paramMap.put("itemPerPage"  , "10");                            // 결과 ROW 의 개수( 최대 10개 )
        paramMap.put("multiMovieYn" , "N");                             // Y:다양성 영화, N:상업영화, Default:전체
        
        //반환할 리스트 객체 생성
        List<KobisDailyBox> dbeList = new ArrayList<>();
        
        try {
            // Request URL 연결 객체 생성
            URL requestURL = new URL(REQUEST_URL+"?"+makeQueryString(paramMap));
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
            
            // GET 방식으로 요청
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            
            // 응답(Response) 구조 작성
            //   - Stream -> JSONObject
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String readline = null;
            StringBuffer response = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                response.append(readline);
            }
 
            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(response.toString());
 
            // 데이터 추출
            JSONObject boxOfficeResult = responseBody.getJSONObject("boxOfficeResult");
            
            // 박스오피스 주제 출력
            String boxofficeType = boxOfficeResult.getString("boxofficeType");
            System.out.println(boxofficeType);
            
            // 박스오피스 목록 출력
            JSONArray dailyBoxOfficeList = boxOfficeResult.getJSONArray("dailyBoxOfficeList");
            
            
            //여기서 하나씩 받자
            Iterator<Object> iter = dailyBoxOfficeList.iterator();
            
            while(iter.hasNext()) {
                JSONObject boxOffice = (JSONObject) iter.next();
                
                KobisDailyBox dbe = new KobisDailyBox();
                dbe.setRank(boxOffice.getString("rnum"));
                dbe.setMovieCd(boxOffice.getString("movieCd"));
                dbe.setAudiCnt(boxOffice.getString("audiCnt"));
                dbe.setAudiCnt(boxOffice.getString("audiAcc"));
                dbe.setSalesAcc(boxOffice.getString("salesAcc"));
                
                dbeList.add(dbe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return dbeList;
    }

    // 영화 상세정보, 영화코드
    public MovieInfoEntity searchMovieInfo(String movieCd){

        //요청 url 설정
        REQUEST_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json";

        //반환할 변수 생성
        MovieInfoEntity movieInfor = new MovieInfoEntity();

        // 변수 설정
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key"    , AUTH_KEY); // 발급받은 인증키
        paramMap.put("movieCd", movieCd);

        try {
            // Request URL 연결 객체 생성
            URL requestURL = new URL(REQUEST_URL+"?"+makeQueryString(paramMap));
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
 
            // GET 방식으로 요청
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
 
            // 응답(Response) 구조 작성
            //   - Stream -> JSONObject
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String readline = null;
            StringBuffer response = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                response.append(readline);
            }
 
            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(response.toString());
 
            // 데이터 추출
            JSONObject movieInfoResult = responseBody.getJSONObject("movieInfoResult").getJSONObject("movieInfo");

            //반환할 리스트 객체 생성
            movieInfor.setMovieCd(movieInfoResult.getString("movieCd")); //영화코드
            movieInfor.setMovieNm(movieInfoResult.getString("movieNm")); //한글이름
            movieInfor.setMovieNmEn(movieInfoResult.getString("movieNmEn"));//영문
            
            // "nations" 키의 값인 JsonArray를 추출
            JSONArray nationsArray = movieInfoResult.getJSONArray("nations");
            Iterator<Object> nationsIter = nationsArray.iterator();//여기서 하나씩 받자
            JSONObject nationsObj = (JSONObject) nationsIter.next();
            movieInfor.setNationNm(nationsObj.getString("nationNm"));//제작국가


            // "genres" 키의 값인 JsonArray를 추출
            JSONArray genreArray = movieInfoResult.getJSONArray("genres");
            Iterator<Object> genreIter = genreArray.iterator();//여기서 하나씩 받자
            JSONObject genreObj = (JSONObject) genreIter.next();
            movieInfor.setGenreNm(genreObj.getString("genreNm"));//genreNm

            movieInfor.setOpenDt(movieInfoResult.getString("openDt"));//개봉일자

        } catch (IOException e) {
            e.printStackTrace();
        }
        //반환
        return movieInfor;
    }

    public MovieUrlEntity requestUrl(String movieName, String movieYear) throws IOException{

        //반환할 데이터 객체 생성
        MovieUrlEntity entity = new MovieUrlEntity();

        //search-movie 먼저 여기서는 영화 코드만 추출하면 됨.
        OkHttpClient client = new OkHttpClient();
        String strMovieName = movieName;
        String strYear = movieYear;
        Long lMovieCode = 0L;

        {
            //search-movie
            String reqUrl = String.format("https://api.themoviedb.org/3/search/movie?query=%s&include_adult=false&language=ko-KR&video&primary_release_year=%s&page=1", strMovieName, strYear);

            //요청 url, query(영화명)와 primary_release_year(년도)는 나중에 인자값으로 전달받아서 처리
            Request request = new Request.Builder()
                .url(reqUrl)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyM2U5NWU1MTY0NWUzYjgwZDU0MzQyNGQxYTA5ODg0YSIsInN1YiI6IjY2MDEzYjRmNzcwNzAwMDE2MzBhZjg0MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFfawiMrE8C2YgpGPdaU5IOcl-R5t-JIquRBN6vaLzU")
                .build();

            Response response = client.newCall(request).execute();

            // 응답 데이터를 JSON 형식으로 파싱
            String responseData = response.body().string();

            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(responseData.toString());

            // String strResults = responseBody.getString("results");
            // 영화 코드 추출
            JSONArray arrMovieInfo = responseBody.getJSONArray("results");
            Iterator<Object> iter = arrMovieInfo.iterator();
            JSONObject jnMovieInfoParts = (JSONObject)iter.next();

            //키 입력하면 값 반환, 아래는 예시(overview라는 키를 입력)
            lMovieCode = jnMovieInfoParts.getLong("id");
            System.out.println("id : "+ lMovieCode);
        }

        {
            //movie-detail, 추출할 데이터: 
            //(소개글) tmdb, overview, 
            //(포스트) tmdb, poster_path,
            //스틸컷이미지,  backdrop_path
            //트레일러영상,  videos <= 배열 형식으로 받아야 함
            String reqUrl = String.format("https://api.themoviedb.org/3/movie/%d?append_to_response=videos,credits&language=ko-KR", lMovieCode);
            Request request = new Request.Builder()
                .url(reqUrl)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyM2U5NWU1MTY0NWUzYjgwZDU0MzQyNGQxYTA5ODg0YSIsInN1YiI6IjY2MDEzYjRmNzcwNzAwMDE2MzBhZjg0MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFfawiMrE8C2YgpGPdaU5IOcl-R5t-JIquRBN6vaLzU")
                .build();

            Response response = client.newCall(request).execute();

            // 응답 데이터를 JSON 형식으로 파싱
            String responseData = response.body().string();

            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(responseData.toString());
            String strOverView = responseBody.getString("overview");
            String strPosterPath = responseBody.getString("poster_path");
            String strStillCut = responseBody.getString("backdrop_path");
            JSONObject jnVideos = responseBody.getJSONObject("videos");
            JSONArray arrResult = jnVideos.getJSONArray("results");
            JSONObject jnResult = arrResult.getJSONObject(0);
            String strTrailerURL = jnResult.getString("key");


            entity.setId(lMovieCode);
            entity.setOverView(strOverView);
            entity.setPosterUrl(strPosterPath);
            entity.setStillcutUrl(strStillCut);
            entity.setTrailerUrl(strTrailerURL);
        }

        return entity;
    };


    
    public static void main(String[] args) throws IOException {
        // 일단 위에거 안쓰고 데이터 저장용 테스트
        TMDBMovieAPI api = new TMDBMovieAPI();
        String movieName = "THE ROUNDUP : PUNISHMENT";
        String year = "2024";
        TMDBMovieDetail md = api.movieDetail(movieName, year);

        //db에 넣을 entity
        Actor actor = new Actor();
        Director director = new Director();
        Movie movie = new Movie();

    }
}
