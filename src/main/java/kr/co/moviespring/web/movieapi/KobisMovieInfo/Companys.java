package kr.co.moviespring.web.movieapi.KobisMovieInfo;

// companys의 자료구조 => companyCd, companyNm, companyNmEn, companyPartNm
public class Companys {
    private String companyCd;	    //참여 영화사 코드
    private String companyNm;	    //참여 영화사명
    private String companyNmEn;	    //참여 영화사명(영문)
    private String companyPartNm;	//참여 영화사 분야명

    
    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getCompanyNm() {
        return companyNm;
    }
    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }
    public String getCompanyNmEn() {
        return companyNmEn;
    }
    public void setCompanyNmEn(String companyNmEn) {
        this.companyNmEn = companyNmEn;
    }
    public String getCompanyPartNm() {
        return companyPartNm;
    }
    public void setCompanyPartNm(String companyPartNm) {
        this.companyPartNm = companyPartNm;
    }
}
