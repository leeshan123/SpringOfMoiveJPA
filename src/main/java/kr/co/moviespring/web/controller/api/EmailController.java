package kr.co.moviespring.web.controller.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moviespring.web.service.MemberService;

@RestController
@RequestMapping("api/email")
public class EmailController {

    @Autowired
    MemberService memberService;

    private final JavaMailSender emailSender;

    public EmailController(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @PostMapping("verify-user")
    public ResponseEntity<Map<String, Long>> verifyUser(
        @RequestBody Map<String, String> requestData    
    ) {
        String username = requestData.get("name");
        String email = requestData.get("email");
        
        Long id = memberService.verifyUser(username, email);
        Map<String, Long> response = new HashMap<>();

        response.put("id", id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("verify-pwd")
    public ResponseEntity<Map<String, Long>> verifyPwd(
        @RequestBody Map<String, String> requestData    
    ) {
        String username = requestData.get("name");
        String email = requestData.get("email");
        String userId = requestData.get("userid");
        
        Long id = memberService.verifyPwd(userId, username, email);
        Map<String, Long> response = new HashMap<>();

        response.put("id", id);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/sendVerificationEmail")
    public ResponseEntity<String> sendVerificationEmail(@RequestBody EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        
        //난수생성
        String strRan = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(strRan.length());
            sb.append(strRan.charAt(index));
        }

        message.setTo(emailRequest.getEmail());
        message.setSubject("영화의 봄 이메일 인증 코드");
        message.setText("인증 코드: " + sb.toString()); // 실제로는 랜덤한 인증 코드를 생성하여 여기에 넣어야 합니다.
        emailSender.send(message);
        // JSON 응답 구성 후 
        String jsonResponse = sb.toString();
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
    }


    static class EmailRequest {
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
