package kr.co.moviespring.web.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import kr.co.moviespring.web.entity.MoviePeople;
import kr.co.moviespring.web.entity.People;
import kr.co.moviespring.web.repository.PeopleInsertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class PeopleInsertServiceImp implements PeopleInsertService {

    @Autowired
    PeopleInsertRepository repository;



    @Override
    public void insertPeopleData(List<People> actorList) {
        for(int i=0;i<actorList.size();i++){
            repository.savePeople(actorList.get(i));
        }
    }

    @Override
    public void insertMoviePeople(List<MoviePeople> filmoList) {
        for(int i=0;i<filmoList.size();i++){
            repository.saveMoviePeople(filmoList.get(i));
        }
    }

    @Override
    public String getpeopleCd(int x) {
        return repository.getPeopleCd(x);
    }

    @Override
    public List<MoviePeople> getPeopleFilmoList(String key,String peopleCd) throws IOException {
        List<MoviePeople> peopleFilmoList = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        URL url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/people/searchPeopleInfo.json?key=" + key + "&peopleCd="+peopleCd);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        Gson gson = new GsonBuilder().create();


        PeopleInfoResult peopleInfoResult = gson.fromJson(sb.toString(),PeopleInfoResult.class);
        //사람 정보
        PeopleInfo peopleInfo = peopleInfoResult.getPeopleInfoResultInfo().getPeopleInfo();


        for(Filmo filmo :peopleInfoResult.getPeopleInfoResultInfo().getPeopleInfo().getFilmos()){
            MoviePeople moviePeople = new MoviePeople();

            moviePeople.setPeopleCd(peopleInfo.getPeopleCd());
            moviePeople.setPeopleNm(peopleInfo.getPeopleNm());
            moviePeople.setPeopleNmEn(peopleInfo.getPeopleNmEn());
            moviePeople.setSex(peopleInfo.getSex());
            moviePeople.setRepRoleNm(peopleInfo.getRepRoleNm());
            moviePeople.setMovieCd(filmo.getMovieCd());
            moviePeople.setMovieNm(filmo.getMovieNm());
            moviePeople.setMoviePartNm(filmo.getMoviePartNm());

            System.out.println(moviePeople.getPeopleNm());
            System.out.println(moviePeople.getMovieNm());

            peopleFilmoList.add(moviePeople);
        }


        System.out.println(peopleFilmoList.size());

        return peopleFilmoList;
    }




    @Override
    public List<People> getActorList(String key) {

        List<People> peopleList = new ArrayList<>();

        for(int i=200; i<1700; i++){

            try {
                StringBuilder sb = new StringBuilder();
                URL url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/people/searchPeopleList.json?key="+key+"&curPage="+i+"&itemPerPage=100");
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                Gson gson = new GsonBuilder().create();

                PeopleListResult peopleListResult = gson.fromJson(sb.toString(), PeopleListResult.class);

                if(peopleListResult != null && peopleListResult.getPeoplelistresult() != null){
                    for(People peopleInfo : peopleListResult.getPeoplelistresult().getPeoplelist()){
                        People people = new People();
                        people.setPeopleCd(peopleInfo.getPeopleCd());
                        people.setPeopleNm(peopleInfo.getPeopleNm());
                        people.setPeopleNmEn(peopleInfo.getPeopleNmEn());
                        people.setRepRoleNm(peopleInfo.getRepRoleNm());
                        people.setFilmoNames(peopleInfo.getFilmoNames());


                        peopleList.add(people);
                    }
                } else {
                    System.out.println("사람이 앙나옴.");
                }
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println(peopleList.size());

        return peopleList;
    }

    //영화인 상세정보 리스트 결과
    class PeopleListResult{

        @SerializedName("peopleListResult")
        private PeopleListInfo peopleListInfo;

        public PeopleListInfo getPeoplelistresult(){return peopleListInfo;}

    }
    //영화인 목록 리스트 정보 가져오기
    class PeopleListInfo {
        @SerializedName("peopleList")
        private List<People> peoleList;

        public  List<People> getPeoplelist(){return peoleList;}
    }

    class PeopleInfoResult {

        @SerializedName("peopleInfoResult")
                private PeopleInfoResultInfo peopleInfoResultInfo;

        public PeopleInfoResultInfo getPeopleInfoResultInfo(){return peopleInfoResultInfo;}
    }

    class PeopleInfoResultInfo {

        @SerializedName("peopleInfo")
        private PeopleInfo peopleInfo;

        public PeopleInfo getPeopleInfo(){return peopleInfo;};
    }


    class PeopleInfo {
        @SerializedName("peopleCd")
        private String peopleCd;
        @SerializedName("peopleNm")
        private String peopleNm;
        @SerializedName("peopleNmEn")
        private String peopleNmEn;
        @SerializedName("sex")
        private String sex;
        @SerializedName("repRoleNm")
        private String repRoleNm;

        @SerializedName("filmos")
        private List<Filmo> filmos;

        public String getPeopleCd() {
            return peopleCd;
        }

        public String getPeopleNm() {
            return peopleNm;
        }

        public String getPeopleNmEn() {
            return peopleNmEn;
        }

        public String getSex() {
            return sex;
        }

        public String getRepRoleNm() {
            return repRoleNm;
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
        @SerializedName("moviePartNm")
        private String moviePartNm;

        public String getMovieCd() {
            return movieCd;
        }

        public String getMovieNm() {
            return movieNm;
        }

        public String getMoviePartNm() {
            return moviePartNm;
        }
    }


}
