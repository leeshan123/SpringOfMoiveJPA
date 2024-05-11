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
public class totalVoteView {

    private Long id;
    private String tmdbId;
    private String kobisId;
    private String movieCd;
    private Date regDate;
    private String korName;
    private String engName;
    private String sponsor;
    private Date releaseDate;
    private int runningTime;
    private String watchGrade;
    private String movieIntro;
    private String posterUrl;
    private String logoUrl;
    private String mainImgUrl;
    private String releaseNation;
    private String genre;
    private Long voteCount;
    
    
}