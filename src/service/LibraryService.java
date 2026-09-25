package service;

import entity.Member;
import exception.LibraryFullException;

import java.sql.SQLException;

public class LibraryService {
    private int capacity;
    private MemberService memberService;

    public LibraryService(MemberService memberService, int capacity) {
        this.memberService = memberService;
        this.capacity = capacity;
    }

    public void register (Member member) throws SQLException {
        if (member==null){
            throw  new IllegalArgumentException("Member is null");
        }
        if (member.getUsername()==null){
            throw  new IllegalArgumentException("Username is null");
        }
        if (member.getEmail()==null){
            throw  new IllegalArgumentException("Email is null");
        }
        if (memberService.countMember()>=capacity){
            throw new LibraryFullException("The library capacity is full");
        }

        memberService.register(member);
    }

}
