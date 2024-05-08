package kr.co.moviespring.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import kr.co.moviespring.web.config.batch.BatchSchedulerConfig;
import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.movieapi.KobisMovieAPI;
import kr.co.moviespring.web.movieapi.dto.kobis.KobisDailyBox;
import kr.co.moviespring.web.repository.MovieRepository;

@Service
public class MovieServiceImp implements MovieService {

    // 모든 목록 가져오기//
    @Autowired
    MovieRepository repository;

    // 프로젝트 로드시 한번 실행되는 어노테이션
    @PostConstruct
    public void movieInit() {
        List<Movie> list = new ArrayList<>();
        KobisMovieAPI api = new KobisMovieAPI();
        List<KobisDailyBox> koList = api.searchDailyBoxOfficeList();
        for (KobisDailyBox kobisDailyBox : koList) {
            Movie movie = this.getByKobisId(kobisDailyBox.getMovieCd());
            list.add(movie);
        }
        BatchSchedulerConfig.setList(list);
    }

    @Override
    public List<Movie> getList(Integer page) {
        int size = 10;
        int offset = (page-1);
        List<Movie> list = repository.findAll(offset, size);
        return list;
    }

    @Override
    public List<Movie> getListByYear() {
        List<Movie> list = repository.findAllByYear();
        return list;
    }

    @Override
    public List<Movie> getListAfter() {
        List<Movie> list = repository.findAllAfter();
        return list;
    }
    // 검색값으로 영화목록 가져오기//
    @Override
    public List<Movie> getListByName(String query) {

        List<Movie> list;
        if (query == null || query.trim().isEmpty()) {
            list = null;
        } else {
            list = repository.findAllByName(query);
        }
        return list;
    }

    //인물별 필모리스트
    @Override
    public List<Movie> getListByPeopleId(Long id) {
        List<Movie> list = repository.findAllByPeopleId(id);
        return list;
    }

    // 요청 id값으로 상세정보가져오기//
    @Override
    public Movie getById(Long id) {
        Movie movie = repository.findById(id);
        return movie;
    }

    @Override
    public Movie getByTMDBId(String id) {
        Movie movie = repository.findByTMDBId(id);
        return movie;
    }
    @Override
    public Movie getByKobisId(String id) {
        Movie movie = repository.findByKobisId(id);
        return movie;
    }

    // 저장하고 생성된 영화 ID를 받아와서 리턴 //
    @Override
    public Long saveMovie(Movie movie) {
        repository.save(movie);
        Long movieId = movie.getId();
        return movieId;
    }

    //카테고리별 영화검색후 등록(2주의영화 관리자)
    public void findByGenre(String childSelectValue) {
        repository.getByGenre(childSelectValue);
    }
    @Override
    public void findByReleseDate(String childSelectValue) {
        repository.getByReleseDate(childSelectValue);

    }
    @Override
    public void findByDistributor(String childSelectValue) {
        repository.getByDistributor(childSelectValue);
    }
    @Override
    public  List<Movie>  findAllEditedList() {
        List<Movie>  movieWeeks = repository.getEditedList();
        return movieWeeks;
    }

   

}