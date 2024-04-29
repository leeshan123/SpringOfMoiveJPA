package kr.co.moviespring.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieActorView {

    private Long actorId;
    private String korName;
    private String engName;
    private String castEngName;
    private String imgUrl;
    private Double popularity;

}
