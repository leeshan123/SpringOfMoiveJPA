package kr.co.moviespring.web.advice;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
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
        //sql duplicate 익셉션 핸들러/ 위에꺼랑 다른거같음
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "duplicate");
        return "redirect:/2weeks/list";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "unknown");
        return "redirect:/2weeks/list";
    }


    public static class DuplicateVoteException extends RuntimeException {
        public DuplicateVoteException(String message) {
            super(message);
        }
    }
}