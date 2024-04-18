package kr.co.moviespring.web.movieapi;

import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;

import kr.co.moviespring.web.movieapi.KonisMovieInfoEntity.Actors;
import kr.co.moviespring.web.movieapi.KonisMovieInfoEntity.Companys;
import kr.co.moviespring.web.movieapi.KonisMovieInfoEntity.Directors;

//영화 상세 정보
//staffs는 자료가 이상해서 일단 버림
public class KobisMovieInfoEntity {

    private List<String> nationNm; //제작국가명, nations 는 여러개의 nationNm을 가지고 있을 수 있음
    private List<String> genreNm;  //장르명, genres 는 여러 개의 genreNm을 가지고 있을 수 있음
    private List<Actors> actors;
    private List<Directors> directors;
    private List<Companys> companys;
    
    private String movieNm;		    //영화명(국문)
    private String movieNmEn;	    //영화명(영문)
    private String movieNmOg;	    //영화명(원문)
    private String prdtYear;	    //제작연도
    private String showTm;	        //상영시간
    private String openDt;	        //개봉연도
    private String prdtStatNm;	    //제작상태명
    private String typeNm;	        //영화유형명
    
    //audits는 auditNo와 watchGradeNm, 웬만하면 1개일 듯
    private String auditNo;	        //심의번호
    private String watchGradeNm;	//관람등급 명칭

    
    public List<String> getNationNm() {
        return nationNm;
    }

    public void setNationNm(List<String> nationNm) {
        this.nationNm = nationNm;
    }

    public List<String> getGenreNm() {
        return genreNm;
    }

    public void setGenreNm(List<String> genreNm) {
        this.genreNm = genreNm;
    }

    public List<Actors> getActors() {
        return actors;
    }

    public void setActors(List<Actors> actors) {
        this.actors = actors;
    }

    public List<Directors> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Directors> directors) {
        this.directors = directors;
    }

    public List<Companys> getCompanys() {
        return companys;
    }

    public void setCompanys(List<Companys> companys) {
        this.companys = companys;
    }

    public String getMovieNm() {
        return movieNm;
    }

    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }

    public String getMovieNmEn() {
        return movieNmEn;
    }

    public void setMovieNmEn(String movieNmEn) {
        this.movieNmEn = movieNmEn;
    }

    public String getMovieNmOg() {
        return movieNmOg;
    }

    public void setMovieNmOg(String movieNmOg) {
        this.movieNmOg = movieNmOg;
    }

    public String getPrdtYear() {
        return prdtYear;
    }

    public void setPrdtYear(String prdtYear) {
        this.prdtYear = prdtYear;
    }

    public String getShowTm() {
        return showTm;
    }

    public void setShowTm(String showTm) {
        this.showTm = showTm;
    }

    public String getOpenDt() {
        return openDt;
    }

    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }

    public String getPrdtStatNm() {
        return prdtStatNm;
    }

    public void setPrdtStatNm(String prdtStatNm) {
        this.prdtStatNm = prdtStatNm;
    }

    public String getTypeNm() {
        return typeNm;
    }

    public void setTypeNm(String typeNm) {
        this.typeNm = typeNm;
    }

    public String getAuditNo() {
        return auditNo;
    }

    public void setAuditNo(String auditNo) {
        this.auditNo = auditNo;
    }

    public String getWatchGradeNm() {
        return watchGradeNm;
    }

    public void setWatchGradeNm(String watchGradeNm) {
        this.watchGradeNm = watchGradeNm;
    }

}


