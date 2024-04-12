package kr.co.moviespring.web.service.communityService;

import kr.co.moviespring.web.entity.GeneralBoard;
import kr.co.moviespring.web.repository.CommunityRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImp implements CommunityService{

    @Autowired
    CommunityRepository communityRepository;
    // 게시글 등록//
    @Override
    public void write(String title, String contents,Long categoryId) {
        communityRepository.saveContents(title,contents,categoryId);

    }
    //게시글 목록//
    @Override
    public List<GeneralBoard> getList(Long CategoryId) {
        List<GeneralBoard> list= communityRepository.findAll(CategoryId);
        return list;
    }
    //게시글 상세//
    @Override
    public GeneralBoard getById(Long id) {
        GeneralBoard board = communityRepository.findById(id);
        return board;
    }
    @Override
    public List<GeneralBoard> getList() {
        List<GeneralBoard> list = communityRepository.findAll();
        return list;
    }
    @Override
    public List<GeneralBoard> getList1() {
        List<GeneralBoard> list1 = communityRepository.findAll1();
        return list1;
    }
    @Override
    public List<GeneralBoard> getList2() {
        List<GeneralBoard> list2 = communityRepository.findAll2();
        return list2;
    }
    @Override
    public List<GeneralBoard> getList3() {
        List<GeneralBoard> list3 = communityRepository.findAll3();
        return list3;
    }
    @Override
    public List<GeneralBoard> getList4() {
        List<GeneralBoard> list4 = communityRepository.findAll4();
        return list4;
    }
}
