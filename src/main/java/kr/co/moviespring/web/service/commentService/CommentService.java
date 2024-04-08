package kr.co.moviespring.web.service.commentService;

import java.util.List;

import kr.co.moviespring.web.entity.OnelineReview;

public interface CommentService {

    void saveComment(String id, String comments ,int rate, Long movieId);

    List<OnelineReview> getOnelineReviews(Long movieId);
    
}
