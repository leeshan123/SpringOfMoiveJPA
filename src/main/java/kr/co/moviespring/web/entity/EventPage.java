package kr.co.moviespring.web.entity;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventPage {
    private Long id;
    private String title;
    private String contents;
    private Date regDate;
    private Date startDate;
    private Date endDate;
    private int hit;
    private String imageUrl;
    private Long memberId;
    private Long eventPageCategoryId;
}
