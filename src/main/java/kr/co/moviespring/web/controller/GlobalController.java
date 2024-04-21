package kr.co.moviespring.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalController {

    // 현재 url을 얻어서 모든 컨트롤러 모델에 넣어줌
  @ModelAttribute("url")
  String getRequestServletPath(HttpServletRequest request) {
    return request.getServletPath();
  }

  // CustomUserDetails도 넣으면 편하려나? 보안문제도 고려해봐야함
}