package kr.co.moviespring.web.entity;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityBoardComments {

 private Long id;
 private Long memberId;
 private Long communityBoardId;
 private String contents;
 private LocalDateTime regDate;
}
