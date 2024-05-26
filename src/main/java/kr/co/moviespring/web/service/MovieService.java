package kr.co.moviespring.web.service;

import java.util.List;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.entity.MovieSearchView;

public interface MovieService {
    List <Movie> getList(Integer page);
    List<MovieSearchView> getListView(Integer page);
    List<MovieSearchView> getListView(Integer page, String query);

    List <Movie> getListAfter();
    List <Movie> getListByName(String query);
    List <Movie> getListByYear();
    
    //영화 리스트 개수
    int getCount();
    int getCount(String query);
    
    //인물별 필모리스트
    List <Movie> getListByPeopleId(Long id, String type);

    Movie getById(Long id);
    Movie getByTMDBId(String id);
    Movie getByKobisId(String id);
    Long saveMovie(Movie movie);
    //장르별 관객수 순위6위까지 검색
    void findByGenre(String childSelectValue);
    //년도별 6위까지검색
    void findByReleseDate(String childSelectValue);
    //배급사별 6위까지 검색
    void findByDistributor(String childSelectValue);

    //관리자 2주영화 등록데이터 조회 비동기(비둘기x)처리
    List<Movie>  findAllEditedList();
    void deleteById(Long id);
}
