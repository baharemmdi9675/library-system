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


    public Loan findActiveLoanByBookId (Integer bookId) throws SQLException {
        return loanRepository.findActiveLoanByBookId(bookId);
    }
}
