package kr.co.moviespring.web.movieapi;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//엑셀로 누적관객수 넣는 거(앵간하면 쓸 일 없을듯)

//컴포넌트로 인식
//@Component
public class StartupTasks {

//    @Autowired
    private AudiAccInsert audiAccInsert;

    //메서드를 자동으로 실행
    //의존성 주입이 완료 된 후에 딱 한번만 실행.
//    @PostConstruct
    public void executeAfterStartup() {
        audiAccInsert.processExcelData();
    }
}
