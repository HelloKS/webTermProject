package com.service;

import com.domain.Board;
import com.persistence.BoardRepository;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;


public class BoardService {
	private final BoardRepository boardRepository = BoardRepository.getInstance();
	public BoardService() {
		
	}
	public void write(Board board) {
		boardRepository.save(board);
	}
	public void update(Board board) {		
        boardRepository.update(board);
	}
	public void delete(long id) {
        boardRepository.delete(id);
	}
	public ArrayList<Board> findBoards() {
        return boardRepository.findAll();
    }
    public Board findArticleById(long id) {
		return boardRepository.findById(id);
	}
}
