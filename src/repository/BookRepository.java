package repository;

import entity.Book;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepository {

    public void save(Book book) throws SQLException {

        try (Connection connection = ConnectionUtil.getConnection()) {

            String sql = "Insert INTO book (name) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getName());
            ps.execute();
        }

    }
    public Book findByName(String name) throws SQLException {
        try(Connection connection=ConnectionUtil.getConnection()){
            String sql="SELECT name FROM book WHERE name=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet resultSet=ps.executeQuery();
           if (resultSet.next()){

               String bookName=resultSet.getString("name");
               Long bookId=resultSet.getLong("id");
               return new Book(bookId,bookName);
           }
           return null;
        }
    }
}
