package kr.co.moviespring.web.repository;

import kr.co.moviespring.web.entity.People;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PeopleInsertRepository {

    void savePeople(People people);

    List<People> getPeopleList();
}
