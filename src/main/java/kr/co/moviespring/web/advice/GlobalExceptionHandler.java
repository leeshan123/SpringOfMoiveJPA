package kr.co.moviespring.web.advice;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Duplicate 익셉션 핸들러 이거는 sql 중복 익셉션캐치가 안됨
    // @ExceptionHandler(DuplicateVoteException.class)
    // public String handleDuplicateVoteException(DuplicateVoteException ex, RedirectAttributes redirectAttributes) {
    //     redirectAttributes.addFlashAttribute("error", "duplicate");
    //     return "redirect:/2weeks/list";
    // }
    //     sql duplicate 익셉션 핸들러/ 위에꺼랑 다른거같음
    // @ExceptionHandler(DataIntegrityViolationException.class)
    // public String handleDataIntegrityViolationException(DataIntegrityViolationException ex, RedirectAttributes redirectAttributes) {
    //     redirectAttributes.addFlashAttribute("error", "duplicate");
    //     return "redirect:/2weeks/list";
    // }

    //진짜 최종 sql 중복에러만 캐치함 제발
    @ExceptionHandler(DuplicateKeyException.class)
        public String handleDuplicateKeyException(DuplicateKeyException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "duplicate");
        return "redirect:/2weeks/list";
    }
    //5/21회원가입에서 기존 회원정보와 중복되는 값 입력하면 2주의영화 오류페이지로 리다이렉션됨 개별핸들링추가예정

    //모든 exception 객체 받는 핸들러 (나중에 404밸류에러 핸들러로 만들어야함)
    // @ExceptionHandler(Exception.class)
    // public String handleGeneralException(Exception ex, RedirectAttributes redirectAttributes) {
    //     redirectAttributes.addFlashAttribute("error", "unknown");
    //     return "redirect:/2weeks/list";
    // }


    public static class DuplicateVoteException extends RuntimeException {
        public DuplicateVoteException(String message) {
            super(message);
        }
    }

}