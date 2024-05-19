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


    //admin
    void getByGenre(String childSelectValue);

    void getByReleseDate(String childSelectValue);

    void getByDistributor(String childSelectValue);

    List<Movie> getEditedList();

    VoteMemberList addVoteToMovieList(Long memberId,Integer movieId);
    VoteMemberList findVotedUser(Long memberId);



}
