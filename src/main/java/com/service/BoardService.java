package com.service;

import com.domain.Board;
import com.persistence.BoardRepository;

import java.util.ArrayList;


public class BoardService {
	private final BoardRepository boardRepository = BoardRepository.getInstance();
	public BoardService() { }
	public ArrayList<Board> findBoards() {
        return boardRepository.findAll();
    }
    public Board findBoardById(int id) {
		return boardRepository.findById(id);
	}
}
