package kr.co.moviespring.web.service;


import kr.co.moviespring.web.entity.OnelineReview;

public interface CommentService {

    OnelineReview SaveComment(String comments ,int rate, Long movieId);
    
}
