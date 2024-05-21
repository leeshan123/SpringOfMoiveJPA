package kr.co.moviespring.web.config.security;

import kr.co.moviespring.web.entity.Member;
import kr.co.moviespring.web.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class WebOAuth2UserDetailsService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private MemberRepository repository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest);


        String email = oAuth2User.getAttribute("email");
        Member member = repository.findByEmail(email);
        String username = oAuth2User.getAttribute("name");


//        CustomUserDetails userDetails = new CustomUserDetails(member); -
//        userDetails.setAttributes(oAuth2User.getAttributes()); -

//        System.out.println(oAuth2User.getAttributes()+"======================sdafkjashdksjdhfk");
//        System.out.println(name);
//        System.out.println(oAuth2User.getAttributes().get("name"));
//        userDetails.setName(oAuth2User.getName());

//        userDetails.setUsername(username); - //그럼 구글 로그인 할떄마다 구글 attribute의 name값이 계속 덮어씌워지는건지? 아 이건 유저디테일즈의 유저네임인듯?
        // 1. 회원이 존재하지 않으면
        // 기본 정보만 담아서 반환하고
        if (member == null) { //로컬 회원으로 가입한적이 없다 ,
            // 실패하는 경우를 먼저 적어주는게 좋다, 보통 실패할경우 리턴 한줄로 끝나기에 깔끔하다
            // 중괄호가 커질수록 복잡해지기 때문
            member = Member.builder().name(oAuth2User.getName()).email(email).username(username).build();
            CustomUserDetails userDetails = new CustomUserDetails(member);
            userDetails.setAttributes(oAuth2User.getAttributes());


            return userDetails;
        }


////        Member member = new Member();
////        CustomUserDetails userDetails = new CustomUserDetails();
//        userDetails.setAttributes(oAuth2User.getAttributes());
////        userDetails.
//        userDetails.setName(oAuth2User.getName());
////        userDetails.getUsername();
//        userDetails.setEmail(email);
//        userDetails.setNickname(oAuth2User.getName());
        return oAuth2User; //oAuth2User
    }
}
