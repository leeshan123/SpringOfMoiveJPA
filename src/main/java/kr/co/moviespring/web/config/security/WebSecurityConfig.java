package kr.co.moviespring.web.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private WebOAuth2UserDetailsService webOAuth2UserDetailsService;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .csrf(csrf -> csrf.disable()) // 포스트 요청을 보낼때 csrf를 방지하고자 토큰을 비교하는 설정을 끈다
               .authorizeHttpRequests((requests) -> requests
                       .requestMatchers("/user/signup").permitAll()
                       .requestMatchers("/user/findid").permitAll()
                       .requestMatchers("/user/findpwd").permitAll()
                       .requestMatchers("/user/findid-result").permitAll()
                       .requestMatchers("/user/change-pwd").permitAll()
                       .requestMatchers("/user/change-pwd-result").permitAll()
                       .requestMatchers("/user/welcome").permitAll()
                       .requestMatchers("/user/**").hasAnyRole("MEMBER","ADMIN") //역할별 권한설정, 멤버와 관리자 모두 접속가능
                       .requestMatchers("/admin/**").hasRole("ADMIN")//관리자만 접속가능
                       .anyRequest().permitAll() //위에서부터 우선순위가 적용됨
               )
               .formLogin((form) -> form
//                       .successHandler(loginSuccessHandler) //로그인후 유저가 원래 가려던 페이지로 리다이렉트하는 설정
                       .loginPage("/user/signin")
                       .loginProcessingUrl("/user/signin")
                       .defaultSuccessUrl("/")
                       .permitAll())
               .oauth2Login(config->config //구글 소셜 로그인 설정
                       .userInfoEndpoint(userInf->userInf
                               .userService(webOAuth2UserDetailsService)))
//                       .successHandler(loginSuccessHandler))
               //이곳에서만 쓸꺼면 그냥 new해서 바로 쓰면 되고 여러곳에서 사용하려면 변수로 만들어 주면 된다
               // 로그인 성공후 추가적으로 사용할 로직 호출..
               // 관리자로 로그인하면 관리자페이지로 바로이동 한다던지 로컬서버에 가입이 안되있는 유저라면 가입페이지로 이동시킨다던지....
               //default url속성과 같은 기능이지만 핸들러는 추가적인 작업이나 여러 조건에 따라 리다이렉트 시킬수 있다, 내 입맛대로 손질가능
               .logout((logout) -> logout
               .logoutUrl("/user/signout")
               .logoutSuccessUrl("/")
               .permitAll());

       return http.build();
   }

//   @Bean
//   public UserDetailsService userDetailsService() {
//       UserDetails user = User.builder()
//               .username("testif")
//               .password("{noop}333") //비번을 넣으려면 암호화를 해줘서 넣어야 한다 ,그게 아니라면 noop을 포함해서 비번을 작성
//               .roles("MEMBER", "ADMIN")
//               .build();
////
////        UserDetails user2 = User.builder()
////                .username("dragon")
////                .password("{noop}222")
////                .roles("MEMBER")
////                .build();
//       return new InMemoryUserDetailsManager(user);
//   }

}
