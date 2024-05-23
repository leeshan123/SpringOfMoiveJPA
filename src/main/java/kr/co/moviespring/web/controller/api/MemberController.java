package kr.co.moviespring.web.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @DeleteMapping("delete")
    public ResponseEntity<String> delete(
        @RequestBody Long id
    ){
        // 일단 업데이트 문으로, 나중에 구현
        // status 속성을 추가해서 active | inactive로 바꾸자
        // or active 속성을 추가해서 boolean으로 받아서 하던가
        // memberService.removeById(id);
        
        return ResponseEntity.ok("성공적으로 삭제되었습니다.");
    }
}
