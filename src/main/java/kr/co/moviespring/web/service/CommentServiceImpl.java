package kr.co.moviespring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.OnelineReview;
import kr.co.moviespring.web.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentrepository;

    // 한줄평 등록//
    @Override
    public void SaveComment(String comments, int rate, Long movieId) {
        commentrepository.Save(comments, rate, movieId);
    }

    // 한줄평 목록//
    @Override
    public OnelineReview getOnelineReviews(Long movieId) {
        OnelineReview onelineReviews = commentrepository.getlist(movieId);
        return onelineReviews;
    }
}
