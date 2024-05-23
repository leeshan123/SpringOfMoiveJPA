package kr.co.moviespring.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.Member;

@Mapper
public interface MemberRepository {
    Member findByMembername(String username);

    Member findByEmail(String email);

    void regist(Member member);
    void update(Member member);

    void updatePoint(Member member);

    void delete(Long id);

    List<Member> findAll(int offset, int size);

    int getCount();

//    String username, String password, String name, String nickname, int age, String email
  
}
