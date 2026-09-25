package service;

import entity.Member;
import exception.MemberNotFoundException;
import repository.MemberRepository;

import java.sql.SQLException;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void register(Member member) throws SQLException {
        Member fetchedMember = memberRepository.findByUsername(member.getUsername().toLowerCase());
        if (fetchedMember != null) {
            System.out.println("username already exit");
            return;
        }
        memberRepository.save(member);

    }

    public void deleteMember(String username) throws SQLException {
        Member fetchedMember = memberRepository.findByUsername(username.toLowerCase());
        if (fetchedMember != null) {
            memberRepository.deleteMember(username);
            return;
        }
        System.out.println("Not found");
    }

    public void updateMember(String username, String email) throws SQLException {
        Member fechedMember = memberRepository.findByUsername(username.toLowerCase());
        if (fechedMember != null) {
            memberRepository.updateMember(username, email);
            return;
        }
        throw new MemberNotFoundException("No member found with the given id");
    }

    public int countMember() throws SQLException {
        return memberRepository.countMember();
    }


}
