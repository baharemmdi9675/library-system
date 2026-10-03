package repository;

import entity.Loan;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanRepository  extends BaseRepository<Loan,Integer>{
    @Override
    protected String insertQuery() {
        return "Insert INTO loan (user_id, book_id, active_loan)  VALUES (?, ?, ?)";
    }

    @Override
    protected void setPS(Loan loan, PreparedStatement ps) throws SQLException {
        ps.setInt(1, loan.getUserId());
        ps.setInt(2, loan.getBookId());
        ps.setBoolean(3, loan.getActiveLoan());
    }

    @Override
    protected String selectQuery() {
        return "SELECT id, user_id, book_id, active_loan FROM loan WHERE id = ?";
    }

    @Override
    protected Loan getPS(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        Integer userId = rs.getInt("user_id");
        Integer bookId = rs.getInt("book_id");
        Boolean activeLoan = rs.getBoolean("active_loan");
        return new Loan(id, userId, bookId, activeLoan);
    }

//    public void save(Loan loan) {
//
//        try (Connection connection = ConnectionUtil.getConnection()) {
//
//            String sql = "Insert INTO loan (user_id, book_id, active_loan)  VALUES (?, ?, ?)";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, loan.getUserId());
//            ps.setInt(2, loan.getBookId());
//            ps.setBoolean(3, loan.getActiveLoan());
//            ps.execute();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public Loan findActiveLoanByBookId(Integer bookId) throws SQLException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM loan l WHERE active_loan = true AND book_id =" + bookId;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer userId = resultSet.getInt("user_id");
                Integer loanBookId = resultSet.getInt("book_id");
                Boolean activeLoan = resultSet.getBoolean("active_loan");
                return new Loan(id, userId, loanBookId, activeLoan);
            }
            return null;
        }
    }

    public void deActiveLoan(Integer id) {
        String sql = "UPDATE loan SET activeloan= ? WHERE id=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, false);
            statement.setInt(2, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    }
