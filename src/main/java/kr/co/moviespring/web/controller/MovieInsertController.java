package kr.co.moviespring.web.controller;

import kr.co.moviespring.web.service.MovieInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//영화 넣을떄 쓰자.
//영진위API
//후보군: fa4a546d896ea6a36a7db5d09bcb80f3(내꺼)
//후보군: 382938328dd953840168608f3f58b586(내꺼)
//후보군: 860589c36cddbbbbd930b4a6aaa53da7(태평이형꺼)
//후보군: a131ca3b5ab570fb631f8ca391fb7c74(민석이꺼)
//후보군: 92902be4026fea05023b7f31b4324c40(민석이꺼2)
//후보군: e7f6a63edea6295a55b893dcc0071d60(준순이꺼)
//후보군: 7142d2f276da9b8a161e569423e64ec3(준순이꺼2)
//후보군: 8eebf0d30cb02fd27d0ccede30262ce2(태평이형꺼2)

@Controller
@RequestMapping("InsertMovie")
public class MovieInsertController {

    @Autowired
    MovieInsertService movieInsertService;


    //영화진흥위원회API 일별박스오피스 영화 가져오기
    @GetMapping("DailyBoxOffice")
    public String DailyBoxOffice() {

            //영진위 key랑 어떤날꺼를 받아올건지 입력
            String key = "fa4a546d896ea6a36a7db5d09bcb80f3";
            String targetDt = "20240327";

        movieInsertService.getDailyBoxOffice(key,targetDt);



            return "InsertMovie/DailyBoxOffice";
        }

    //영화진흥위원회API 영화목록 가져오기
    //영화목록에서 겹치는거는 안가져오기
    @GetMapping("saveIfNotMovie")
    public String saveIfNotMovie() {
        String key = "a131ca3b5ab570fb631f8ca391fb7c74";

        movieInsertService.getsaveIfNotMovie(key);



        return "InsertMovie/saveIfNotMovie";

    }

    //영화진흥위원회API 영화목록 가져오기
    //영화목록에서 겹치든 말든 가져오기
    //이건 안쓸듯 이젠
//    @GetMapping("saveAllMovie")
//    public String saveAllMovie() {
//        String key = "a131ca3b5ab570fb631f8ca391fb7c74";
//
//        movieInsertService.getsaveAllMovie(key);
//
//
//        return "InsertMovie/saveAllMovie";
//
//    }





}




