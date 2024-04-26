package kr.co.moviespring.web.entity;

import kr.co.moviespring.web.service.MovieInsertServiceImp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//롬복에서 주는 기능
@Data //게터 세터 ToString 생성자 해쉬코드 이퀄을 제공해준다.
@Builder //객체 생성 과정을 단순화 , 가독성을 높힘, 유연성을 확보, 불변성을 확보.
@NoArgsConstructor // 기본 생성자를 자동으로 생성해줌.
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자를 자동으로 생성해줌.
public class Movie2 {
    private String movieCd; // 영화 코드(PK)
    private String movieNm; // 영화한국이름
    private String movieNmEn; // 영화영어이름
    private String prdtYear; // 제작년도
    private String openDt; //날짜 일단 string으로
    private int salesAmt; //해당 매출액
    private int audiCnt; //해당 관객수
    private int audiAcc; // 누적 관객수
    private  String repGenreNm; //대표 장르
    private String nationAlt; //제작국가전부
    private List<Director> directors; // 감독 리스트
    private List<Company> companys; // 회사 nm 리스트
    private  String directorNm; //데이터 넣을거
    private String companyCd; // 데이터 넣을거

    public class Director {
        private String peopleNm;

        public String getPeopleNm() {
            return peopleNm;
        }
    }

    public class Company {
        private String companyCd;

        public String getCompanyCd() {
            return companyCd;
        }
    }
}
