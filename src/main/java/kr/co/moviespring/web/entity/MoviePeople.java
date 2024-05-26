package kr.co.moviespring.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoviePeople {
    private String peopleCd; // 사람코드
    private String peopleNm; // 사람 한국 이름
    private String peopleNmEn; // 사람 영어 이름
    private String sex; //성별
    private String repRoleNm; //대표적으로 뭐냐
    private String movieCd; // 영화코드
    private String movieNm; // 영화 한국어명
    private String moviePartNm; // 영화 배역



}
