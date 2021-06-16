package com.controller;

import com.domain.Board;
import com.domain.Post;
import com.service.BoardService;
import com.service.PostService;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardController implements Controller {

    private final BoardService boardService = new BoardService();
    private final PostService postService = new PostService();

    @Override
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        if (url.equals("/board/list")) {
            ArrayList<Board> boards = boardService.findBoards();
            modelAndView.setViewName("board/board-list");
            modelAndView.getModel().put("boards", boards);
        } else if (url.equals("/board/write")) {
            if (request.getMethod().equals("GET")) {
                modelAndView.setViewName("board/board-write");
            } else if (request.getMethod().equals("POST")) {
                request.setCharacterEncoding("UTF-8");
                /*
                Board board = new Board();
                board.setTitle(request.getParameter("title"));
                board.setContents(request.getParameter("content"));
                board.setWriter(request.getParameter("writer"));
                boardService.write(board);
                 */
                modelAndView.setViewName("board/board-writeok");
            }
        } else if (url.equals("/board/detail")) {
            Post article = postService.findArticleById(Integer.parseInt(request.getParameter("id")));
            modelAndView.setViewName("board/board-detail");
            modelAndView.getModel().put("board", article);
        } else if (url.equals("/board/modify")) {
            if (request.getMethod().equals("GET")) {
                Post article = postService.findArticleById(Integer.parseInt(request.getParameter("id")));
                modelAndView.setViewName("board/board-modify");
                modelAndView.getModel().put("board", article);
            } else if (request.getMethod().equals("POST")) {
                request.setCharacterEncoding("UTF-8");
                Post post = new Post();
                post.setId(Integer.parseInt(request.getParameter("id")));
                post.setTitle(request.getParameter("title"));
                post.setContents(request.getParameter("content"));
                post.setBoardId(Integer.parseInt(request.getParameter("boardId")));
                postService.update(post);
                modelAndView.setViewName("board/board-writeok");
            }
        } else if (url.equals("/board/delete")) {
            postService.delete(Integer.parseInt(request.getParameter("id")));
            modelAndView.setViewName("board/board-writeok");
        } else {
            modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return modelAndView;
    }

}
