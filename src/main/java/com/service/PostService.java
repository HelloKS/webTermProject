package com.service;

import com.domain.Post;
import com.persistence.PostRepository;

import java.util.ArrayList;


public class PostService {
	private final PostRepository postRepository = PostRepository.getInstance();
	public PostService() { }
	public void write(Post post) {
		postRepository.save(post);
	}
	public void update(Post post) {
        postRepository.update(post);
	}
	public void delete(int id) {
        postRepository.delete(id);
	}
	public ArrayList<Post> findAllPosts() {
        return postRepository.findAll();
    }
    public Post findArticleById(int id) {
		return postRepository.findById(id);
	}
	public ArrayList<Post> findBoardPosts(int boardId) {
		return postRepository.findByBoardId(boardId);
	}
}
