package kr.co.moviespring.web.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityRepository {
    // 게시글 등록//
    void saveContents(String title, String contents);

}
