package kr.co.moviespring.web.service;

import kr.co.moviespring.web.entity.GeneralBoard;

public interface CommunityService {
    GeneralBoard write(String title, String contents);
}
