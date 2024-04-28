package kr.co.moviespring.web.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieStillcut {
    private Long id;
    private Long movieId;
    private String url;
}
