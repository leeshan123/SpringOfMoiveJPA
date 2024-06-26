package kr.co.moviespring.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kr.co.moviespring.web.advice.GlobalExceptionHandler;
import kr.co.moviespring.web.config.security.CustomUserDetails;
import kr.co.moviespring.web.entity.Member;
import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.VoteMemberList;
import kr.co.moviespring.web.entity.totalVoteView;
import kr.co.moviespring.web.repository.MemberRepository;
import kr.co.moviespring.web.repository.TwoWeeksMovieRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TwoWeeksMovieServiceImp implements TwoWeeksMovieService {

    @Autowired
    TwoWeeksMovieRepository TWMovieRepository;
    @Autowired
    MemberRepository memberRepository;
   
    //2주영화 후보 목록가져오기
    @Override
    public List<totalVoteView> findByMovieCd() {
        List<totalVoteView> TWMovie = TWMovieRepository.getByMovieCd();
        // System.out.println("투표수 영화목록 ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ"+TWMovie);
        return TWMovie;
    }
    //2주의영화 투표수가져오기
    @Override
    public Long findTotalVote() {
        Long totalVoteCount = TWMovieRepository.getVoteCount();
        System.out.println("투표수 합계 ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ"+totalVoteCount);
        // if(totalVoteCount == null){
        //     totalVoteCount=0L;
        // }

        return totalVoteCount;
    }

    // @Override
    // public List<String> findMovieVoteListId(){
    //     List<String> Id=TWMovieRepository.getVoteListId();
    //     parseId = parseLong(Id);
    // }
    // admin
    // public void findByCriteria(String parentSelectValue, String childSelectValue) {
    //     TWMovieRepository.getMoviesByDynamicCriteria(parentSelectValue,childSelectValue);
    //     // Map<String, String> params = new HashMap<>();
    //     // params.put("parentSelectValue", parentSelectValue);
    //     // params.put("childSelectValue", childSelectValue);
    // }

    @Override
    // @Transactional
    public void initList(String parentSelectValue, String childSelectValue){
        TWMovieRepository.deleteAllVoteList();
        TWMovieRepository.deleteAllMovieList();
        TWMovieRepository.getMoviesByDynamicCriteria(parentSelectValue,childSelectValue);
    }
    
    public void findByGenre(String childSelectValue) {
        TWMovieRepository.getByGenre(childSelectValue);
    }

    // @Override
    // public void findByReleseDate(String childSelectValue) {
    //     TWMovieRepository.getByReleaseDate(childSelectValue);

    // }

    // @Override
    // public void findByDistributor(String childSelectValue) {
    //     TWMovieRepository.getByDistributor(childSelectValue);
    // }

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


    //sql duplicate exception객체 받을수있는 DataIntegrityViolationException로 바꿈
    @Override
    @Transactional
    public void vote(Long memberId, Integer movieId) {
        Integer point=1000;
        TWMovieRepository.addVoteToMovieList(memberId, movieId);
            boolean isDuplicate = checkDuplicateVote(memberId, movieId);
            if (isDuplicate) {
                throw new DataIntegrityViolationException("Duplicate vote attempt detected.");
            }
            // 
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            
            // 사용자 이름을 사용하여 Member 찾기
            Member member = memberRepository.findByMembername(userDetails.getUsername());
            if (member != null) {
                int userPoint = member.getPoint();
                userPoint += point;
                
                member.setPoint(userPoint);
                userDetails.setPoint(userPoint);
                
            }

        memberRepository.plus1000Point(memberId,point);
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

    //당첨된 2주의 영화 찾기
    @Override
    public totalVoteView findWinnerMovie() {
        totalVoteView winner = TWMovieRepository.getThisWeeksMovie();
        // if(winner.getVoteCount()==0){
        //     winner=null;
        // }
        return winner;
    }
    
    
}
