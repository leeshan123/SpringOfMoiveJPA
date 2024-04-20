package kr.co.moviespring.web.movieapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import kr.co.moviespring.web.movieapi.TMDBMovieDetail.Cast;
import kr.co.moviespring.web.movieapi.TMDBMovieDetail.Genre;
import kr.co.moviespring.web.movieapi.TMDBMovieDetail.Result;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TMDBMovieAPI {
    

    // 영화명, 제작년도, 일단 search-movie에서 코드 찾은다음 movie-detatil에서 검색
    public void movieDetail(String movieName, String movieYear) throws IOException{

        //반환할 데이터 객체 생성
        //일단 보류

        //search-movie 먼저 여기서는 영화 코드만 추출하면 됨.
        OkHttpClient client = new OkHttpClient();
        String strMovieName = movieName;
        String strYear = movieYear;
        Long lMovieCode = 0L;

        {
            // 영화 코드만 추출하는 search-movie
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

            // 데이터 담을 그릇
            TMDBMovieDetailEntity movieDetail = new TMDBMovieDetailEntity();

            // 데이터 추출 작업
            // "results" 키의 값인 JsonArray를 추출
            JSONObject videos = responseBody.getJSONObject("videos");
            JSONArray resultArray = videos.getJSONArray("results");
            Iterator<Object> resultIter = resultArray.iterator();
            List<Result> resultList = new ArrayList<>();
            while (resultIter.hasNext()) {
                JSONObject object = (JSONObject)resultIter.next();
                Result result = new Result();
                result.setKey(object.getString("key"));
                result.setName(object.getString("name"));
                result.setPublishedAt(object.getString("published_at"));
                resultList.add(result);
            }
            movieDetail.setResults(resultList);

            // "casts" 키의 값인 JsonArray를 추출
            JSONObject credits = responseBody.getJSONObject("credits");
            JSONArray castArr = credits.getJSONArray("cast");
            Iterator<Object> castIter = castArr.iterator();
            List<Cast> castList = new ArrayList<>();
            while (castIter.hasNext()) {
                JSONObject object = (JSONObject)castIter.next();
                Cast cast = new Cast();
                cast.setId(String.valueOf(object.getLong("id")));
                cast.setGender(String.valueOf(object.getLong("gender")));
                cast.setCharacter(object.getString("character"));
                cast.setProfilePath(object.getString("profile_path"));
                cast.setOriginalName(object.getString("original_name"));
                castList.add(cast);
            }
            movieDetail.setCasts(castList);

            // "genres" 키의 값인 JsonArray를 추출
            JSONArray genreArr = responseBody.getJSONArray("genres");
            Iterator<Object> genreIter = genreArr.iterator();
            List<Genre> genreList = new ArrayList<>();
            while (genreIter.hasNext()) {
                JSONObject object = (JSONObject)genreIter.next();
                Genre genre = new Genre();
                genre.setId(String.valueOf(object.getLong("id")));
                genre.setName(object.getString("name"));
                genreList.add(genre);
            }
            movieDetail.setGenres(genreList);

            // "production_countries" 키의 값인 JsonArray를 추출
            JSONArray pcArray = responseBody.getJSONArray("production_countries");
            Iterator<Object> pcIter = pcArray.iterator();
            List<String> pcList = new ArrayList<>();;
            while(pcIter.hasNext()){
                JSONObject object = (JSONObject)pcIter.next();
                pcList.add(object.getString("name"));
            }
            movieDetail.setProductCountries(pcList);

            // "origin_country" 키의 값인 JsonArray를 추출 ==> 이거 Json 형식이 이상함 나중에 필요하면 다시
            // JSONArray ocArray = responseBody.getJSONArray("origin_country");
            // Iterator<Object> ocIter = ocArray.iterator();
            // List<String> ocList = new ArrayList<>();;
            // while(ocIter.hasNext()){
            //     JSONObject object = (JSONObject)ocIter.next();
            //     ocList.add(object.getString("name"));
            // }
            // MovieDetail.setOriginCountries(ocList);
            
            movieDetail.setId(String.valueOf(responseBody.getLong("id")));
            movieDetail.setTitle(responseBody.getString("title"));
            movieDetail.setBackdropPath(responseBody.getString("backdrop_path"));
            movieDetail.setOverview(responseBody.getString("overview"));
            movieDetail.setOriginalTitle(responseBody.getString("original_title"));
            movieDetail.setRuntime(String.valueOf(responseBody.getLong("runtime")));
            movieDetail.setReleaseDate(responseBody.getString("release_date"));
            movieDetail.setPosterPath(responseBody.getString("poster_path"));
            movieDetail.setTagLine(responseBody.getString("tagline"));

        }

    }

    public static void main(String[] args) throws IOException {
        // API 객체 생성
        TMDBMovieAPI api = new TMDBMovieAPI();
        String movieName = "THE ROUNDUP : PUNISHMENT";
        String year = "2024";
        api.movieDetail(movieName, year);
 
        // API 요청
        // api.requestAPI();
        // api.requestBoxDailly();
    }
}
