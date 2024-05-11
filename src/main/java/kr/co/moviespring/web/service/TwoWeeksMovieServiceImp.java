package kr.co.moviespring.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.VoteMemberList;
import kr.co.moviespring.web.entity.totalVoteView;
import kr.co.moviespring.web.repository.TwoWeeksMovieRepository;

@Service
public class TwoWeeksMovieServiceImp implements TwoWeeksMovieService {

    @Autowired
    TwoWeeksMovieRepository TWMovieRepository;
    //2주영화 후보 목록가져오기
    @Override
    public List<totalVoteView> findByMovieCd() {
        List<totalVoteView> TWMovie = TWMovieRepository.getByMovieCd();
        return TWMovie;
    }
    //2주의영화 투표수가져오기
    @Override
    public Long findTotalVote() {
        Long totalVoteCount = TWMovieRepository.getVoteCount();
        return totalVoteCount;
    }

    // admin
    public void findByGenre(String childSelectValue) {
        TWMovieRepository.getByGenre(childSelectValue);
    }

    @Override
    public void findByReleseDate(String childSelectValue) {
        TWMovieRepository.getByReleseDate(childSelectValue);

    }

    @Override
    public void findByDistributor(String childSelectValue) {
        TWMovieRepository.getByDistributor(childSelectValue);
    }

    @Override
    public List<Movie> findAllEditedList() {
        List<Movie> movieWeeks = TWMovieRepository.getEditedList();
        return movieWeeks;
    }

    @Override
    public String findGenreName() {
        String genre = TWMovieRepository.getGenre();
        return genre;
    }

    @Override
    public VoteMemberList vote(Long memberId, Integer movieId) {
        VoteMemberList voteMember = TWMovieRepository.addVoteToMovieList(memberId, movieId);
        return voteMember;
    }

}
