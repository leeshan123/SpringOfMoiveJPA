package kr.co.moviespring.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private Long id;
    private String korName;
    private String engName;
    private String sponsor;
    private Date releaseDate;
    private int totalRate;
    private int totalBoxoffice;
    private int totalSales;
    private int dailySales;
    private int totalAudience;
    private int dailyAudience;
    private String movieIntro;
    private String posterUrl;
    private String stillcutUrl;
    private String trailerUrl;
    private Long releaseNationId;
    private Long genreId;
}
