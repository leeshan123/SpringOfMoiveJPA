package kr.co.moviespring.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.Director;

@Mapper
public interface DirectorRepository {
    Long save(Director director);
    int update(Director director);
    int delete(Director director);
}
