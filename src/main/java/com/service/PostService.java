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
	public ArrayList<Post> findTenPostsFrom(int offset) {
        return postRepository.findTenFromOffset(offset);
    }
    public Post findArticleById(int id) {
		return postRepository.findById(id);
	}
	public ArrayList<Post> findBoardPosts(int offset, int boardId) {
		return postRepository.findTenFromOffsetByBoardId(offset, boardId);
	}
	public void addHit(int id) {postRepository.updateHit(id);}
}
