package kr.co.moviespring.web.movieapi.dto.tmdb.sub;

// 이름, 포스터, 무비코드(PK)만 받아서 출력하기
public class MovieInfo {
    private String name;
    private String originName;
    private String posterUrl;
    private Long movieCode;

    
    @Override
    public String toString() {
        return "MovieInfo [name=" + name + ", originName=" + originName + ", posterUrl=" + posterUrl + ", movieCode="
                + movieCode + "]";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPosterUrl() {
        return posterUrl;
    }
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
    public Long getMovieCode() {
        return movieCode;
    }
    public void setMovieCode(Long movieCode) {
        this.movieCode = movieCode;
    }
    public String getOriginName() {
        return originName;
    }
    public void setOriginName(String originName) {
        this.originName = originName;
    }
}
