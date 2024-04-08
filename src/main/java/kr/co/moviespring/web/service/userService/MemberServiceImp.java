package kr.co.moviespring.web.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.Member;
import kr.co.moviespring.web.repository.MemberRepository;

@Service
public class MemberServiceImp implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

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
//        String userId, String pwd, String name, String nickname, int age, String email

        //db에 동일한 이름의 유저가 이미 존재하는지 검증하는코드 필요함

        Member data = new Member();
//        data.setId(member.getId());
        data.setUsername(member.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        data.setName(member.getName());
        data.setNickname(member.getNickname());
        data.setAge(member.getAge());
        data.setEmail(member.getEmail());
//        data.setPoint(member.getPoint());
//        data.setRegDate(member.getRegDate());
//        data.setWithDrawn(member.isWithDrawn());
        data.setRole("ROLE_MEMBER");

        memberRepository.regist(data);
    }

}
