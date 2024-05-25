package kr.co.moviespring.web.config.security;

import kr.co.moviespring.web.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import groovyjarjarantlr4.v4.parse.ANTLRParser.ruleAction_return;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomUserDetails implements UserDetails, OAuth2User {

    private Member member;

//    public CustomUserDetails(){
//    }

    public CustomUserDetails(Member member) {

        this.member = member;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });
        return collection;
    }
    public Long getId(){
        return member.getId();
    }

    public void setId(Long Id){
        this.member.setId(Id);
    }

    public String getNickname() {
        return member.getNickname();
    }

    public void setNickname(String nickname) {
        this.member.setNickname(nickname);
//        this.nickname = nickname;
    }

    public String getName() {
        return member.getName();
    }

    public void setName(String name) {
        this.member.setName(name);
    }

    public int getAge() {
        return member.getAge();
    }

    public void setAge(int age) {
        this.member.setAge(age);
    }

    public int getStatus(){
        return member.getStatus();
    }

    public void setStatus(int status){
        this.member.setStatus(status);
    }

    public String getEmail() {
        return member.getEmail();
    }

    public void setEmail(String email) {
        this.member.setEmail(email);
    }

    public int getPoint() {
        return member.getPoint();
    }

    public void setPoint(int point) {
        this.member.setPoint(point);
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    public void setUsername(String username) { this.member.setUsername(username); }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //구글 로그인 설정
    private Map<String, Object> attributes;
//    private String name;


    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }


}
