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

    @Override
    public OnelineReview SaveComment(String comments,int rate,Long movieId) {

        OnelineReview comment = commentrepository.Save(comments,rate,movieId);

        return comment;
    }
}
