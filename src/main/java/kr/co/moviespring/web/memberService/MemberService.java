package kr.co.moviespring.web.memberService;

import kr.co.moviespring.web.entity.Member;

public interface MemberService {

    boolean validate();


    Member membership(String userId, String pwd, String nickname, String nickname2, int age, String email);
    
}
