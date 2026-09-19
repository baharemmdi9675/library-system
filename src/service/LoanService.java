package service;

import entity.Book;
import repository.LoanRepository;

import java.sql.SQLException;

public class LoanService {
    private LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void view(Long id,String name) throws SQLException {
        Book fetchBook=bookRepository.findByName(name.toLowerCase());
        if (fetchBook==null){
            bookRepository.save (new Book(id, name));
        }else {
            System.out.println("book already exits");
        }

    }
}
