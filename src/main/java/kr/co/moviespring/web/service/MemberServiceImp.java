package kr.co.moviespring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.CommunityBoardView;
import kr.co.moviespring.web.entity.Member;
import kr.co.moviespring.web.repository.CommunityBoardRepository;
import kr.co.moviespring.web.repository.MemberRepository;

@Service
public class MemberServiceImp implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CommunityBoardRepository communityBoardRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 로그인정보 검사하기// 스프링 시큐리티로 인해 불필요?
//    @Override
//    public boolean validate(String username,String password) {
//        Member member = memberRepository.findByMembername(username);
//        if (member == null) {
//            return false;
//        }
//
//        if (!member.getPassword().equals(bCryptPasswordEncoder.encode(password))) {
//            System.out.println(bCryptPasswordEncoder.encode(password));
//            System.out.println(bCryptPasswordEncoder.encode(password));
//            return false;
//        }
//        return true;
//    }

    // 회원 가입//
    @Override
    public void regist(Member member) {
        
        //db에 동일한 이름의 유저가 이미 존재하는지 검증하는코드 필요함

        Member data = new Member();
        //data.setId(member.getId());
        data.setUsername(member.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        data.setName(member.getName());
        data.setNickname(member.getNickname());
        data.setAge(member.getAge());
        data.setEmail(member.getEmail());
        //data.setPoint(member.getPoint());
        //data.setRegDate(member.getRegDate());
        //data.setWithDrawn(member.isWithDrawn());
        data.setRole("ROLE_MEMBER");

        memberRepository.regist(data);
    }

    @Override
    public void changeUserInfo(Long id, String nickname, String password, String email) {
        Member member = new Member();
        member.setId(id);
        member.setNickname(nickname);
        member.setPassword(password == null ? null : bCryptPasswordEncoder.encode(password));
        member.setEmail(email);
        memberRepository.update(member);
    }

    @Override
    public List<CommunityBoardView> getMyBoard(Long memberId) {
        List<CommunityBoardView> list = communityBoardRepository.findByMemberId(memberId);
        return list;
    }

    @Override
    public boolean getByUsername(String name) {
        Member member = memberRepository.findByMembername(name);

        if(member == null)
            return false;

        return true;
    }

}
