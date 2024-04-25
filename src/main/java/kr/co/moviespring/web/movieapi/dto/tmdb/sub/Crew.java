package kr.co.moviespring.web.movieapi.dto.tmdb.sub;

// "crew" 의 자료구조
// "job":"Director" 인지 확인해서 감독인지 확인
public class Crew {
    
    private String id;           // 스텝 ID
    private String originalName; // 이름명
    private String profilePath;  // 스텝 이미지
    private String gender;       // 성별
    private String popularity;   // 스텝 인기도

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public String getPopularity() {
        return popularity;
    }
    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }
}
