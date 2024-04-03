package kr.co.moviespring.web;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
OkHttpClient 사용을 위해 의존성 추가
 <dependency>
  <groupId>com.squareup.okhttp3</groupId>
  <artifactId>okhttp</artifactId>
  <version>3.10.0</version>
</dependency>
 * 
 */

public class TmdbApiTest {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        //요청 url, query(영화명)와 primary_release_year(년도)는 나중에 인자값으로 전달받아서 처리
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/search/movie?query=%EC%95%84%EC%A0%80%EC%94%A8&include_adult=false&language=ko-KR&primary_release_year=2010&page=1")
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
        // 데이터 추출
        JSONArray arrMovieInfo = responseBody.getJSONArray("results");
        Iterator<Object> iter = arrMovieInfo.iterator();
        JSONObject joMovieInfoParts = (JSONObject)iter.next();

        //키 입력하면 값 반환, 아래는 예시(overview라는 키를 입력)
        String overview = joMovieInfoParts.getString("overview");
        String title = joMovieInfoParts.getString("title");
        String posterPath = joMovieInfoParts.getString("poster_path");
        String backdropPath = joMovieInfoParts.getString("backdrop_path");
        String releaseDate = joMovieInfoParts.getString("release_date");

        System.out.println("overview : "+overview);
        System.out.println("title : "+title);
        System.out.println("posterPath : "+posterPath);
        System.out.println("backdropPath : "+backdropPath);
        System.out.println("releaseDate : "+releaseDate);


        //값이 배열인 경우가 있음(대괄호가 있으면 배열형식이라 Array로 받아야함)
        //아래는 예시
        //"audits":[{"auditNo":"2012-F610","watchGradeNm":"15세이상관람가"}]"
        // "audits" 키의 값인 JsonArray를 추출
        // JSONArray auditsArray = movieInfoResult.getJSONArray("audits");
        // Iterator<Object> auditsIter = auditsArray.iterator();//여기서 하나씩 받자
        // JSONObject auditsObj = (JSONObject) auditsIter.next();
        // movieInfor.setWatchGradeNm(auditsObj.getString("watchGradeNm"));



        //영상은 밑의 url에 붙여서 사용
        //https://image.tmdb.org/t/p/original/

    }
}
