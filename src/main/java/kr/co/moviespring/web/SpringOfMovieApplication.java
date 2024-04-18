package kr.co.moviespring.web;

import kr.co.moviespring.web.movieapi.AudiAccInsert;
import kr.co.moviespring.web.movieapi.StartupTasks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringOfMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringOfMovieApplication.class, args);
        System.out.println("서버시작");


    }

}
