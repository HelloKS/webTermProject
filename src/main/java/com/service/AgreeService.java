package com.service;

import com.domain.Agree;
import com.persistence.AgreeRepository;

import java.util.ArrayList;

public class AgreeService {
    private final AgreeRepository agreeRepository = AgreeRepository.getInstance();
    public AgreeService(){}
    public void save(Agree agree){
        agreeRepository.save(agree);
    }
    public void delete(Agree agree){
        agreeRepository.delete(agree);
    }
    public int countAgreeByPostId(int postId){
        ArrayList<Agree> count = agreeRepository.findByPostId(postId);
        int result = 0;
        for(Agree index : count){
            result++;
        }
        return result;
    }
    public Agree findAgree(int userid, int postid){
        return agreeRepository.findById(userid, postid);
    }
}
