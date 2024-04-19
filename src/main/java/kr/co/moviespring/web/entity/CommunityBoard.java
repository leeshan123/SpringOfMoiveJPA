package kr.co.moviespring.web.entity;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityBoard {
    
 private Long id;
 private String title;
 private String contents;
 private Date regDate;
 private int hit;
 private Long memberId;
 private Long categoryId;
 private String imageUrl;

}
