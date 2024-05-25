package kr.co.moviespring.web.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moviespring.web.entity.Member;
import kr.co.moviespring.web.service.MemberService;

@Controller("AdminUserManagementController") //이름의 중복을 피하기 위해 ioc컨테이너에 담기는 이름을 직접 작성해줄수있다
@RequestMapping("admin/user-management")
public class UserManagementController {

    @Autowired
    MemberService memberService;

    @GetMapping("list")
    public String list(
        @RequestParam(name = "p", required = false, defaultValue = "1") Integer page
        , Model model
    ) {

        List<Member> list = new ArrayList<>();
        int cnt = 0;

        list = memberService.getList(page, null);
        cnt = memberService.getCount(null);

        model.addAttribute("list", list);
        model.addAttribute("count", cnt);


        return "admin/user-management/list";
    }

    @GetMapping("ban-list")
    public String banList(
        @RequestParam(name = "p", required = false, defaultValue = "1") Integer page
        , Model model
    ) {

        List<Member> list = new ArrayList<>();
        int cnt = 0;
        int status = 1;

        list = memberService.getList(page, status);
        cnt = memberService.getCount(status);

        model.addAttribute("list", list);
        model.addAttribute("count", cnt);


        return "admin/user-management/ban-list";
    }

    @GetMapping("detail")
    public String detail(
        String id
        ,Model model
    ){
        System.out.println(id);
        Member user = memberService.getById(Long.parseLong(id));

        model.addAttribute("user", user);

        return "admin/user-management/detail";
    }

}
