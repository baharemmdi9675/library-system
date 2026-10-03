package repository;

import entity.Member;
import exception.RepositoryException;
import util.ConnectionUtil;

import java.sql.*;

public class MemberRepository extends BaseRepository<Member,Integer> {

    @Override
    protected String selectQuery() {
        return "SELECT id, username FROM member WHERE id = ?";
    }

    @Override
    protected Member getPS(ResultSet rs) throws SQLException {
        Integer userid = rs.getInt("id");
        String memberUsername = rs.getString("username");
        return new Member(userid, memberUsername);
    }

    public Member findByUsername(String username) {

        String sql = "SELECT id, username from member where username = ?";

        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return extractMember(resultSet);
        } catch (SQLException e) {
            throw new RepositoryException("Failed to access the dataBase", e);
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
            statement.setString(1, email);
            statement.setString(2, username);
            statement.executeUpdate();
        }
    }

    public int countMember() {
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

    @Override
    protected String insertQuery() {
        return "Insert INTO member (username,email) VALUES (?,?)";
    }


    @Override
    protected void setPS(Member member, PreparedStatement ps) throws SQLException {
        ps.setString(1, member.getUsername());
        ps.setString(2, member.getEmail());
    }
}