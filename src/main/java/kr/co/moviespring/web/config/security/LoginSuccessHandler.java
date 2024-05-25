package kr.co.moviespring.web.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
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

        // 원래 요청한 페이지가 존재하면 해당 페이지로 리다이렉트, 없으면 기본적인 리다이렉트
        if (savedRequest != null) {
            response.sendRedirect(savedRequest.getRedirectUrl());
        } else {
            response.sendRedirect("/");
        }

    }
}

