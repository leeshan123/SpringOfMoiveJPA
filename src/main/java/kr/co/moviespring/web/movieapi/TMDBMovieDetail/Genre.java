package kr.co.moviespring.web.movieapi.TMDBMovieDetail;

// 장르
public class Genre {
    private String id;    // 장르 아이디
    private String name;  // 장르 명
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
