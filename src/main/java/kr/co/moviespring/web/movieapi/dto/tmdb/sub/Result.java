package kr.co.moviespring.web.movieapi.dto.tmdb.sub;

public class Result {
    private String name;         // 영상소개글
    private String publishedAt; // 영상 업로드 날짜(외국사이트라 1일 차이)
    private String key;          // 영상 주소 키

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPublishedAt() {
        return publishedAt;
    }
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
}
