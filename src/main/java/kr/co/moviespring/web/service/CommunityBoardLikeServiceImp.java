package kr.co.moviespring.web.service;

import kr.co.moviespring.web.repository.CommunityBoardLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityBoardLikeServiceImp implements CommunityBoardLikeService {

    @Autowired
    CommunityBoardLikeRepository repository;

    @Override
    public Integer getCount(Long boardId, int status) {
        Integer count = repository.getCount(boardId, status);

        return count;
    }

    @Override
    public int getByMemberId(Long boardId, Long memberId) {
        return 0;
    }

    @Override
    public int like(Long boardId, Long userId, int status) {
        repository.save(boardId, userId, status);
        return 1;
    }

    @Override
    public int disLike(Long boardId, Long userId, int status) {
        repository.save(boardId, userId, status);
        return -1;
    }
}