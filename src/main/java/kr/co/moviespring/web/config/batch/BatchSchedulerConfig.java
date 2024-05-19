package kr.co.moviespring.web.config.batch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.co.moviespring.web.movieapi.CompanyDataUpdate;
import kr.co.moviespring.web.movieapi.MovieDataUpdate;
import kr.co.moviespring.web.movieapi.PeopleDataUpdate;
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

    public static void setList(List<Movie> list) {
        BatchSchedulerConfig.list = list;
    }

    @Autowired
    MovieService service;

    @Autowired
    private MovieDataUpdate movieDataUpdate;

    @Autowired
    private PeopleDataUpdate peopleDataUpdate;



    //초, 분, 시간, 일, 월, 요일
    @Scheduled(cron = "0 0 0 * * *")
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

    //초, 분, 시간, 일, 월, 요일
    //매일 오전 6시 0분에 영화랑 영화인들 정보를 가져오기.
//    @Scheduled(cron = "0 6 * * * *")
    public void updateMovieAndPeoPleData() throws IOException {
        String key = "e7f6a63edea6295a55b893dcc0071d60";

        //영화 목록을 가져와서 영화 리스트를 업데이트함.
        movieDataUpdate.movieListUpdate(movieDataUpdate.getMovieList(key));
        //영화 목록을 가져와서 영화 목록 중 없는 것만 데이터를 저장해줌.
        movieDataUpdate.movieListInsert(movieDataUpdate.getMovieList(key));

        //영화 배우랑 감독 데이터 넣기
        peopleDataUpdate.peopleInsert(peopleDataUpdate.getActorList(key));

        //영화 배우랑 pk 가져와서 영화 배우랑 감독 필모그래피 데이터 업데이트해주기
        for(int i =1; i<200; i++) {
            String peopleCd = peopleDataUpdate.getPeopleCd(i);
            peopleDataUpdate.filmoInsertServie(peopleDataUpdate.getFilmoList(key,peopleCd));
        }




        System.out.println("영화 + 사람 정보 업데이트.");
    }
}
