package kr.co.moviespring.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.OnelineReview;
import kr.co.moviespring.web.entity.OnelineReviewView;

@Mapper
public interface OnelineReviewRepository {
    // 한줄평 등록//
    void save(Long id, String comments, int rate, Long movieId);

    // 한줄평 목록//
    List<OnelineReviewView> getlist(Long movieId);

}
