package kr.co.moviespring.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    
 private Long id;
 private String username;
 private String password;
 private String name;
 private String nickname;
 private int age;
 private String email;
 private int point;
 private Date regDate;
 private int Status; // tinyint(1)
 private String profileImageUrl;
 private String role;
}
