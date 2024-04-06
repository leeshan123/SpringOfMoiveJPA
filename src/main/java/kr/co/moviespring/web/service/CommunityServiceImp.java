package kr.co.moviespring.web.service;

import kr.co.moviespring.web.repository.CommunityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImp implements CommunityService{

    @Autowired
    CommunityRepository communityRepository;
    // 게시글 등록//
    @Override
    public void write(String title, String contents) {
        communityRepository.saveContents(title,contents);

    }
}
