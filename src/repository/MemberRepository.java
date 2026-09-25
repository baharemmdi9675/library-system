package repository;

import entity.Member;
import exception.RepositoryException;
import util.ConnectionUtil;

import java.sql.*;

public class MemberRepository {
    public void save(Member member) {

        try (Connection connection = ConnectionUtil.getConnection()) {

            String sql = "Insert INTO member (username,email) VALUES (?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, member.getUsername());
            ps.setString(2, member.getEmail());
            ps.execute();
        } catch (SQLException e) {
            throw new RepositoryException("error", e);
        }
    }

    public Member findById(Integer id) {

        try (Connection connection = ConnectionUtil.getConnection()) {

            String sql = "SELECT id, username FROM member WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            return extractMember(resultSet);
        } catch (SQLException e) {
            throw new RepositoryException("error", e);
        }
    }

    public Member findByUsername(String username) {

        String sql = "SELECT id, username from member where username = ?";

        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return extractMember(resultSet);
        } catch (SQLException e) {
            throw new RepositoryException("error", e);
        }


    }

    public Member extractMember(ResultSet rs) throws SQLException {

        if (rs.next()) {
            Integer userid = rs.getInt("id");
            String memberUsername = rs.getString("username");
            return new Member(userid, memberUsername);
        }
        return null;
    }

    public int deleteMember(String username) throws SQLException {
        String sql = "DELETE FROM member where username = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            return statement.executeUpdate();
        }
    }

    public void updateMember(String username, String email) throws SQLException {
        String sql = "UPDATE member SET email= ? WHERE username=?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,username);
             statement.executeUpdate();
        }
    }

    public int countMember () {
        String sql = "SELECT COUNT (*) FROM member";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RepositoryException("error", e);
        }
    }
}