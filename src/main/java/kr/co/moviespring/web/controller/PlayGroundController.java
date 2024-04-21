package kr.co.moviespring.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("playground")
public class PlayGroundController {

    // 현재 url을 얻어서 모델에 넣어줌
    @ModelAttribute("url")
    String getRequestServletPath(HttpServletRequest request) {
        return request.getServletPath();
    }

    @GetMapping("main")
    public String main(){

        return "playground/main";
    }
}