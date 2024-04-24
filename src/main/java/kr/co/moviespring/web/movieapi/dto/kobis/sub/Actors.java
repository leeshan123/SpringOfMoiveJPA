package kr.co.moviespring.web.movieapi.dto.kobis.sub;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// actors의 자료 구조 => peopleNm(한국명), peopleNmEn(영어명), cast, castEn
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Actors{
    private String peopleNm;	    //배우명
    private String peopleNmEn;	    //배우명(영문)
    private String cast;	        //배역명
    private String castEn;	        //배역명(영문)

    
//    public String getPeopleNm() {
//        return peopleNm;
//    }
//    public void setPeopleNm(String peopleNm) {
//        this.peopleNm = peopleNm;
//    }
//    public String getPeopleNmEn() {
//        return peopleNmEn;
//    }
//    public void setPeopleNmEn(String peopleNmEn) {
//        this.peopleNmEn = peopleNmEn;
//    }
//    public String getCast() {
//        return cast;
//    }
//    public void setCast(String cast) {
//        this.cast = cast;
//    }
//    public String getCastEn() {
//        return castEn;
//    }
//    public void setCastEn(String castEn) {
//        this.castEn = castEn;
//    }
}