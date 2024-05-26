package kr.co.moviespring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moviespring.web.entity.EventPage;
import kr.co.moviespring.web.service.EventPageService;

@Controller
@RequestMapping("event")
public class EventController {

    @Autowired
    EventPageService eventPageService;

    @GetMapping("list")
    public String list(
        Model model
    ){
        List<EventPage> list = eventPageService.getList();
        model.addAttribute("list", list);
        return "event/list";
    }

    @GetMapping("detail")
    public String detail(
        @RequestParam("id") Long id
        ,Model model
    ){
        EventPage ep = eventPageService.getById(id);
        EventPage nextEp = eventPageService.getByNextId(id);
        EventPage preEp = eventPageService.getByPreId(id);

        if(nextEp == null){
            nextEp = new EventPage();
            nextEp.setTitle("다음 글 없음");
        }

        if(preEp == null){
            preEp = new EventPage();
            preEp.setTitle("이전 글 없음");
        }

        model.addAttribute("premenu", preEp);
        model.addAttribute("nextmenu", nextEp);
        model.addAttribute("menu", ep);
        return "event/detail";
    }
}
