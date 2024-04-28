package kr.co.moviespring.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.MovieStillcut;

@Mapper
public interface MovieStillcutRepository {
    Long save(MovieStillcut stillcut);
}
