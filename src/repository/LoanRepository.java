package repository;

import entity.Loan;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
