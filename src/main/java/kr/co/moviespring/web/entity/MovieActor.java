package kr.co.moviespring.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieActor {
    private Long movieId;
    private Long actorId;
    private Long order;
    private String castKorName;
    private String castEngName;
}
