package kr.co.moviespring.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie2 {
    private int movieCd; // 영화 코드(PK)
    private String movieNm; // 영화한국이름
    private String openDt; //날짜 일단 string으로
    private int salesAmt; //해당 매출액
    private int audiCnt; //해당 관객수
    private int audiAcc; // 누적 관객수


//    @Override
//    public String toString() {
//        return "Movie2{" +
//                "movieCd=" + movieCd +
//                ", movieNm='" + movieNm + '\'' +
//                ", openDt='" + openDt + '\'' +
//                ", salesAmt=" + salesAmt +
//                ", audiCnt=" + audiCnt +
//                ", audiAcc=" + audiAcc +
//                '}';
//    }
}
