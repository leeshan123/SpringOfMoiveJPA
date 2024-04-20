package kr.co.moviespring.web.movieapi;

import kr.co.moviespring.web.entity.Movie2;
import kr.co.moviespring.web.service.MovieInsertService;
import kr.co.moviespring.web.service.MovieInsertServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDataUpdate {
    @Autowired
    MovieInsertServiceImp movieInsertService;


    //영화 목록 가져오기
    public List<Movie2> getMovieList(String key){
        System.out.println("movieList메서드 시작");
        return movieInsertService.getMovieList(key);
    }

    //가져온 영화 목록 데이터들을 업데이트해주기.
    public void movieListUpdate(List<Movie2> movielist){
        System.out.println("movieListUpdate 메서드 시작");
        movieInsertService.upDateMovieInfo(movielist);
    }

    //가져온 영화 목록 데이터들 중에 없는 데이터를 삽입해주기.
    public void movieListInsert(List<Movie2> movielist){
        System.out.println("movieListInsert 메서드 시작");
        movieInsertService.saveIfNotMovie(movielist);
    }

    //엑셀 데이터 가져오기.
    public List<Movie2> getExcelData(String filePath){
        System.out.println("getExcelData 메서드 시작");
        return movieInsertService.getExcelData(filePath);

    }

    //엑셀 데이터 삽입하기.
    public void excelDataInsert(List<Movie2> movielist){
        System.out.println("excelDataInsert 메서드 시작");
        movieInsertService.excelDataInsert(movielist);
    }


}
