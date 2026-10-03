


package repository;

import entity.Member;
import exception.RepositoryException;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseRepository<T, ID> {


    public void save(T t) {

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = insertQuery(); //"Insert INTO member (username,email) VALUES (?,?)"
            PreparedStatement ps = connection.prepareStatement(sql);

            setPS(t, ps);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract String insertQuery();
    protected abstract void setPS(T entity, PreparedStatement ps) throws SQLException;

    public T findById(Integer id) {

        try (Connection connection = ConnectionUtil.getConnection()) {

            String sql = selectQuery(); // "SELECT id, username FROM member WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
//            return extractMember(resultSet);
            if (resultSet.next()) {
//                Integer userid = resultSet.getInt("id");
//                String memberUsername = resultSet.getString("username");
//                return new Member(userid, memberUsername);
                return getPS(resultSet);
            }
            return null;

        } catch (SQLException e) {
            throw new RepositoryException("Failed to access the dataBase", e);
        }
    }
    protected abstract String selectQuery();
    protected abstract T getPS(ResultSet rs) throws SQLException;


//    public Member extractMember(ResultSet rs) throws SQLException {
//
//        if (rs.next()) {
//            Integer userid = rs.getInt("id");
//            String memberUsername = rs.getString("username");
//            return new Member(userid, memberUsername);
//        }
//        return null;
//    }

}




