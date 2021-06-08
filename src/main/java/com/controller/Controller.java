package com.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {
	ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException;
}
