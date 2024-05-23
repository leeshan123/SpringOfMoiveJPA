package kr.co.moviespring.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayGroundBoard {

    Long id;
    String title;
    String movieId;
    String bettingTitle;
    Date voteEndDate;
    Date deadLineDate;
    int leftBettingPoint;
    int rightBettingPoint;
    double leftDividend;
    double RightDividend;
    int hit;
    Long adminId;
    String posterUrl;

    @Builder
    public PlayGroundBoard(String title, String movieId, String bettingTitle, Date voteEndDate, Date deadLineDate, Long adminId) {
        this.title = title;
        this.movieId = movieId;
        this.bettingTitle = bettingTitle;
        this.voteEndDate = voteEndDate;
        this.deadLineDate = deadLineDate;
        this.adminId = adminId;
    }
}
