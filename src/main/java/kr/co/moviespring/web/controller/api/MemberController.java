package kr.co.moviespring.web.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moviespring.web.service.MemberService;

@RestController("ApiMemberController")
@RequestMapping("api/member")
public class MemberController {
    
    @Autowired
    MemberService memberService;

    @GetMapping("check-username")
    public Map<String, Boolean> checkUsername(
        @RequestParam String name
    ){
        boolean isDuplicate = memberService.getByUsername(name);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return response;
    }
}
