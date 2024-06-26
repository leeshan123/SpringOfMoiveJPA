package kr.co.moviespring.web.movieapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import kr.co.moviespring.web.entity.Movie3;
import kr.co.moviespring.web.movieapi.dto.tmdb.TMDBMovieDetail;
import kr.co.moviespring.web.movieapi.dto.tmdb.TMDBPersonDetails;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Cast;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Crew;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Genre;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.MovieInfo;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Result;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TMDBMovieAPI {

    public List<MovieInfo> serchMovies(String movieName) throws IOException{

        List<MovieInfo> movies = new ArrayList<>();

        //여기서는 영화 코드, 이름, 이미지만 추출하면 됨.
        OkHttpClient client = new OkHttpClient();
        String reqUrl = String.format("https://api.themoviedb.org/3/search/movie?query=%s&include_adult=false&language=ko-KR&page=1", movieName);

        //요청 url
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

        // 영화 정보 추출
        JSONArray arrMovieInfo = responseBody.getJSONArray("results");
        Iterator<Object> iter = arrMovieInfo.iterator();
        // 예외처리
        if(arrMovieInfo.isEmpty())
            return null;

        while(iter.hasNext()) {
            JSONObject movieInfo = (JSONObject) iter.next();
            MovieInfo mi = new MovieInfo();
            mi.setMovieCode(movieInfo.isNull("id") ? 0 : movieInfo.getLong("id"));
            mi.setName(movieInfo.isNull("title") ? null :movieInfo.getString("title"));
            mi.setOriginName(movieInfo.isNull("original_title") ?  null : movieInfo.getString("original_title"));
            mi.setPosterUrl(movieInfo.isNull("poster_path") ? null : "https://image.tmdb.org/t/p/original" + movieInfo.getString("poster_path"));
            movies.add(mi);
        }


        return movies;
    }

    // 영화명, 제작년도, 일단 search-movie에서 코드 찾기 
    public Long serchMovie(String movieName, String movieYear) throws IOException{

        String strMovieName = movieName;
        String strYear = movieYear;
        Long lMovieCode = 0L;

        //search-movie 먼저 여기서는 영화 코드만 추출하면 됨.
        OkHttpClient client = new OkHttpClient();
        
        
        // 영화 코드만 추출하는 search-movie
        String reqUrl = String.format("https://api.themoviedb.org/3/search/movie?query=%s&include_adult=false&language=ko-KR&page=1&year=%s", strMovieName, strYear);
                                             
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
        if(arrMovieInfo.isEmpty())
            return null;
        JSONObject jnMovieInfoParts = (JSONObject)iter.next();

        //키 입력하면 값 반환, 아래는 예시(overview라는 키를 입력)
        lMovieCode = jnMovieInfoParts.getLong("id");
        System.out.println("id : "+ lMovieCode);
        

        return lMovieCode;
    }
    

    // 전달받은 코드로 movie-detatil에서 검색, 검색된 결과가 없으면 null을 반환
    public TMDBMovieDetail movieDetail(Long movieCode) throws IOException, InterruptedException{

        //반환할 데이터 객체 생성
        TMDBMovieDetail movieDetail = new TMDBMovieDetail();

        //search-movie 먼저 여기서는 영화 코드만 추출하면 됨.
        OkHttpClient client = new OkHttpClient();
        
        {
            //movie-detail, 추출할 데이터: 
            //(소개글) tmdb, overview, 
            //(포스트) tmdb, poster_path,
            //스틸컷이미지,  backdrop_path
            //트레일러영상,  videos <= 배열 형식으로 받아야 함
            String reqUrl = String.format("https://api.themoviedb.org/3/movie/%d?append_to_response=videos,credits,images&language=ko-KR", movieCode);
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

            // 데이터 추출 작업
            // "results" 키의 값인 JsonArray를 추출
            JSONObject videos = responseBody.getJSONObject("videos");
            JSONArray resultArray = videos.getJSONArray("results");
            Iterator<Object> resultIter = resultArray.iterator();
            List<Result> resultList = new ArrayList<>();
            while (resultIter.hasNext()) {
                JSONObject object = (JSONObject)resultIter.next();
                Result result = new Result();
                result.setKey(object.isNull("key") ? null : ("https://www.youtube.com/embed/" + object.getString("key")));
                result.setName(object.isNull("name") ? null : object.getString("name"));
                result.setPublishedAt(object.isNull("published_at") ? null : object.getString("published_at"));
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
                cast.setCharacter(object.isNull("character") ? null : object.getString("character"));
                cast.setProfilePath(object.isNull("profile_path") ? null : ("https://image.tmdb.org/t/p/original" + object.getString("profile_path")));
                cast.setOriginalName(object.isNull("original_name") ? null : object.getString("original_name"));
                cast.setCastOrder(String.valueOf(object.getLong("order")));
                cast.setPopularity(String.valueOf(object.getDouble("popularity")));
                castList.add(cast);
            }
            movieDetail.setCasts(castList);


            // "crew" 키의 값인 JsonArray를 추출
            JSONArray crewArr = credits.getJSONArray("crew");
            Iterator<Object> crewIter = crewArr.iterator();
            List<Crew> crewList = new ArrayList<>();
            while (crewIter.hasNext()) {
                JSONObject object = (JSONObject)crewIter.next();
                if(object.getString("job").equals("Director")){
                    Crew crew = new Crew();
                    crew.setId(String.valueOf(object.getLong("id")));
                    crew.setGender(String.valueOf(object.getLong("gender")));
                    crew.setProfilePath(object.isNull("profile_path") ? null : ("https://image.tmdb.org/t/p/original" + object.getString("profile_path")));
                    crew.setOriginalName(object.getString("original_name"));
                    crew.setPopularity(String.valueOf(object.getDouble("popularity")));
                    crewList.add(crew);
                }
            }
            movieDetail.setCrews(crewList);

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
            movieDetail.setBackdropPath(responseBody.isNull("backdrop_path") ? null : ("https://image.tmdb.org/t/p/original" + responseBody.getString("backdrop_path")));
            movieDetail.setOverview(responseBody.isNull("overview") ? null : responseBody.getString("overview"));
            movieDetail.setOriginalTitle(responseBody.isNull("original_title") ? null : responseBody.getString("original_title"));
            movieDetail.setRuntime(String.valueOf(responseBody.getLong("runtime")));
            movieDetail.setReleaseDate(responseBody.isNull("release_date") ? null : responseBody.getString("release_date"));
            movieDetail.setPosterPath(responseBody.isNull("poster_path") ? null : ("https://image.tmdb.org/t/p/original" + responseBody.getString("poster_path")));
            movieDetail.setTagLine(responseBody.isNull("tagline") ? null : responseBody.getString("tagline"));

        }

        Thread.sleep(100);
        // 비디오 없으면 다시 받아오기
        if(movieDetail.getResults().size() == 0)
        {
            String reqUrl = String.format("https://api.themoviedb.org/3/movie/%s/videos", movieCode);
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

            // "results" 키의 값인 JsonArray를 추출
            JSONArray resultArray = responseBody.getJSONArray("results");
            Iterator<Object> resultIter = resultArray.iterator();
            List<Result> resultList = new ArrayList<>();
            while (resultIter.hasNext()) {
                JSONObject object = (JSONObject)resultIter.next();
                Result result = new Result();
                result.setKey(object.isNull("key") ? null : ("https://www.youtube.com/embed/" + object.getString("key")));
                result.setName(object.isNull("name") ? null : object.getString("name"));
                result.setPublishedAt(object.isNull("published_at") ? null : object.getString("published_at"));
                resultList.add(result);

                if(resultList.size() == 5)
                    break;
            }
            movieDetail.setResults(resultList);
        }

        Thread.sleep(100);
        // 스틸컷
        {
            String reqUrl = String.format("https://api.themoviedb.org/3/movie/%d/images", movieCode);
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

            // 데이터 추출 작업
            // "backdrops" 키의 값인 JsonArray를 추출, 10개만 저장
            JSONArray images = responseBody.getJSONArray("backdrops");
            Iterator<Object> imagesIter = images.iterator();
            List<String> stillCutList = new ArrayList<>();
            while (imagesIter.hasNext()) {
                JSONObject object = (JSONObject)imagesIter.next();
                stillCutList.add(object.isNull("file_path") ? null : ("https://image.tmdb.org/t/p/original" + object.getString("file_path")));
                if(stillCutList.size() == 10)
                    break;
            }
            movieDetail.setStillCuts(stillCutList);

            // "logos" 키의 값인 JsonArray를 추출
            JSONArray logos = responseBody.getJSONArray("logos");
            Iterator<Object> logosIter = logos.iterator();
            while (logosIter.hasNext()){
                JSONObject object = (JSONObject)logosIter.next();
                String logoNt = object.isNull("iso_639_1") ? "" : object.getString("iso_639_1");
                if(logoNt.equals("ko")){
                    movieDetail.setLogo("https://image.tmdb.org/t/p/original" + object.getString("file_path"));
                    break;
                }
                else if(logoNt.equals("en"))
                    movieDetail.setLogo("https://image.tmdb.org/t/p/original" + object.getString("file_path"));
            }

        }

        return movieDetail;
    }


    // 영화인검색
    public TMDBPersonDetails personDetails(String personId) throws IOException{
        // 채울 데이터
        TMDBPersonDetails personDetails = new TMDBPersonDetails();

        // SocketTimeoutException: timeout 때문에 빌더에서 다시 설정, 되려나?
        OkHttpClient client = new OkHttpClient().newBuilder()
                                    .connectTimeout(100, TimeUnit.SECONDS)
                                    .readTimeout(100, TimeUnit.SECONDS)
                                    .writeTimeout(100, TimeUnit.SECONDS)
                                    .build();

        Request request = new Request.Builder()
        .url("https://api.themoviedb.org/3/person/"+personId+"?language=ko-KR")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyM2U5NWU1MTY0NWUzYjgwZDU0MzQyNGQxYTA5ODg0YSIsInN1YiI6IjY2MDEzYjRmNzcwNzAwMDE2MzBhZjg0MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFfawiMrE8C2YgpGPdaU5IOcl-R5t-JIquRBN6vaLzU")
        .build();

        Response response = client.newCall(request).execute();

        // 응답 데이터를 JSON 형식으로 파싱
        String responseData = response.body().string();

        // JSON 객체로  변환
        JSONObject responseBody = new JSONObject(responseData.toString());

        // "also_known_as" 키의 값인 JsonArray를 추출
        JSONArray nameArr = responseBody.getJSONArray("also_known_as");
        Iterator<Object> nameIter = nameArr.iterator();
        // 배우 여러 이름 받아오는데 영문이랑 한글이 필요함 고민중.
        while (nameIter.hasNext()) {
            Object object = nameIter.next();
            String name = object.toString();
            Pattern pattern = Pattern.compile("[가-힣]+");
            Matcher matcher = pattern.matcher(name);
            if (matcher.find()){
                //이름 저장하는 로직
                personDetails.setKorName(name);
                break;
            } 
        }

        personDetails.setBirthday(responseBody.isNull("birthday") ? null : responseBody.getString("birthday"));
        personDetails.setDeathday(responseBody.isNull("deathday") ? null : responseBody.getString("deathday"));
        personDetails.setPlaceOfBirth(responseBody.isNull("place_of_birth") ? null : responseBody.getString("place_of_birth"));
        personDetails.setProfilePath(responseBody.isNull("profile_path") ? null : responseBody.getString("profile_path"));

        return personDetails;
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        // API 객체 생성
        TMDBMovieAPI api = new TMDBMovieAPI();

        // 년도 스트링형으로 받아서 -테스트
        // String str = "2022";
        // int num = Integer.parseInt(str);
        // num--;
        // str = Integer.toString(num);
        // System.out.println(str);

        // 사람 디테일 마동석:1024395, 티모시:1190668, 
        // TMDBPersonDetails personDetails = new TMDBPersonDetails();
        // personDetails = api.personDetails("1190668");
        // System.out.println(personDetails.getKorName());
        
        // 영화 디테일 찾는 방법(웬만하면 영어 이름으로 찾기)
        // String movieName = "THE ROUNDUP : PUNISHMENT";
        // Long movieCode = api.serchMovie(movieName, "2024");
        // System.out.println(movieCode);

        // 아래는 영화이름하고 년도 쓰면 오버뷰 나오게 함. 영화명은 한글 영문 모두 가능
        // while(true){
        //     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //     String movieName = br.readLine();
        //     String movieYear = br.readLine();
        //     Long movieCode = api.serchMovie(movieName, movieYear);
        //     TMDBMovieDetail entity = api.movieDetail(movieCode);
        //     if(entity != null)
        //         System.out.println(entity.getOverview());
        //     else
        //         System.out.println("영화 없음");
        // }

        // 이름으로만 검색 테스트
        // List<MovieInfo> list = new ArrayList<>();
        // list = api.serchMovies("아바타");
        // for (MovieInfo movieInfo : list) {
        //     System.out.println(movieInfo.toString());
        // }
    }
}
