package kr.co.moviespring.web.entity;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityBoardView {

 private Long id;
 private String title;
 private String contents;
 private String imageUrl;
 private Date regDate;
 private int hit;
 private Long memberId;
 private Long categoryId;
 private String nickname;
 private Long commentCount;
 private Long likeCount;

}
