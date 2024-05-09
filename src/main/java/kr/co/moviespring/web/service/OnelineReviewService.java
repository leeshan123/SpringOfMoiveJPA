package kr.co.moviespring.web.service;

import java.util.List;

import kr.co.moviespring.web.entity.OnelineReview;
import kr.co.moviespring.web.entity.OnelineReviewMovieView;
import kr.co.moviespring.web.entity.OnelineReviewView;

public interface OnelineReviewService {

    void saveComment(String id, String comments ,int rate, Long movieId);

    List<OnelineReviewView> getList(Long movieId);
    List<OnelineReviewMovieView> getListByMemberId(Long id);
    // 회원이 작성한 리뷰 가져오기
    OnelineReview getById(Long movieId, Long memberId);
}
