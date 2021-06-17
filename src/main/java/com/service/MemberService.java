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

    // 여기서 받는 member는 이메일과 패스워드 밖에 없음
    public Member tryLogin(Member member) {
        if (!memberRepository.checkLogin(member.getEmail(), member.getPassword())) {
            throw new IllegalArgumentException("아이디 혹은 패스워드가 틀림");
        }

        Member user = memberRepository.findByEmail(member.getEmail());
        return user;
    }

    public Member findByUserId(int id) {
        return memberRepository.findByUserId(id);
    }

    public ArrayList<Member> findMembers() {
        return memberRepository.findAll();
    }
}
