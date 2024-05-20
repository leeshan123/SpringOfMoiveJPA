package kr.co.moviespring.web.service;

public interface VisitorCountService {
    void incrementVisitorCount();

    Long getTotalVisitors();

    Long getTodayVisitors();

}
