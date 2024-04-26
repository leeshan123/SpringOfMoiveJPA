package kr.co.moviespring.web.entity;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class People {
    private String peopleCd;
    private String peopleNm;
    private String peopleNmEn;
    private String repRoleNm;
    private String filmoNames;

}
