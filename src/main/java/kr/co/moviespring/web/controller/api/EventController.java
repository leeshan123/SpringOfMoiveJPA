package kr.co.moviespring.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.moviespring.web.entity.EventPage;
import kr.co.moviespring.web.service.EventPageService;

@RestController("ApiEventController")
@RequestMapping("api/event")
public class EventController {
    
    @Autowired
    EventPageService eventPageService;

    @GetMapping("/ongoing")
    public List<EventPage> ongoing(
    ){
        List<EventPage> list = eventPageService.getOngoingList();
        return list;
    }

    @GetMapping("/ended")
    public List<EventPage> ended(
    ){
        List<EventPage> list = eventPageService.getEndedList();
        return list;
    }

}
