package kr.co.moviespring.web.movieapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import kr.co.moviespring.web.movieapi.dto.kobis.KobisDailyBox;
import kr.co.moviespring.web.movieapi.dto.kobis.KobisMovieInfo;
import kr.co.moviespring.web.movieapi.dto.kobis.sub.Actors;
import kr.co.moviespring.web.movieapi.dto.kobis.sub.Audits;
import kr.co.moviespring.web.movieapi.dto.kobis.sub.Companys;
import kr.co.moviespring.web.movieapi.dto.kobis.sub.Directors;

public class KobisMovieAPI {

    //후보군: fa4a546d896ea6a36a7db5d09bcb80f3(내꺼) (1~2500)
    //후보군: 382938328dd953840168608f3f58b586(내꺼) (2500~5000)
    //후보군: 860589c36cddbbbbd930b4a6aaa53da7(태평이형꺼) (5000~7500)
    //후보군: a131ca3b5ab570fb631f8ca391fb7c74(민석이꺼) (7500~10000)
    //후보군: 92902be4026fea05023b7f31b4324c40(민석이꺼2) (10000 ~ 12500)
    //후보군: e7f6a63edea6295a55b893dcc0071d60(준순이꺼) (12500 ~ 15000)
    //후보군: 7142d2f276da9b8a161e569423e64ec3(준순이꺼2) (15000 ~ 17500)
    //후보군: 8eebf0d30cb02fd27d0ccede30262ce2(태평이형꺼2) 18490

    // 상수 설정
    //   - 요청(Request) 요청 변수
    private String REQUEST_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
    // private String REQUEST_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=1e5731730c8946b865535ff457996b63&targetDt=";
    private final String AUTH_KEY = "1e5731730c8946b865535ff457996b63";
 
    //   - 일자 포맷
    private final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyyMMdd");

    // Map -> QueryString
    public String makeQueryString(Map<String, String> paramMap) {
        final StringBuilder sb = new StringBuilder();
        paramMap.entrySet().forEach(( entry )->{
            if( sb.length() > 0 ) {
                sb.append('&');
            }
            sb.append(entry.getKey()).append('=').append(entry.getValue());
        });
        return sb.toString();
    }
    
    //순위, 대표코드, 해당일의 관객수, 누적관객수, 누적매출액
    public List<KobisDailyBox> searchDailyBoxOfficeList(){
        
        // 일단 어제 날짜
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
        
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key"          , AUTH_KEY);                        // 발급받은 인증키
        paramMap.put("targetDt"     , DATE_FMT.format(cal.getTime()));  // 조회하고자 하는 날짜
        // paramMap.put("itemPerPage"  , "10");                            // 결과 ROW 의 개수( 최대 10개 )
        // paramMap.put("multiMovieYn" , "N");                             // Y:다양성 영화, N:상업영화, Default:전체
        
        //반환할 리스트 객체 생성
        List<KobisDailyBox> dbeList = new ArrayList<>();
        
        try {
            // Request URL 연결 객체 생성
            URL requestURL = new URL(REQUEST_URL+"?"+makeQueryString(paramMap));
            // URL requestURL = new URL(REQUEST_URL);
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
            
            // GET 방식으로 요청
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            
            // 응답(Response) 구조 작성
            //   - Stream -> JSONObject
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String readline = null;
            StringBuffer response = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                response.append(readline);
            }
 
            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(response.toString());
 
            // 데이터 추출
            JSONObject boxOfficeResult = responseBody.getJSONObject("boxOfficeResult");
            
            // 박스오피스 주제 출력
            String boxofficeType = boxOfficeResult.getString("boxofficeType");
            System.out.println(boxofficeType);
            
            // 박스오피스 목록 출력
            JSONArray dailyBoxOfficeList = boxOfficeResult.getJSONArray("dailyBoxOfficeList");
            
            
            //여기서 하나씩 받자
            Iterator<Object> iter = dailyBoxOfficeList.iterator();
            
            while(iter.hasNext()) {
                JSONObject boxOffice = (JSONObject) iter.next();
                
                KobisDailyBox dbe = new KobisDailyBox();
                dbe.setRnum(boxOffice.getString("rnum"));
                dbe.setRank(boxOffice.getString("rank"));
                dbe.setRankInten(boxOffice.getString("rankInten"));
                dbe.setRankOldAndNew(boxOffice.getString("rankOldAndNew"));
                dbe.setMovieCd(boxOffice.getString("movieCd"));
                dbe.setMovieNm(boxOffice.getString("movieNm"));
                dbe.setOpenDt(boxOffice.getString("openDt"));
                dbe.setSalesAmt(boxOffice.getString("salesAmt"));
                dbe.setSalesShare(boxOffice.getString("salesShare"));
                dbe.setSalesInten(boxOffice.getString("salesInten"));
                dbe.setSalesChange(boxOffice.getString("salesChange"));
                dbe.setSalesAcc(boxOffice.getString("salesAcc"));
                dbe.setAudiCnt(boxOffice.getString("audiCnt"));
                dbe.setAudiInten(boxOffice.getString("audiInten"));
                dbe.setAudiChange(boxOffice.getString("audiChange"));
                dbe.setAudiAcc(boxOffice.getString("audiAcc"));
                dbe.setScrnCnt(boxOffice.getString("scrnCnt"));
                dbe.setShowCnt(boxOffice.getString("showCnt"));

                
                dbeList.add(dbe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbeList;
    }


    //영화 상세정보, 인자값은 영화 코드
    public KobisMovieInfo searchMovieInfo(String movieCd){

        //요청 url 설정
        REQUEST_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json";

        //반환할 변수 생성
        KobisMovieInfo kobisMovieInfo = new KobisMovieInfo();

        // 변수 설정
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key"    , AUTH_KEY); // 발급받은 인증키
        paramMap.put("movieCd", movieCd);

        try {
            // Request URL 연결 객체 생성
            URL requestURL = new URL(REQUEST_URL+"?"+makeQueryString(paramMap));
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
 
            // GET 방식으로 요청
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
 
            // 응답(Response) 구조 작성
            //   - Stream -> JSONObject
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String readline = null;
            StringBuffer response = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                response.append(readline);
            }
 
            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(response.toString());
 
            // 데이터 추출
            JSONObject movieInfoResult = responseBody.getJSONObject("movieInfoResult").getJSONObject("movieInfo");

            //일단 코비스 영역 다시 데이터 추출
            {
                // "nations" 키의 값인 JsonArray를 추출
                JSONArray nationsArray = movieInfoResult.getJSONArray("nations");
                Iterator<Object> nationsIter = nationsArray.iterator();
                List<String> nationList = new ArrayList<>();;
                while(nationsIter.hasNext()){
                    JSONObject object = (JSONObject)nationsIter.next();
                    nationList.add(object.getString("nationNm"));
                }
                kobisMovieInfo.setNationNm(nationList);  

                // "genres" 키의 값인 JsonArray를 추출
                JSONArray genreArray = movieInfoResult.getJSONArray("genres");
                Iterator<Object> genreIter = genreArray.iterator();
                List<String> genreList = new ArrayList<>();
                while (genreIter.hasNext()) {
                    JSONObject object = (JSONObject)genreIter.next();
                    genreList.add(object.getString("genreNm"));
                }
                kobisMovieInfo.setGenreNm(genreList);

                // "directors" 키의 값인 JsonArray를 추출
                JSONArray directorsArray = movieInfoResult.getJSONArray("directors");
                Iterator<Object> directorsIter = directorsArray.iterator();
                List<Directors> directorsList = new ArrayList<>();
                while (directorsIter.hasNext()) {
                    JSONObject object = (JSONObject)directorsIter.next();
                    Directors director = new Directors();
                    director.setPeopleNm(object.getString("peopleNm"));
                    director.setPeopleNmEn(object.getString("peopleNmEn"));
                    directorsList.add(director);
                }
                kobisMovieInfo.setDirectors(directorsList);

                // "actors" 키의 값인 JsonArray를 추출
                JSONArray actorsArray = movieInfoResult.getJSONArray("actors");
                Iterator<Object> actorsIter = actorsArray.iterator();
                List<Actors> actorsList = new ArrayList<>();
                while (actorsIter.hasNext()) {
                    JSONObject object = (JSONObject)actorsIter.next();
                    Actors actor = new Actors();
                    actor.setPeopleNm(object.getString("peopleNm"));
                    actor.setPeopleNmEn(object.getString("peopleNmEn"));
                    actor.setCast(object.getString("cast"));
                    actor.setCastEn(object.getString("castEn"));
                    actorsList.add(actor);
                }
                kobisMovieInfo.setActors(actorsList);

                // "companys" 키의 값인 JsonArray를 추출
                JSONArray companysArray = movieInfoResult.getJSONArray("companys");
                Iterator<Object> companysIter = companysArray.iterator();
                List<Companys> companysList = new ArrayList<>();
                while (companysIter.hasNext()) {
                    JSONObject object = (JSONObject)companysIter.next();
                    Companys company = new Companys();
                    company.setCompanyCd(object.getString("companyCd"));
                    company.setCompanyNm(object.getString("companyNm"));
                    company.setCompanyNmEn(object.getString("companyNmEn"));
                    company.setCompanyPartNm(object.getString("companyPartNm"));
                    companysList.add(company);
                }
                kobisMovieInfo.setCompanys(companysList);

                // "audits" 키의 값인 JsonArray를 추출
                JSONArray auditsArray = movieInfoResult.getJSONArray("audits");
                Iterator<Object> auditsIter = auditsArray.iterator();
                List<Audits> auditsList = new ArrayList<>();
                while (auditsIter.hasNext()) {
                    JSONObject object = (JSONObject)auditsIter.next();
                    Audits audits = new Audits();
                    audits.setAuditNo(object.getString("auditNo"));
                    audits.setWatchGradeNm(object.getString("watchGradeNm"));
                    auditsList.add(audits);
                }
                kobisMovieInfo.setAudits(auditsList);

                kobisMovieInfo.setMovieNm(movieInfoResult.getString("movieNm"));
                kobisMovieInfo.setMovieNmEn(movieInfoResult.getString("movieNmEn"));
                kobisMovieInfo.setMovieNmOg(movieInfoResult.getString("movieNmOg"));
                kobisMovieInfo.setPrdtYear(movieInfoResult.getString("prdtYear"));
                kobisMovieInfo.setShowTm(movieInfoResult.getString("showTm"));
                kobisMovieInfo.setOpenDt(movieInfoResult.getString("openDt"));
                kobisMovieInfo.setPrdtStatNm(movieInfoResult.getString("prdtStatNm"));
                kobisMovieInfo.setTypeNm(movieInfoResult.getString("typeNm"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //반환
        return kobisMovieInfo;
    }

    
    public static void main(String[] args) {
        // API 객체 생성
        KobisMovieAPI api = new KobisMovieAPI();
        api.searchDailyBoxOfficeList(); // 작동 확인
        // KobisMovieInfoEntity entity = api.searchMovieInfo("20228797");// 범죄도시로 테스트
        // List<Actors> actors = entity.getActors();
        // actors.get(0).getCast();
        // actors.get(0).getCastEn();
        // actors.get(0).getPeopleNm();
        // actors.get(0).getPeopleNmEn();
 
        // API 요청
        // api.requestAPI();
        // api.requestBoxDailly();
    }
}
