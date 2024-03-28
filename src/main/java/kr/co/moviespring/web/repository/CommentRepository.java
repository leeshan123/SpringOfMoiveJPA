package kr.co.moviespring.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.OnelineReview;

@Mapper
public interface CommentRepository {
    // 한줄평 등록//
    OnelineReview Save(String comments, int rate, Long movieId);

    // 한줄평 목록//
    OnelineReview getlist(Long movieId);

}
