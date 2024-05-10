package kr.co.moviespring.web.entity;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityBoardCommentsView {

 private Long id;
 private Long memberId;
 private Long communityBoardId;
 private Long communityBoardCategoryId;
 private String communityBoardTitle;
 private String contents;
 private String nickname;
 private LocalDateTime regDate;

}
