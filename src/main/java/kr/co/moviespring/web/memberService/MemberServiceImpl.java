package kr.co.moviespring.web.memberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.Member;
import kr.co.moviespring.web.memberRepository.MemberRepository;
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public Member membership(String userId, String pwd,String name , String nickname, int age, String email) {
        Member member = memberRepository.regist(userId,pwd,name,nickname,age,email);
        return member;
    }
    
}
