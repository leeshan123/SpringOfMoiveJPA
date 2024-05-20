package kr.co.moviespring.web.repository;

import kr.co.moviespring.web.entity.VisitorCount;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.Optional;
@Mapper
public interface VisitorCountRepository {


    Optional<VisitorCount> findByDate(LocalDate date);

    Long getTotalVisitors();

    void insertVisitorCount(VisitorCount visitorCount);

    void updateVisitorCount(VisitorCount visitorCount);
}

