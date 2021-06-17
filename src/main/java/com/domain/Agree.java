package com.domain;

public class Agree {
    private int agreeId;
    private int userId;
    private int postId;

    public Agree() {
    }

    public Agree(int userId, int postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public Agree(int agreeId, int userId, int postId) {
        this.agreeId = agreeId;
        this.userId = userId;
        this.postId = postId;
    }

    public int getAgreeId() {
        return agreeId;
    }

    public void setAgreeId(int agreeId) {
        this.agreeId = agreeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
