package repository;

import entity.Loan;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanRepository {
    public void save(Loan loan) {

        try (Connection connection = ConnectionUtil.getConnection()) {

            String sql = "Insert INTO loan (user_id, book_id, active_loan)  VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, loan.getUserId());
            ps.setInt(2, loan.getBookId());
            ps.setBoolean(3, loan.getActiveLoan());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Loan findActiveLoanByBookId (Integer bookId) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM loan l WHERE active_loan = true AND book_id =" + bookId;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                Integer id = resultSet.getInt("id");
                Integer userId = resultSet.getInt("user_id");
                Integer loanBookId = resultSet.getInt("book_id");
                Boolean activeLoan = resultSet.getBoolean("active_loan");
                return new Loan (id, userId, loanBookId, activeLoan);
            }
            return null;
        }
    }

}
