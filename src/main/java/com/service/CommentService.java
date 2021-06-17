package com.service;

import com.domain.Comment;
import com.persistence.CommentRepository;

import java.util.ArrayList;

public class CommentService {
    private final CommentRepository commentRepository = CommentRepository.getInstance();
    public CommentService() {}
    public void write(Comment comment) { commentRepository.write(comment);}
    public void delete(int id) {commentRepository.delete(id);}
    public void update(Comment comment) { commentRepository.update(comment);}
    public ArrayList<Comment> showComment(int id) { return commentRepository.showByPostId(id);}
}
