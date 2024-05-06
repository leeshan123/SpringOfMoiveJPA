package kr.co.moviespring.web.service;

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
    public Long reg(String title, String contents, String imageUrl, Long memberId) {
        repository.save(title, contents, imageUrl, memberId);
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
    public void edit(EventPage eventPage) {
        repository.update(eventPage);
    }
    
}
