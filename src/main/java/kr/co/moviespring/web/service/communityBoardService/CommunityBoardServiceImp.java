package kr.co.moviespring.web.service.communityBoardService;

import kr.co.moviespring.web.entity.CommunityBoard;
import kr.co.moviespring.web.repository.CommunityBoardRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityBoardServiceImp implements CommunityBoardService {

    @Autowired
    CommunityBoardRepository communityBoardRepository;

    // 게시글 등록//
    @Override
    public void write(String title, String contents,Long categoryId) {
        communityBoardRepository.saveContents(title,contents,categoryId);
    }

    //카테고리별 게시글 목록//
    @Override
    public List<CommunityBoard> getList(Long categoryId, int size) {
        List<CommunityBoard> list = communityBoardRepository.findAll(categoryId, size);
        return list;
    }

    //게시글 상세//
    @Override
    public CommunityBoard getById(Long id) {
        CommunityBoard board = communityBoardRepository.findById(id);
        return board;
    }

//    @Override
//    public List<GeneralBoard> getList() {
//        List<GeneralBoard> list = communityRepository.findAll();
//        return list;
//    }
}
