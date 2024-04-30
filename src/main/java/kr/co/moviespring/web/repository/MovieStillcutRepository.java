package kr.co.moviespring.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.moviespring.web.entity.MovieStillcut;

import java.util.List;

@Mapper
public interface MovieStillcutRepository {
    Long save(MovieStillcut stillcut);
    //영화 아이디별 스틸컷 리스트
    List<MovieStillcut> findAllById(Long movieId);
}
