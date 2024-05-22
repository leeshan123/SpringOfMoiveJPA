package kr.co.moviespring.web.entity;

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




}
