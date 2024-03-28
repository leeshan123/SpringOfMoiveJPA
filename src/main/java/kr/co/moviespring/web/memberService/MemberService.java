package kr.co.moviespring.web.memberService;

import kr.co.moviespring.web.entity.Member;

public interface MemberService {
    //로그인 입력정보 검사//
    boolean validate();

    //회원가입//
    Member membership(String userId, String pwd, String nickname, String nickname2, int age, String email);
    
}
