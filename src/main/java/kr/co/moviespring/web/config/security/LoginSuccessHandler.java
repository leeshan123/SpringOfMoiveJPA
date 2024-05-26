package kr.co.moviespring.web.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
//      나중에 작동 테스트예정
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {
        // 사용자가 원래 요청한 페이지를 가져옴
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        //
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        System.out.println("===============================================================================");
        System.out.println(userDetails.toString());
        System.out.println(userDetails.getStatus());
        System.out.println("===============================================================================");
        
        String url = "/";

        // Status 상태값에 따라서 페이지 이동
        if (userDetails.getStatus() == 2) {
            //로그아웃
            HttpSession session = request.getSession(false);
            if(session != null)
                session.invalidate();

            url = "/deleted-member";
        } else if(userDetails.getStatus() == 1) {
            //로그아웃
            HttpSession session = request.getSession(false);
            if(session != null)
                session.invalidate();

            url = "/ban-member";
        }

        response.sendRedirect(url);

    }
}

