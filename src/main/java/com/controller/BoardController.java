package com.controller;

import com.domain.Board;
import com.domain.Post;
import com.service.BoardService;
import com.service.PostService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return modelAndView;
    }

}
