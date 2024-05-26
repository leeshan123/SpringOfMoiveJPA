package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.VisitorCount;
import kr.co.moviespring.web.repository.VisitorCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VisitorCountServiceImp implements VisitorCountService{

    @Autowired
    private VisitorCountRepository repository;



    @Override
    public void incrementVisitorCount() {
        LocalDate today = LocalDate.now();
        VisitorCount visitorCount = repository.findByDate(today).orElse(new VisitorCount());

        if (visitorCount.getId() == null) {
            visitorCount.setDate(today);
            visitorCount.setTotalVisitors(1L);
            visitorCount.setTodayVisitors(1L);
            repository.insertVisitorCount(visitorCount);
        } else {
            visitorCount.setTotalVisitors(visitorCount.getTotalVisitors() + 1);
            visitorCount.setTodayVisitors(visitorCount.getTodayVisitors() + 1);
            repository.updateVisitorCount(visitorCount);
        }

    }

    @Override
    public Long getTotalVisitors() {
        return repository.getTotalVisitors();
    }

    @Override
    public Long getTodayVisitors() {
        return repository.findByDate(LocalDate.now())
                .map(VisitorCount::getTodayVisitors)
                .orElse(0L);
    }
}
