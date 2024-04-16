package kr.co.moviespring.web.entity;

import java.util.Date;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TwoWeeksMovie {
    
    private Long id;
    private String title;
    private String content;
    private Date regDate; 
    private int hit;
    private int vote;
    private int adminId;

}
