package kr.co.moviespring.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie3 {
    
    private String movieCd;
    private String movieNm;
    private String openDt;
    private String repGenreNm;
    private String audiAcc;
    private String movieNmEn;
    private String nationAlt;


    public String getMovieCd() {
        return movieCd;
    }
    public void setMovieCd(String movieCd) {
        this.movieCd = movieCd;
    }
    public String getMovieNm() {
        return movieNm;
    }
    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }
    public String getOpenDt() {
        return openDt;
    }
    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }
    public String getRepGenreNm() {
        return repGenreNm;
    }
    public void setRepGenreNm(String repGenreNm) {
        this.repGenreNm = repGenreNm;
    }
    public String getAudiAcc() {
        return audiAcc;
    }
    public void setAudiAcc(String audiAcc) {
        this.audiAcc = audiAcc;
    }
    public String getMovieNmEn() {
        return movieNmEn;
    }
    public void setMovieNmEn(String movieNmEn) {
        this.movieNmEn = movieNmEn;
    }
    public String getNationAlt() {
        return nationAlt;
    }
    public void setNationAlt(String nationAlt) {
        this.nationAlt = nationAlt;
    }
    public String getPrdYear() {
        return prdYear;
    }
    public void setPrdYear(String prdYear) {
        this.prdYear = prdYear;
    }
    public String getDirectorNm() {
        return directorNm;
    }
    public void setDirectorNm(String directorNm) {
        this.directorNm = directorNm;
    }
    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    private String prdYear;
    private String directorNm;
    private String companyCd;
}
