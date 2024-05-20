package kr.co.moviespring.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.advice.GlobalExceptionHandler;
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
        if(totalVoteCount == null){
            totalVoteCount=0L;
        }

        return totalVoteCount;
    }

    // admin
    public void findByGenre(String childSelectValue) {
        TWMovieRepository.getByGenre(childSelectValue);
    }

    @Override
    public void findByReleseDate(String childSelectValue) {
        TWMovieRepository.getByReleaseDate(childSelectValue);

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


    
    // @Override
    // public void vote(Long memberId, Integer movieId) {
    //     TWMovieRepository.addVoteToMovieList(memberId, movieId);
    //     boolean isDuplicate = checkDuplicateVote(memberId, movieId);
    //     if (isDuplicate) {
    //         throw new GlobalExceptionHandler.handleDataIntegrityViolationException("Duplicate vote attempt detected.");
    //     }
    // }

    //sql duplicate exception객체 받을수있는 DataIntegrityViolationException로 바꿈
    @Override
    public void vote(Long memberId, Integer movieId) {
        TWMovieRepository.addVoteToMovieList(memberId, movieId);
        boolean isDuplicate = checkDuplicateVote(memberId, movieId);
        if (isDuplicate) {
            throw new DataIntegrityViolationException("Duplicate vote attempt detected.");
        }
    }

    private boolean checkDuplicateVote(Long memberId, Integer movieId) {

        boolean voteRecord= true;
        VoteMemberList isVotable=TWMovieRepository.findVotedUser(memberId);
        if (isVotable != null){
            voteRecord = false;
            System.out.println(voteRecord);
        }
        // 사용자아이디를 찾아오면 false 못찾으면 true
        return voteRecord;
    }
    
    
}
