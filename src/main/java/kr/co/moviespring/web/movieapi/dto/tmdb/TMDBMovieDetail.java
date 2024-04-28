package kr.co.moviespring.web.movieapi.dto.tmdb;

import java.util.List;

import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Cast;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Crew;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Genre;
import kr.co.moviespring.web.movieapi.dto.tmdb.sub.Result;

public class TMDBMovieDetail {
    private List<Result> results;   // 유튜브 미리보기
    private List<Cast> casts;       // 캐스팅 배우 정보
    private List<Crew> crews;       // 스텝 정보, 감독만 빼올거임
    private List<Genre> genres;     // 장르
    private List<String> productCountries;  // 제작국가
    private List<String> originCountries;   // 원래 국가?
    private List<String> stillCuts; //스틸컷

    private String id;            // 영화 ID
    private String title;         // 영화명
    private String backdropPath;  // 스틸컷
    private String overview;      // 영화 소개글
    private String originalTitle; // 영화 원제목
    private String runtime;       // 상영시간
    private String releaseDate;   // 개봉일
    private String posterPath;    // 메인 포스터
    private String tagLine;       // 영화 한마디 소개

    public List<Result> getResults() {
        return results;
    }
    public void setResults(List<Result> results) {
        this.results = results;
    }
    public List<Cast> getCasts() {
        return casts;
    }
    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }
    public List<Genre> getGenres() {
        return genres;
    }
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
    public List<String> getProductCountries() {
        return productCountries;
    }
    public void setProductCountries(List<String> productCountries) {
        this.productCountries = productCountries;
    }
    public List<String> getOriginCountries() {
        return originCountries;
    }
    public void setOriginCountries(List<String> originCountries) {
        this.originCountries = originCountries;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBackdropPath() {
        return backdropPath;
    }
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getOriginalTitle() {
        return originalTitle;
    }
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
    public String getRuntime() {
        return runtime;
    }
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String getPosterPath() {
        return posterPath;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    public String getTagLine() {
        return tagLine;
    }
    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }
    public List<Crew> getCrews() {
        return crews;
    }
    public void setCrews(List<Crew> crews) {
        this.crews = crews;
    }
    public List<String> getStillCuts() {
        return stillCuts;
    }
    public void setStillCuts(List<String> stillCuts) {
        this.stillCuts = stillCuts;
    }
}
