package kr.co.moviespring.web.movieapi;

import jakarta.annotation.PostConstruct;
import kr.co.moviespring.web.repository.PeopleInsertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//영화 넣을떄 쓰자.
//영진위API 10127
// 1691까지
//후보군: fa4a546d896ea6a36a7db5d09bcb80f3(내꺼)(1~191)
//후보군: 382938328dd953840168608f3f58b586(내꺼) (2500 -5000)
//후보군: 860589c36cddbbbbd930b4a6aaa53da7(태평이형꺼)
//후보군: a131ca3b5ab570fb631f8ca391fb7c74(민석이꺼)
//후보군: 92902be4026fea05023b7f31b4324c40(민석이꺼2)
//후보군: e7f6a63edea6295a55b893dcc0071d60(준순이꺼)
//후보군: 7142d2f276da9b8a161e569423e64ec3(준순이꺼2)
//후보군: 8eebf0d30cb02fd27d0ccede30262ce2(태평이형꺼2)

//컴포넌트로 인식
//어떻게 사용할지 고민해 봐야할듯!

//@Component
public class DataInsertService {


    String key = "a131ca3b5ab570fb631f8ca391fb7c74";
    String filePath = "C:/Users/leedw/Desktop/audience.xlsx";
//    @Autowired
    private MovieDataUpdate movieDataUpdate;

//    @Autowired
    private PeopleDataUpdate peopleDataUpdate;

    //메서드를 자동으로 실행
    //의존성 주입이 완료 된 후에 딱 한번만 실행.
//    @PostConstruct
    public void executeAfterStartup() {
        //영화 목록을 가져와서 영화 리스트를 업데이트함.
        movieDataUpdate.movieListUpdate(movieDataUpdate.getMovieList(key));
        //영화 목록을 가져와서 영화 목록 중 없는 것만 데이터를 저장해줌.
//        movieDataUpdate.movieListInsert(movieDataUpdate.getMovieList(key));

        //이제 다 넣어서 안쓸 것 같긴한데 엑셀에서 데이터를 가져와서 데이터를 삽입해줌.
//        movieDataUpdate.excelDataInsert(movieDataUpdate.getExcelData(filePath));

        //영화 배우랑 감독 데이터 넣기
//        peopleDataUpdate.peopleInsert(peopleDataUpdate.getActorList(key));

        System.out.println("executeAfterStartup종료");
    }

}
