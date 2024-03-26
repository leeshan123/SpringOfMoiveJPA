package kr.co.moviespring.web.entity;

import java.util.Date;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OnelineReview {

    private Long id;
    private Date reg_date;
    private String comments;
    private int memberRate;
    private Long movieId;
    private Long memberId;
}
