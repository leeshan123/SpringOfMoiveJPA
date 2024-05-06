package kr.co.moviespring.web.repository;

import kr.co.moviespring.web.entity.Company;
import kr.co.moviespring.web.entity.MovieCompany;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyInsertRepository {

    void saveCompany(Company company);


    void saveMovieCompany(MovieCompany movieCompany);
}
