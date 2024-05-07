package kr.co.moviespring.web.service;

import java.util.List;

import kr.co.moviespring.web.entity.EventPage;

public interface EventPageService {
    List<EventPage> getList();
    EventPage getById(Long id);
    
    Long reg(EventPage eventPage, Long memberId);
    void edit(EventPage eventPage);
    void deleteById(Long id);
}
