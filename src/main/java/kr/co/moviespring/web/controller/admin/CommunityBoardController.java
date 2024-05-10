package kr.co.moviespring.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("AdminCommunityBoardController") //이름의 중복을 피하기 위해 ioc컨테이너에 담기는 이름을 직접 작성해줄수있다
@RequestMapping("admin/community-board")
public class CommunityBoardController {


    @GetMapping("main")
    public String communityBoardMain(){
        return "admin/community-board/main";
    }
}
