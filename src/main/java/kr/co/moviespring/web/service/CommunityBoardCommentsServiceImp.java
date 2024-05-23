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
    public List<CommunityBoardCommentsView> getListById(Long boardId, Long memberId) {
        List<CommunityBoardCommentsView> list = repository.findAllById(boardId, memberId);
        return list;
    }

    @Override
    public List<CommunityBoardCommentsView> getListByMemberId(Long id) {
        return repository.findAllByMemberId(id);
    }

    @Override
    public List<CommunityBoardCommentsView> getList(Integer page, int size) {
        int offset = (page-1) * size;
        return repository.findAll(offset, size);
    }

    @Override
    public int getCount() {
        return getCount(null);
    }

    @Override
    public int getCount(Long memberId) {
        return repository.getCount(memberId);
    }

    //댓글 수정
    @Override
    public int editById(Long commentId, Long memberId, String comment) {
        int result = repository.edit(commentId, memberId, comment);
        return result;
    }
    //댓글 삭제
    @Override
    public int deleteById(Long commentId) {
        int result = repository.delete(commentId);
        return result;
    }
}
