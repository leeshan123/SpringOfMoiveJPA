package kr.co.moviespring.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.VoteMemberList;
import kr.co.moviespring.web.entity.totalVoteView;

@Mapper
public interface TwoWeeksMovieRepository {

    //2주의영화 목록가져오기(투표수포함)
    List<totalVoteView> getByMovieCd();
    //테마 이름가져오기
    String getGenre();
    //총 투표수가져오기
    Long getVoteCount();
    
    //투표한유저,영화ID 등록
    VoteMemberList addVoteToMovieList(Long memberId,Integer movieId);
    //중복투표 예외처리를 위한 이미 투표한 유저id찾기
    VoteMemberList findVotedUser(Long memberId);
    //투표제일많이받은영화
    totalVoteView getThisWeeksMovie();

    //admin
    void getByGenre(String childSelectValue);

    void getByReleaseDate(String childSelectValue);

    void getByDistributor(String childSelectValue);

    List<Movie> getEditedList();



}
