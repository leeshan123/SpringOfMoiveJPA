package kr.co.moviespring.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.moviespring.web.service.VisitorCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//방문자수를 얻기 위한 인터셉트
@Component
public class VisitInterceptor implements HandlerInterceptor {

    @Autowired
    private VisitorCountService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        //메인 페이지에 대한 요청을 가로챕니다.
        if("/".equals(request.getRequestURI())){
            service.incrementVisitorCount();
        }
        return true;
    }




}
