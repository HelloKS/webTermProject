package com.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontController", value = "/front/*")
public class FrontController extends HttpServlet {
    private Map<String, Controller> controllerMap =new HashMap<>();
    public FrontController(){}

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //프로젝트 + 파일경로까지 반환
        String uri = request.getRequestURI();
        //프로젝트 path만 반환
        String conPath = request.getContextPath();
        conPath+="/front";

        String com = uri.substring(conPath.length());

        System.out.println("uri "+ uri);
        System.out.println("conPath "+ conPath);
        System.out.println("com "+ com);
        String [] tokens = com.split("/");
        String domain = tokens[1];

        if(com.equals("/")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/webapp/index.jsp");
            dispatcher.forward(request, response);
        }else if(domain.equals("board")){
            controllerMap.put("board", new BoardController());
        }else if(domain.equals("post")){
            controllerMap.put("post", new PostController());
        }

        Controller controller = controllerMap.get(domain);

        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        ModelAndView mv = controller.process(request, response, com); //논리 이름

        String viewPath = viewResolver(mv.getViewName());
        View view = new View(viewPath);
        view.render(mv.getModel(), request, response);
    }
    private String viewResolver(String viewName){
        return "/"+viewName+".jsp";
    }
}
