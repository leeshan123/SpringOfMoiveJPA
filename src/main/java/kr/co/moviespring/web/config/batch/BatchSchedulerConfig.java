package kr.co.moviespring.web.config.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import kr.co.moviespring.web.entity.Movie;
import kr.co.moviespring.web.movieapi.KobisMovieAPI;
import kr.co.moviespring.web.movieapi.dto.kobis.KobisDailyBox;
import kr.co.moviespring.web.service.MovieService;

@Configuration
@EnableScheduling
public class BatchSchedulerConfig {

    private static List<Movie> list;

    public static List<Movie> getList() {
        return list;
    }

    @Autowired
    MovieService service;

    //초, 분, 시간, 일, 월, 요일
    @Scheduled(cron = "0 21 18 * * *")
    public void performBatchJob() {
        list = new ArrayList<>();
        KobisMovieAPI api = new KobisMovieAPI();
        List<KobisDailyBox> koList = api.searchDailyBoxOfficeList();
        for (KobisDailyBox kobisDailyBox : koList) {
            Movie movie = service.getByKobisId(kobisDailyBox.getMovieCd());
            list.add(movie);
        }
        System.out.println("데일리 리스트 데이터 획득ㅋ");
    }
}
