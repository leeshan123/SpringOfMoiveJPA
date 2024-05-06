package kr.co.moviespring.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.EventPage;

@Mapper
public interface EventPageRepository {
    List<EventPage> findAll();
    EventPage findById(Long id);
    Long save(String title, String contents, String imageUrl, Long memberId);
    void update(EventPage eventPage);
    void delete(Long id);
}
