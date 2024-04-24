package kr.co.moviespring.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    private Long id;
    private String kobisId;
    private String tmdbId;
    private String korName;
    private String engName;
    private String imgUrl;
}
