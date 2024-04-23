package kr.co.moviespring.web.movieapi.TMDBMovieDetail;

// "cast" 의 자료구조
public class Cast {

    private String id;           // 배우 ID
    private String character;    // 역중 인물명
    private String originalName; // 배우명
    private String profilePath;  // 배우 이미지
    private String gender;       // 성별
    private String order;        // 캐스팅 중요도 순서
    private String popularity;   // 배우 인기도


    public String getOrder() {
        return order;
    }
    public void setOrder(String order) {
        this.order = order;
    }
    public String getPopularity() {
        return popularity;
    }
    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCharacter() {
        return character;
    }
    public void setCharacter(String character) {
        this.character = character;
    }
    public String getOriginalName() {
        return originalName;
    }
    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }
    public String getProfilePath() {
        return profilePath;
    }
    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
