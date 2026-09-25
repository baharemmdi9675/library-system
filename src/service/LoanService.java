package service;

import entity.Book;
import entity.Loan;
import entity.Member;
import repository.LoanRepository;

import java.sql.SQLException;

public class LoanService {
    private LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }


    public Loan findActiveLoanByBookId(Integer bookId) {
        try {
            return loanRepository.findActiveLoanByBookId(bookId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addActiveLoan(Integer bookId, Integer userId) {
        loanRepository.save(new Loan(userId, bookId, true));
    }

    public void deActiveLoan(Integer id)  {
        loanRepository.deActiveLoan(id);
    }
}
