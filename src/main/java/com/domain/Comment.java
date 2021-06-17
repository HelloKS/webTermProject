package com.domain;

import java.time.LocalDateTime;

public class Comment {
    private int cmt_id; // ai
    private int post_id; //post id (FK)
    private String cmt_content;
    private LocalDateTime cmt_date;
    private int user_uid; // member id (FK)
    private String writer;

    public Comment() { }

    public Comment(int cmt_id, int post_id, String cmt_content, LocalDateTime cmt_date, int user_uid, String writer)
    {
        this.cmt_id = cmt_id;
        this.post_id = post_id;
        this.cmt_content = cmt_content;
        this.cmt_date = cmt_date;
        this.user_uid = user_uid;
        this.writer = writer;
    }

    public int getCmt_id() {
        return cmt_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getCmt_content() {
        return cmt_content;
    }

    public void setCmt_content(String cmt_content) {
        this.cmt_content = cmt_content;
    }

    public LocalDateTime getCmt_date() {
        return cmt_date;
    }

    public void setCmt_date(LocalDateTime cmt_date) {
        this.cmt_date = cmt_date;
    }

    public int getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(int user_uid) {
        this.user_uid = user_uid;
    }

    public String getWriter() { return writer; }

    public void setWriter(String writer) { this.writer = writer; }
}
