package com.controller;

import com.domain.Board;
import com.service.BoardService;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardController implements Controller{

	private final BoardService boardService = new BoardService();
	@Override
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {		
		ModelAndView modelAndView = new ModelAndView();
		if(url.equals("/board/list")) {
			ArrayList<Board> boards = boardService.findBoards();
			modelAndView.setViewName("board/board-list");
			modelAndView.getModel().put("boards", boards);
		} else if (url.equals("/board/write")) {
			if (request.getMethod().equals("GET")) {
				modelAndView.setViewName("board/board-write");
			} else if (request.getMethod().equals("POST")) {
				request.setCharacterEncoding("UTF-8");
				Board board = new Board();
				board.setTitle(request.getParameter("title"));
				board.setContents(request.getParameter("content"));
				board.setWriter(request.getParameter("writer"));
				boardService.write(board);
				modelAndView.setViewName("board/board-writeok");
			}
		} else if (url.equals("/board/detail")) {
			Board article = boardService.findArticleById(Long.parseLong(request.getParameter("id")));
			modelAndView.setViewName("board/board-detail");
			modelAndView.getModel().put("board", article);
		} else if (url.equals("/board/modify")) {
			if (request.getMethod().equals("GET")) {
				Board article = boardService.findArticleById(Long.parseLong(request.getParameter("id")));
				modelAndView.setViewName("board/board-modify");
				modelAndView.getModel().put("board", article);
			} else if (request.getMethod().equals("POST")) {
				request.setCharacterEncoding("UTF-8");
				Board board = new Board();
				board.setId(Long.parseLong(request.getParameter("id")));
				board.setTitle(request.getParameter("title"));
				board.setContents(request.getParameter("content"));
				board.setWriter(request.getParameter("writer"));
				boardService.update(board);
				modelAndView.setViewName("board/board-writeok");
			}
		} else if (url.equals("/board/delete")) {
			boardService.delete(Long.parseLong(request.getParameter("id")));
			modelAndView.setViewName("board/board-writeok");
		} else {
			modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return modelAndView;
	}

}
