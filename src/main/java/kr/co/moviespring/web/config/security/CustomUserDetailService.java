package kr.co.moviespring.web.config.security;

import kr.co.moviespring.web.entity.Member;
import kr.co.moviespring.web.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = repository.findByMembername(username);

        if (member != null) {

            return new CustomUserDetails(member);
        }


        return null;
    }
}
