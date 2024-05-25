package kr.co.moviespring.web.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Betting {

    Long id;
    int bettingPoint;
    int successPoint;
    Long memberId;
    Long bettingBoardId;
    LocalDateTime regDate;
    int choose;




}
