package kr.co.moviespring.web.service;

import java.util.List;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.VoteMemberList;
import kr.co.moviespring.web.entity.totalVoteView;

public interface TwoWeeksMovieService {
    // 투표수 포함 목록가져오기
    List<totalVoteView> findByMovieCd();
    //투표합계
    Long findTotalVote();

    String findGenreName();
    //2주의 영화 투표
    void vote(Long memberId,Integer movieId);

    //admin 장르별 관객수 순위6위까지 검색
    void findByCriteria(String parentSelectValue, String childSelectValue);
     void findByGenre(String childSelectValue);
     //년도별 6위까지검색
     void findByReleseDate(String childSelectValue);
     //배급사별 6위까지 검색
     void findByDistributor(String childSelectValue);
 
     //관리자 2주영화 등록데이터 조회 비동기(비둘기x)처리
     List<Movie>  findAllEditedList();
    totalVoteView findWinnerMovie();


     
    
}
