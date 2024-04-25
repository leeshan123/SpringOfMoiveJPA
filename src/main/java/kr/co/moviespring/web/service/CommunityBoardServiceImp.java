package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.CommunityBoard;
import kr.co.moviespring.web.entity.CommunityBoardView;
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
    
    // 게시글 삭제//
    @Override
    public int deleteById(Long id) {
        int result = communityBoardRepository.delete(id);
        return result;
    }
    
    // 게시글 수정
    @Override
    public void editById(Long id, String title, String contents) {
        communityBoardRepository.edit(id, title, contents);
    }

    //카테고리별 게시글 목록//
    @Override
    public List<CommunityBoardView> getList(Long categoryId, int size) {
        List<CommunityBoardView> list = communityBoardRepository.findAll(categoryId, size);
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
