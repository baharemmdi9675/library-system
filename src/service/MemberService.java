package service;

import entity.Member;
import repository.MemberRepository;

import java.sql.SQLException;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void register(String username) throws SQLException {
        Member fetchedMember = memberRepository.findByUsername(username.toLowerCase());
        if (fetchedMember != null) {
            System.out.println("username already exit");
            return;
        }


        Member member = new Member(username.toLowerCase());
        memberRepository.save(member);

    }
}
