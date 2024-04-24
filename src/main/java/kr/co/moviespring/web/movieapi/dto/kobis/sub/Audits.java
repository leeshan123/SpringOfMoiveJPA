package kr.co.moviespring.web.movieapi.dto.kobis.sub;

//심의정보
public class Audits {
    private String auditNo;	        //심의번호
    private String watchGradeNm;	//관람등급 명칭
    
    public String getAuditNo() {
        return auditNo;
    }
    public void setAuditNo(String auditNo) {
        this.auditNo = auditNo;
    }
    public String getWatchGradeNm() {
        return watchGradeNm;
    }
    public void setWatchGradeNm(String watchGradeNm) {
        this.watchGradeNm = watchGradeNm;
    }
}
