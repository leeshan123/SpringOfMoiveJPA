package kr.co.moviespring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class SpringOfMovieApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(SpringOfMovieApplication.class, args);
        System.out.println("서버시작");
        
        





    }

}
