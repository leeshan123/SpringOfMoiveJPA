package kr.co.moviespring.web.service.communityService;

import kr.co.moviespring.web.entity.Category;
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

    //카테고리별 게시판 목록//
    @Override
    public List<Category> getListByCategoryId(Long categoryId) {
        List<Category> categorys = communityRepository.findByCategoryId(categoryId);
        return categorys;
    }
}
