package kr.co.moviespring.web.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.EventPage;

@Mapper
public interface EventPageRepository {
    List<EventPage> findAll();
    EventPage findById(Long id);
    EventPage findByNextId(Long id);
    EventPage findByPreId(Long id);
    Long save(EventPage eventPage, Long memberId);
    void update(EventPage eventPage);
    void delete(Long id);
    List<EventPage> ongoingEvents(LocalDate currentDate);
    List<EventPage> endedEvents(LocalDate currentDate);

    List<EventPage> findByTop5();
}
