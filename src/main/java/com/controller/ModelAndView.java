package com.controller;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

//model과 viewpath에 대한 정보를 담음
public class ModelAndView {
    private String viewName;
    //String -> model의 이름 , Object -> 실제 데이터를 담고 있는 자료형, 어떤 자료형이 올지 모르기 때문에 Object로 선언
    private Map<String, Object> model = new HashMap<>();
    //HttpServletRespose 예외상태를 위한 변수 ex)404,500,...
    private int status;

    ModelAndView(){
        status = HttpServletResponse.SC_OK;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
