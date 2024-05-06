package kr.co.moviespring.web.entity;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OnelineReviewMovieView {
    Long id;
    Date regDate;
    String comments; 
    int memberRate; 
    Long movieId; 
    Long memberId; 
    String title; 
    String url;
}
