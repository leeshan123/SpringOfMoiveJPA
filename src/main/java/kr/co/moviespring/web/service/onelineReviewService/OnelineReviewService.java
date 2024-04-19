package kr.co.moviespring.web.service.onelineReviewService;

import java.util.List;

import kr.co.moviespring.web.entity.OnelineReview;

public interface OnelineReviewService {

    void saveComment(String id, String comments ,int rate, Long movieId);

    List<OnelineReview> getOnelineReviews(Long movieId);
    
}
