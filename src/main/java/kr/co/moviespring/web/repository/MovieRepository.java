package kr.co.moviespring.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.MovieSearchView;

@Mapper
public interface MovieRepository {

    List<Movie> findAll(int offset, int size);
    List<MovieSearchView> findAllByQuery(String query, int offset, int size);

    List<Movie> findAllAfter();

    Long save(Movie movie);

    Movie findById(Long id);
    Movie findByTMDBId(String id);
    Movie findByKobisId(String id);


    // 검색값으로 영화목록 가져오기//
    List<Movie> findAllByName(String query);
    List<Movie> findAllByYear();


    //테마별 영화 2주의영화에 등록
    void getByGenre(String childSelectValue);

    void getByReleseDate(String childSelectValue);

    void getByDistributor(String childSelectValue);

    List<Movie> getEditedList();

    //인물별 필모리스트
    List<Movie> findAllByPeopleId(Long id, String type);
    int getCount(String query);
    void delete(Long id);
}
