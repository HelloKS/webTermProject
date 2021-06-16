package com.service;

import com.domain.Member;
import com.persistence.MemberRepository;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;

public class MemberService {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    public MemberService() {

    }

    public void join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());

        if (findMember != null) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

    public ArrayList<Member> findMembers() {
        return memberRepository.findAll();
    }
}
