package kr.co.moviespring.web.movieapi;

public class MovieUrlEntity {

    private Long id;
    private String overView;
    private String posterUrl;
    private String stillcutUrl;
    private String trailerUrl;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOverView() {
        return overView;
    }
    public void setOverView(String overView) {
        this.overView = overView;
    }
    public String getPosterUrl() {
        return posterUrl;
    }
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
    public String getStillcutUrl() {
        return stillcutUrl;
    }
    public void setStillcutUrl(String stillcutUrl) {
        this.stillcutUrl = stillcutUrl;
    }
    public String getTrailerUrl() {
        return trailerUrl;
    }
    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }
    
    
}
