package repository;

import entity.Loan;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanRepository {
    public void save(Loan loan) throws SQLException {

        try (Connection connection = ConnectionUtil.getConnection()) {

            String sql = "Insert INTO loan (user_id, book_id, active_loan)  VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, loan.getUserId());
            ps.setLong(2, loan.getBookId());
            ps.setBoolean(3, loan.getActiveLoan());
            ps.execute();
        }
    }

    public Loan findActiveLoanByBookId (Long bookId) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM loan l WHERE active_loan = true AND book_id =" + bookId;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                Long id = (long) resultSet.getInt("id");
                Long userId = (long) resultSet.getInt("user_id");
                Long loanBookId = (long) resultSet.getInt("book_id");
                Boolean activeLoan = resultSet.getBoolean("active_loan");
                return new Loan (id, userId, loanBookId, activeLoan);
            }
            return null;
        }
    }

}
