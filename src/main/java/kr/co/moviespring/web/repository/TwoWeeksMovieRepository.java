package kr.co.moviespring.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.TwoWeeksMovie;
import kr.co.moviespring.web.entity.VoteMemberList;

@Mapper
public interface TwoWeeksMovieRepository {

    List<TwoWeeksMovie> getByMovieCd();

    String getGenre();

    //admin
    void getByGenre(String childSelectValue);

    void getByReleseDate(String childSelectValue);

    void getByDistributor(String childSelectValue);

    List<Movie> getEditedList();

    VoteMemberList addVoteToMovieList(Long memberId,Integer movieId);


}
