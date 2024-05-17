package kr.co.moviespring.web.config.security;

import kr.co.moviespring.web.entity.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class WebOAuth2UserDetailsService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest);

        System.out.println("---------------------------authorities-------------");
        System.out.println(oAuth2User.getAuthorities());
        System.out.println("---------------------------attributes-------------");
        System.out.println(oAuth2User.getAttributes());
        System.out.println("---------------------------name-------------");
        System.out.println(oAuth2User.getName());


        System.out.println("---------------------------AccessToken-------------");
        System.out.println(userRequest.getAccessToken());
        System.out.println("---------------------------token-------------");
        System.out.println(userRequest.getClientRegistration().getRegistrationId());

//        Member member = new Member();
//        CustomUserDetails userDetails = new CustomUserDetails();
//        userDetails.setAttributes(oAuth2User.getAttributes());
//        userDetails.setName(oAuth2User.getName());
//        userDetails.getUsername();
//        userDetails.setNickname(oAuth2User.getName());
        return oAuth2User; //userDetails
    }
}
