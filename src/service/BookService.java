package service;

import entity.Book;
import entity.Member;
import repository.BookRepository;

import java.sql.SQLException;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void register(Integer id, String name) {
        Book fetchBook = bookRepository.findByName(name.toLowerCase());
        if (fetchBook == null) {
            bookRepository.save(new Book(id, name));
        } else {
            System.out.println("book already exits");
        }

    }

    public Book findById(Integer id) {
        return bookRepository.findById(id);
    }
}
