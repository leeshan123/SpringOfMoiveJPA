package kr.co.moviespring.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityBoardCommentsView {

 private Long id;
 private Long memberId;
 private Long communityBoardId;
 private Long communityBoardCategoryId;
 private int likeCount;
 private int disLikeCount; //db에는 dislike라고 정의 되어있는데 가져오는게 신기함
 private Integer commentStatus; // 로그인한 상태라면 해당멤버의 댓글 좋아요 여부 / 1 = 좋아요, -1 = 싫어요, 0 = 투표안함
 private String communityBoardTitle;
 private String contents;
 private String nickname;
 private LocalDateTime regDate;

}
