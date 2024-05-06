package kr.co.moviespring.web.movieapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import kr.co.moviespring.web.entity.Company;
import kr.co.moviespring.web.entity.MovieCompany;
import kr.co.moviespring.web.repository.CompanyInsertRepository;
import kr.co.moviespring.web.service.PeopleInsertServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyDataUpdate {

    @Autowired
    CompanyInsertRepository repository;

    public List<Company> getCompanyList(String key) throws IOException {
        List<Company> companyList = new ArrayList<>();

        for(int i=1; i<140; i++) {
            StringBuilder sb = new StringBuilder();
            URL url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/company/searchCompanyList.json?key=" + key + "&itemPerPage=100&curPage=" + i);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            String line;
            while((line = br.readLine()) != null){
                sb.append(line);
            }

            Gson gson = new GsonBuilder().create();

            CompanyListResult companyListResult = gson.fromJson(sb.toString(),CompanyListResult.class);

            if(companyListResult != null && companyListResult.getCompanyListresult() != null){
                for(Company companyInfo : companyListResult.getCompanyListresult().getCompanyList()){
                    Company company = new Company();
                    company.setCompanyCd(companyInfo.getCompanyCd());
                    company.setCompanyNm(companyInfo.getCompanyNm());
                    company.setCompanyNmEn(companyInfo.getCompanyNmEn());
                    company.setCompanyPartNames(companyInfo.getCompanyPartNames());

                    companyList.add(company);
                }
            }
            br.close();


        }

        System.out.println("총 회사 갯수" + companyList.size());

        return companyList;
    }

    public void insertCompanyData(List<Company> companyList){
        for(int i=0; i<companyList.size(); i++){
            repository.saveCompany(companyList.get(i));
        }
    }

    public List<MovieCompany> getCompanyMovieList(String key,int conpanyCd) throws IOException {

        List<MovieCompany> moiveCompanyList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        URL url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/company/searchCompanyInfo.json?key="+key+"&companyCd="+conpanyCd);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

        String line;
        while((line = br.readLine()) != null){
            sb.append(line);
        }

        Gson gson = new GsonBuilder().create();

        CompanyInfoResult companyInfoResult = gson.fromJson(sb.toString(), CompanyInfoResult.class);
        CompanyInfo companyInfo = companyInfoResult.getCompanyInfoResultInfo().getCompanyInfo();

        for(Filmo filmo : companyInfo.getFilmos()){
            MovieCompany movieCompany = new MovieCompany();

            movieCompany.setCompanyCd(companyInfo.getCompanyCd());
            movieCompany.setMovieCd(filmo.getMovieCd());
            movieCompany.setMovieNm(filmo.getMovieNm());
            movieCompany.setCompanyPratNm(filmo.getCompanyPartNm());

            moiveCompanyList.add(movieCompany);
        }

        return moiveCompanyList;

    }

    public void insertMovieCompanyData(List<MovieCompany> movieCompanyList){
        for(int i=0; i<movieCompanyList.size(); i++){
            repository.saveMovieCompany(movieCompanyList.get(i));
        }
    }


    class CompanyListResult{

        @SerializedName("companyListResult")
        private CompanyListInfo companyListInfo;

        public CompanyListInfo getCompanyListresult(){return companyListInfo;}
    }

    class CompanyListInfo{

        @SerializedName("companyList")
        private List<Company> companyList;

        public List<Company> getCompanyList(){return companyList;}
    }

    class CompanyInfoResult {

        @SerializedName("companyInfoResult")
        private CompanyInfoResultInfo companyInfoResultInfo;

        public CompanyInfoResultInfo getCompanyInfoResultInfo(){return companyInfoResultInfo;}
    }

    class CompanyInfoResultInfo {

        @SerializedName("companyInfo")
        private CompanyInfo companyInfo;

        public CompanyInfo getCompanyInfo(){return companyInfo;};
    }

    class CompanyInfo {

        @SerializedName("companyCd")
        private String companyCd;

        @SerializedName("filmos")
        private List<Filmo> filmos;

        public String getCompanyCd() {
            return companyCd;
        }

        public List<Filmo> getFilmos() {
            return filmos;
        }
    }

    class Filmo {
        @SerializedName("movieCd")
        private String movieCd;
        @SerializedName("movieNm")
        private String movieNm;
        @SerializedName("companyPartNm")
        private String companyPartNm;

        public String getMovieCd() {
            return movieCd;
        }

        public String getMovieNm() {
            return movieNm;
        }

        public String getCompanyPartNm() {
            return companyPartNm;
        }
    }




}
