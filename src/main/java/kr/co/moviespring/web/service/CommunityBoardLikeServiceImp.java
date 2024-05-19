package kr.co.moviespring.web.service;

import kr.co.moviespring.web.repository.CommunityBoardCommentsRepository;
import kr.co.moviespring.web.repository.CommunityBoardLikeRepository;
import kr.co.moviespring.web.repository.CommunityBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityBoardLikeServiceImp implements CommunityBoardLikeService {

    @Autowired
    private CommunityBoardLikeRepository repository;

    @Override
    public Integer getCount(Long boardId, int status) {
        Integer count = repository.getCount(boardId, status);

        return count;
    }

    @Override
    public Integer getStatusById(Long boardId, Long memberId) {
        Integer result = repository.getStatusById(boardId, memberId);
        if (result == null)
            return 0;
        return result;
    }

    @Override
    public int addLikeDisLike(Long id, Long memberId, int status, String type) {
        repository.save(id, memberId, status, type);
        return status;
    }

//    @Override
//    public int disLike(Long id, Long memberId, int status, String type) {
//        repository.save(id, memberId, status, type);
//        return -1;
//    }

}