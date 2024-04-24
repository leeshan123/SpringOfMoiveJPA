package kr.co.moviespring.web.movieapi.dto.tmdb;

public class TMDBPersonDetails {
    private String korName;
    private String birthday;
    private String deathday;
    private String placeOfBirth;
    private String profilePath;

    
    public String getKorName() {
        return korName;
    }
    public void setKorName(String korName) {
        this.korName = korName;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getDeathday() {
        return deathday;
    }
    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }
    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    public String getProfilePath() {
        return profilePath;
    }
    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    
}
