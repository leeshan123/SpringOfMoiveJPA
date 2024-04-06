package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.Movie2;

import java.util.List;

public interface MovieInsertService {

    void DailyBoxOfficeSave(List<Movie2> movieList);
    void getDailyBoxOffice(String key,String targetDt);

    //지금 쓸일은 없을듯
//    void saveAllMovie(List<Movie2> movie3List);
//    void getsaveAllMovie(String key);

    void saveIfNotMovie(List<Movie2> movie3List);
    void getsaveIfNotMovie(String key);



}
