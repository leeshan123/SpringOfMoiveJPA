package kr.co.moviespring.web.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import kr.co.moviespring.web.entity.People;
import kr.co.moviespring.web.repository.PeopleInsertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

    class PeopleListResult{

        @SerializedName("peopleListResult")
        private PeopleListInfo peopleListInfo;

        public PeopleListInfo getPeoplelistresult(){return peopleListInfo;}
    }

    class PeopleListInfo {
        @SerializedName("peopleList")
        private List<People> peoleList;

        public  List<People> getPeoplelist(){return peoleList;}
    }
}
