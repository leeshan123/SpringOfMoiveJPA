package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.Movie2;
import kr.co.moviespring.web.entity.Movie3;
import kr.co.moviespring.web.entity.MovieSearchView;

import java.util.List;

public interface MovieInsertService {

    //일일박스오피스 데이터 넣기
    void DailyBoxOfficeSave(List<Movie2> movieList);

    //일일박스오피스 데이터 가져오기
    void getDailyBoxOffice(String key,String targetDt);

    //영화가 없으면 데이터 집어넣기
    void saveIfNotMovie(List<Movie2> movie3List);

    //영화 목록 업데이트하기
    void upDateMovieInfo(List<Movie2> movie2List);

    //영화 목록 가져오기
    List<Movie2> getMovieList(String key);

    // 태평, 영화3 목록 가져오기 테스트
    List<Movie3> getMovie3List(String year);

    // 태평, 영화3 목록 가져오기 테스트
    List<Movie3> getMovie3ListByPrd(String year);

    //엑셀에서 가져온 누적관객수 데이터 넣기.
    void excelDataInsert(List<Movie2> movie2List);

    //엑셀 데이터 가져오기.
    List<Movie2> getExcelData(String filePath);

    List<Movie3> getMovie3ListByQuery(Integer page, String query);

    List<Movie3> getMovie3ListByQuery(Integer page);

    int getCount(String query);

    int getCount();



}
