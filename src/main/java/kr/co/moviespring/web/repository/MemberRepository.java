package kr.co.moviespring.web.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.Member;

@Mapper
public interface MemberRepository {
    Member findByMembername(String username);

    Member findByEmail(String email);

    //닉네임 있나 여부
    Member findByNickname(String nickname);


    void regist(Member member);
    void update(Member member);

    void updatePoint(Member member);

    void delete(Long id);

    List<Member> findAll(int offset, int size, Integer status);

    int getCount(Integer status);

    Member findById(long memberId);

    Long findByEmailAndName(String username, String email);

//    String username, String password, String name, String nickname, int age, String email
  
}
