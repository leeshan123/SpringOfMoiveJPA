package kr.co.moviespring.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieSearchView {
    private Long movieId;
    private String korName; //영화상세정보(한글이름) > movieNm
    private String engName; //영화상세정보(영문) > movieNmEn
    private Integer reviewCount;
    private Double averageRate;
}
