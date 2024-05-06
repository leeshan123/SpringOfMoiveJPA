package kr.co.moviespring.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moviespring.web.config.security.CustomUserDetails;
import kr.co.moviespring.web.entity.EventPage;
import kr.co.moviespring.web.service.EventPageService;

@Controller("AdminMovieEventController") //이름의 중복을 피하기 위해 ioc컨테이너에 담기는 이름을 직접 작성해줄수있다
@RequestMapping("admin/movieEvent")
public class MovieEventController {

    @Autowired
    EventPageService eventPageService;

    
    @GetMapping("list")
    public String movieEventList(
        Model model
    ) {
        List<EventPage> list = eventPageService.getList();
        model.addAttribute("list", list);
        return "/admin/movieEvent/list"; 
    }

    @GetMapping("reg")
    public String movieEventReg(
        
    ) {
        return "admin/movieEvent/reg"; 
    }

    @PostMapping("reg")
    public String movieEventReg(
        EventPage ep
        ,@AuthenticationPrincipal CustomUserDetails userDetails
    ){
        eventPageService.reg(ep.getTitle(), ep.getContents(), ep.getImageUrl(), userDetails.getId());

        return "/admin/movieEvent/list";
    }

    @GetMapping("edit")
    public String movieEventEdit(
        @RequestParam("id") Long id
        , Model model
    ){
        EventPage ep = eventPageService.getById(id);
        model.addAttribute("menu", ep);
        return "/admin/movieEvent/edit";
    }

    @PostMapping("edit")
    public String movieEventEdit(
        EventPage eventPage
    ){
        eventPageService.edit(eventPage);
        return "redirect:/admin/movieEvent/list";
    }
}
