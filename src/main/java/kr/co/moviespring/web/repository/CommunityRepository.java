package kr.co.moviespring.web.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityRepository {

    void saveContents(String title, String contents);

}
