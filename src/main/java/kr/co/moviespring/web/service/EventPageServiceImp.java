package kr.co.moviespring.web.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.moviespring.web.entity.EventPage;
import kr.co.moviespring.web.repository.EventPageRepository;

@Service
public class EventPageServiceImp implements EventPageService{

    @Autowired
    EventPageRepository repository;

    @Override
    public Long reg(EventPage eventPage, Long memberId) {
        repository.save(eventPage, memberId);
        return null;
    }

    @Override
    public List<EventPage> getList() {
        return repository.findAll();
    }

    @Override
    public EventPage getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public EventPage getByNextId(Long id) {
        EventPage ep = repository.findByNextId(id);
        return ep;
    }

    @Override
    public EventPage getByPreId(Long id) {
        EventPage ep = repository.findByPreId(id);
        return ep;    
    }

    @Override
    public void edit(EventPage eventPage) {
        repository.update(eventPage);
    }

    @Override
    public void deleteById(Long id) {
        repository.delete(id);
    }

    @Override
    public List<EventPage> getOngoingList() {
        LocalDate currentDate = LocalDate.now(); // 현재 날짜 가져오기
        List<EventPage> list = repository.ongoingEvents(currentDate);
        return list;
    }

    @Override
    public List<EventPage> getEndedList() {
        LocalDate currentDate = LocalDate.now(); // 현재 날짜 가져오기
        List<EventPage> list = repository.endedEvents(currentDate);
        return list;
    }

    @Override
    public List<EventPage> getEventListTop5() {
        List<EventPage> list = repository.findByTop5();
        return list;

    }

}
