package kr.co.moviespring.web.service;

import java.util.List;

import kr.co.moviespring.web.entity.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.OnelineReview;
import kr.co.moviespring.web.entity.OnelineReviewMovieView;
import kr.co.moviespring.web.entity.OnelineReviewView;
import kr.co.moviespring.web.repository.OnelineReviewRepository;
import kr.co.moviespring.web.repository.MemberRepository;

@Service
public class OnelineReviewServiceImp implements OnelineReviewService {

    @Autowired
    OnelineReviewRepository onelineReviewRepository;

    @Autowired
    MemberRepository memberRepository;

    // 한줄평 등록//
    @Override
    public void saveComment(String id, String comments, int rate, Long movieId) {
        Member member = memberRepository.findByMembername(id);
        onelineReviewRepository.save(member.getId(), comments, rate, movieId);
    }

    // 한줄평 목록//
    @Override
    public List<OnelineReviewView> getOnelineReviews(Long movieId) {
        List<OnelineReviewView> onelineReviews = onelineReviewRepository.getlist(movieId);
        return onelineReviews;
    }

    @Override
    public List<OnelineReviewMovieView> getByMemberId(Long id) {
        return onelineReviewRepository.getByMemberId(id);
    }
}
