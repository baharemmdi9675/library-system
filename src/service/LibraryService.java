package service;

import entity.Book;
import entity.Loan;
import entity.Member;
import exception.*;

import java.sql.SQLException;

public class LibraryService {
    private int capacity;
    private MemberService memberService;
    private BookService bookService;
    private LoanService loanService;

    public LibraryService(int capacity, MemberService memberService, BookService bookService, LoanService loanService) {
        this.capacity = capacity;
        this.memberService = memberService;
        this.bookService = bookService;
        this.loanService = loanService;
    }

    public void register(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member is null");
        }
        if (member.getUsername() == null) {
            throw new IllegalArgumentException("Username is null");
        }
        if (member.getEmail() == null) {
            throw new IllegalArgumentException("Email is null");
        }
        if (memberService.countMember() >= capacity) {
            throw new LibraryFullException("The library capacity is full");
        }

        memberService.register(member);
    }

    public void deleteAccount(Integer id) {
        try {
            memberService.deleteById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void lendBook(Integer bookId, Integer userId) {
        Member fetchedMember = memberService.findById(userId);
        if (fetchedMember == null) {
            throw new MemberNotFoundException("No member found with the given id");
        }

        Book fetchedBook = bookService.findById(bookId);
        if (fetchedBook == null) {
            throw new BookNotFoundException("No book found with the given id");
        }

        Loan fetchedLoan = loanService.findActiveLoanByBookId(bookId);
        if (fetchedLoan != null) {
            throw new BookAlreadyLentException("Book is already lent");
        }

        loanService.addActiveLoan(bookId, userId);
    }

    public void returnBook(Integer bookId) {
        Loan fetchedLoan = loanService.findActiveLoanByBookId(bookId);
        if (fetchedLoan == null) {
            throw new LoanNotFoundException("No active loan found for the given book");
        }
        loanService.deActiveLoan(fetchedLoan.getId());
    }
}