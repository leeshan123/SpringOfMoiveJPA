package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.CommunityBoardComments;
import kr.co.moviespring.web.entity.CommunityBoardCommentsView;
import kr.co.moviespring.web.entity.CommunityBoardView;
import kr.co.moviespring.web.repository.CommunityBoardCommentsRepository;
import kr.co.moviespring.web.repository.CommunityBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityBoardCommentsServiceImp implements CommunityBoardCommentsService {
    @Autowired
    CommunityBoardCommentsRepository repository;
    //커뮤니티 게시글 댓글달기
    @Override
    public void write(Long boardId, Long memberId, String content) {
        repository.saveContent(boardId,memberId,content);

    }

    //게시글 댓글 목록
    @Override
    public List<CommunityBoardCommentsView> getListById(Long boardId) {
        List<CommunityBoardCommentsView> list = repository.findAllById(boardId);
        return list;
    }

    @Override
    public List<CommunityBoardCommentsView> getListByMemberId(Long id) {
        return repository.findAllByMemberId(id);
    }
}
