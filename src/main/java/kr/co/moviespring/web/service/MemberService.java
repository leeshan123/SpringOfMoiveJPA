package kr.co.moviespring.web.service;


import java.util.List;

import kr.co.moviespring.web.entity.CommunityBoardView;
import kr.co.moviespring.web.entity.Member;

public interface MemberService {
    //로그인 입력정보 검사//
//    boolean validate(String username , String password); //스프링 시큐리티로 인해 불필요?

    //회원가입//
    void regist(Member member);

    //회원정보 변경
    void changeUserInfo(Long id, String nickname, String password, String email);
    List<CommunityBoardView> getMyBoard(Long memberId); // 내 게시글 조회

    boolean getByUsername(String name);
    
}
