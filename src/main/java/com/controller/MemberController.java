package com.controller;

import com.domain.Member;
import com.service.MemberService;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberController implements Controller {

    private final MemberService memberService = new MemberService();

    @Override
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView();
        //GET = 가입창, POST = 가입요청req
        if (url.equals("/member/join")) {
            if (request.getMethod().equals("GET")) {
                modelAndView.setViewName("member/join-form");
            } else if (request.getMethod().equals("POST")) {
                Member member = new Member();
                member.setEmail(request.getParameter("email"));
                member.setPassword(request.getParameter("pass"));
                member.setNickname(request.getParameter("nick"));
                memberService.join(member);
                modelAndView.setViewName("index");
            }
        } else if (url.equals("/member/members")) {
            ArrayList<Member> members = memberService.findMembers();
            modelAndView.setViewName("member/member-list");
            modelAndView.getModel().put("members", members);
        } else if (url.equals("/member/login")) {
          if (request.getMethod().equals("GET")) {
              modelAndView.setViewName("member/login-form");
          } else if (request.getMethod().equals("POST")) {
              Member member = new Member();
              member.setEmail(request.getParameter("email"));
              member.setPassword(request.getParameter("pass"));
              member = memberService.tryLogin(member);

              request.getSession(true).setAttribute("LOGIN", member);
              modelAndView.setViewName("index");
          }
        } else if (url.equals("/member/logout")) {
            if (request.getMethod().equals("GET")) {
                HttpSession session = request.getSession(true);
                session.invalidate();

                modelAndView.setViewName("index");
            }
        } else {
            modelAndView.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return modelAndView;
    }
}
