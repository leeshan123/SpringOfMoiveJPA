package kr.co.moviespring.web.movieapi.dto.kobis;

import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;

import kr.co.moviespring.web.movieapi.dto.kobis.sub.Actors;
import kr.co.moviespring.web.movieapi.dto.kobis.sub.Audits;
import kr.co.moviespring.web.movieapi.dto.kobis.sub.Companys;
import kr.co.moviespring.web.movieapi.dto.kobis.sub.Directors;


//영화 상세 정보
//staffs는 자료가 이상해서 일단 버림
public class KobisMovieInfo {

    private List<String> nationNm;      //제작국가명
    private List<String> genreNm;       //장르명
    private List<Actors> actors;        //배우명
    private List<Directors> directors;  //감독명
    private List<Companys> companys;    //참여 영화사
    private List<Audits> audits;        //심의정보
    
    private String movieNm;		    //영화명(국문)
    private String movieNmEn;	    //영화명(영문)
    private String movieNmOg;	    //영화명(원문)
    private String prdtYear;	    //제작연도
    private String showTm;	        //상영시간
    private String openDt;	        //개봉연도
    private String prdtStatNm;	    //제작상태명
    private String typeNm;	        //영화유형명
    
    
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

    public List<Audits> getAudits() {
        return audits;
    }

    public void setAudits(List<Audits> audits) {
        this.audits = audits;
    }

}


