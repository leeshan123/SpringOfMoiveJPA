package kr.co.moviespring.web.repository;


import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.OnelineReview;

@Mapper
public interface CommentRepository {
    OnelineReview Save(String comments,int rate,Long movieId);
    
}
