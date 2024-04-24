package kr.co.moviespring.web.movieapi.dto.kobis;

public class KobisDailyBox {

    private String rnum;         //순번을 출력
    private String rank;         //해당일자의 박스오피스 순위
    private String rankInten;    //전일대비 순위의 증감분을 출력
    private String rankOldAndNew;//“OLD” : 기존 , “NEW” : 신규
    private String movieCd;      //영화의 대표코드
    private String movieNm;      //영화명(국문)
    private String openDt;       //영화의 개봉일
    private String salesAmt;     //해당일의 매출액
    private String salesShare;	 //해당일자 상영작의 매출총액 대비 해당 영화의 매출비율
    private String salesInten;   //전일 대비 매출액 증감분
    private String salesChange;  //전일 대비 매출액 증감 비율
    private String salesAcc;     //누적매출액
    private String audiCnt;      //해당일의 관객수
    private String audiInten;    //전일 대비 관객수 증감분
    private String audiChange;	 //전일 대비 관객수 증감 비율
    private String audiAcc;      //누적관객수
    private String scrnCnt;      //해당일자에 상영한 스크린수
    private String showCnt;      //해당일자에 상영된 횟수

    public String getRnum() {
        return rnum;
    }
    public void setRnum(String rnum) {
        this.rnum = rnum;
    }
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public String getRankInten() {
        return rankInten;
    }
    public void setRankInten(String rankInten) {
        this.rankInten = rankInten;
    }
    public String getRankOldAndNew() {
        return rankOldAndNew;
    }
    public void setRankOldAndNew(String rankOldAndNew) {
        this.rankOldAndNew = rankOldAndNew;
    }
    public String getMovieCd() {
        return movieCd;
    }
    public void setMovieCd(String movieCd) {
        this.movieCd = movieCd;
    }
    public String getMovieNm() {
        return movieNm;
    }
    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }
    public String getOpenDt() {
        return openDt;
    }
    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }
    public String getSalesAmt() {
        return salesAmt;
    }
    public void setSalesAmt(String salesAmt) {
        this.salesAmt = salesAmt;
    }
    public String getSalesShare() {
        return salesShare;
    }
    public void setSalesShare(String salesShare) {
        this.salesShare = salesShare;
    }
    public String getSalesInten() {
        return salesInten;
    }
    public void setSalesInten(String salesInten) {
        this.salesInten = salesInten;
    }
    public String getSalesChange() {
        return salesChange;
    }
    public void setSalesChange(String salesChange) {
        this.salesChange = salesChange;
    }
    public String getSalesAcc() {
        return salesAcc;
    }
    public void setSalesAcc(String salesAcc) {
        this.salesAcc = salesAcc;
    }
    public String getAudiCnt() {
        return audiCnt;
    }
    public void setAudiCnt(String audiCnt) {
        this.audiCnt = audiCnt;
    }
    public String getAudiInten() {
        return audiInten;
    }
    public void setAudiInten(String audiInten) {
        this.audiInten = audiInten;
    }
    public String getAudiChange() {
        return audiChange;
    }
    public void setAudiChange(String audiChange) {
        this.audiChange = audiChange;
    }
    public String getAudiAcc() {
        return audiAcc;
    }
    public void setAudiAcc(String audiAcc) {
        this.audiAcc = audiAcc;
    }
    public String getScrnCnt() {
        return scrnCnt;
    }
    public void setScrnCnt(String scrnCnt) {
        this.scrnCnt = scrnCnt;
    }
    public String getShowCnt() {
        return showCnt;
    }
    public void setShowCnt(String showCnt) {
        this.showCnt = showCnt;
    }
}
