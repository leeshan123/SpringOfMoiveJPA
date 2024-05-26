package kr.co.moviespring.web.service;


import java.util.List;
import java.util.Optional;

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

    void removeById(Long id);

    List<Member> getList(Integer page, Integer status);

    int getCount(Integer status);

    Member getById(long memberId);

    void banById(Long memberId);

    void restoreById(Long memberId);

    Long verifyUser(String username, String email);
    
}
