package repository;

import entity.Book;
import entity.Member;
import exception.RepositoryException;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepository {

    public void save(Book book) {

        try (Connection connection = ConnectionUtil.getConnection()) {

            String sql = "Insert INTO book (name) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getName());
            ps.execute();
        } catch (SQLException e) {
            throw new RepositoryException("error", e);
        }

    }
    public Book findByName(String name) {
        try(Connection connection=ConnectionUtil.getConnection()){
            String sql="SELECT name FROM book WHERE name=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet resultSet=ps.executeQuery();
           if (resultSet.next()){

               String bookName=resultSet.getString("name");
               Integer bookId=resultSet.getInt("id");
               return new Book(bookId,bookName);
           }
           return null;
        } catch (SQLException e) {
            throw new RepositoryException("error", e);
        }
    }

    public Book findById(Integer id) {

        try (Connection connection = ConnectionUtil.getConnection()) {

            String sql = "SELECT id, name FROM book WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Integer fetchedId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                return new Book(fetchedId, name);
            }
            return null;
        } catch (SQLException e) {
            throw new RepositoryException("error", e);
        }
    }
}
