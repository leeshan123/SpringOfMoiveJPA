package kr.co.moviespring.web.service;

import java.util.List;

import kr.co.moviespring.web.entity.OnelineReview;
import kr.co.moviespring.web.entity.OnelineReviewMovieView;
import kr.co.moviespring.web.entity.OnelineReviewView;

public interface OnelineReviewService {

    void saveComment(String id, String comments ,int rate, Long movieId);

    List<OnelineReviewView> getOnelineReviews(Long movieId);
    List<OnelineReviewMovieView> getByMemberId(Long id);
}
