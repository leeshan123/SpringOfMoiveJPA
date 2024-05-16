package kr.co.moviespring.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityBoardCommentsLike {
    private Long communityBoardCommentsId;
    private Long memberId;
    private int like;
    private LocalDateTime regDate;
}
