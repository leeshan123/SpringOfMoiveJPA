package kr.co.moviespring.web.movieapi.dto.kobis.sub;


import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// directors의 자료 구조 => peopleNm(한국명), peopleNmEn(영어명)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Directors {
    private String peopleNm;	 //감독명
    private String peopleNmEn;	//감독명(영문)

//
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
}
